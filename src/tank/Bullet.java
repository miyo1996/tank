package tank;

import lombok.Data;
import java.awt.*;

/*
 * 子弹
 */
@Data
public class Bullet {
    //子弹速度
    private static final int SPEED = Integer.parseInt((String)PropertyMgr.get("bulletSpeed"));;
    //子弹大小
    static int WIDTH = ResourceMgr.bulletD.getWidth(), HEIGHT = ResourceMgr.bulletD.getHeight();
    //初始坐标
    private int x , y;
    //子弹方向
    private Dir dir;

    private TankFrame tf;
    //是否存活
    private Boolean living = true;
    //阵营
    public Group group = Group.BAD;

    Rectangle rect = new Rectangle();

    public Bullet (int x , int y , Dir dir , Group group ,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        tf.bullets.add(this);
    }
    //绘制
    public void paint(Graphics g) {

        if (!living) {
            tf.bullets.remove(this);
        }

        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;

            default:
                break;
        }

        move();

    }
    //子弹移动
    private void move() {

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

        //更新 rect
        rect.x = this.x;
        rect.y = this.y;

        if (x<0 || y<0 || x>TankFrame.GAME_WIDTH || y>TankFrame.GAME_WIDTH) {
            this.living = false;
        }
    }

    //碰撞检测
    public void collidewith(Tank tank) {
        if (this.group == tank.group) return;

        if (rect.intersects(tank.rect)) {
            tank.die();
            this.die();

            int ex = tank.getX()+Tank.WIDTH/2-this.WIDTH/2;
            int ey = tank.getY()+Tank.HEIGHT/2-this.HEIGHT/2;
            tf.explodes.add(new Explode(ex,ey,tf));
        }
    }

    //子弹死亡
    private void die() {
        this.living = false;
    }
}
