package tank;

import lombok.Data;
import java.awt.*;
import java.util.Random;

/*
 * 坦克
 */
@Data
public class Tank {
    //初始坐标位置
    public int x,y;
    //初始方向
    Dir dir = Dir.DOWN;
    //坦克大小
    static int WIDTH = ResourceMgr.goodTankD.getWidth(), HEIGHT = ResourceMgr.goodTankD.getHeight();
    //移动速度
    private static final int SPEED = Integer.parseInt((String)PropertyMgr.get("tankSpeed"));
    //是否处于移动状态
    boolean moving = true;
    //是否存活
    private boolean living = true;
    //阵营
    public Group group = Group.BAD;

    public FireStrategy fireStrategy;

    private Random random = new Random();

    TankFrame tf = null;

    Rectangle rect = new Rectangle();

    public Tank (int x,int y,Dir dir,Group group,TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        if (group == Group.GOOD) {
            String goodFSName = PropertyMgr.get("goodStrategy").toString();
            try {
                fireStrategy = (FireStrategy)Class.forName(goodFSName).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            String badFSName = PropertyMgr.get("goodStrategy").toString();
            try {
                fireStrategy = (FireStrategy)Class.forName(badFSName).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    //绘制
    public void paint (Graphics g) {

        if (!living) {
            tf.tanks.remove(this);
        }

        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL,x,y,null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD,x,y,null);
                break;

            default:
                break;
        }

        move();

    }
    //坦克移动
    private void move() {
        if (!moving) return;

        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;

            default:
                break;
        }

        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            this.fire();
        }

        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            randomDir();
        }

        boundCheck();

        //更新 rect
        rect.x = this.x;
        rect.y = this.y;
    }

    //边界检测
    private void boundCheck() {
        if (x < 0) x = 0;
        if (y < 30) y = 30;
        if (x > TankFrame.GAME_WIDTH-Tank.WIDTH) x = TankFrame.GAME_WIDTH-Tank.WIDTH;
        if (y > TankFrame.GAME_HEIGHT-Tank.HEIGHT) y = TankFrame.GAME_HEIGHT-Tank.HEIGHT;
    }

    //随机移动
    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    //开火
    public void fire() {
        fireStrategy.fire(this);
    }

    //坦克死亡
    public void die() {
        this.living = false;
    }
}
