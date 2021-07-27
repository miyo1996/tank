package tank;

public class FourDirFireStrategy implements FireStrategy{
    @Override
    public void fire(Tank tank) {
        int bx = tank.x+Tank.WIDTH/2-Bullet.WIDTH/2;
        int by = tank.y+Tank.HEIGHT/2-Bullet.HEIGHT/2;

        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            new Bullet(bx, by, dir, tank.group, tank.tf);
        }

        if(tank.group == Group.GOOD) {
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
        }
    }
}
