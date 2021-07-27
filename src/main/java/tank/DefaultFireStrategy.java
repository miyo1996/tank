package tank;

/*
 * 默认设计策略
 */
public class DefaultFireStrategy implements FireStrategy{
    @Override
    public void fire(Tank tank) {
        int bx = tank.x+Tank.WIDTH/2-Bullet.WIDTH/2;
        int by = tank.y+Tank.HEIGHT/2-Bullet.HEIGHT/2;

        new Bullet(bx,by,tank.dir,tank.group,tank.tf);

        if(tank.group == Group.GOOD) {
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
        }
    }
}
