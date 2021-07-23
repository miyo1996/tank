package tank;

import java.awt.*;

/*
 * 爆炸
 */
public class Explode {
    //爆炸大小
    static int WIDTH = ResourceMgr.explodes[0].getWidth(), HEIGHT = ResourceMgr.explodes[0].getHeight();
    //初始坐标
    private int x , y;

    private int step = 0;

    private TankFrame tankFrame;

    public Explode (int x , int y , TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;

        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }
    //绘制
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if (step >= ResourceMgr.explodes.length) {
            tankFrame.explodes.remove(this);
        }
    }

}
