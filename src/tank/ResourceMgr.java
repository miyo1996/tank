package tank;

import util.ImageUtils;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*
 * 资源预加载
 */
public class ResourceMgr {

    private static final ResourceMgr INSTANCE = new ResourceMgr();

    private ResourceMgr() {}

    public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD;
    public static BufferedImage badTankL, badTankU, badTankR, badTankD;
    public static BufferedImage bulletL, bulletU, bulletR, bulletD;

    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Images/GoodTank1.png"));
            goodTankL = ImageUtils.rotateImage(goodTankU,-90);
            goodTankR = ImageUtils.rotateImage(goodTankU,90);
            goodTankD = ImageUtils.rotateImage(goodTankU,180);
            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Images/BadTank1.png"));
            badTankL = ImageUtils.rotateImage(badTankU,-90);
            badTankR = ImageUtils.rotateImage(badTankU,90);
            badTankD = ImageUtils.rotateImage(badTankU,180);
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Images/bulletU.png"));
            bulletL = ImageUtils.rotateImage(bulletU,-90);
            bulletR = ImageUtils.rotateImage(bulletU,90);
            bulletD = ImageUtils.rotateImage(bulletU,180);

            for (int i = 0 ; i < 16 ;i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("Images/e"+(i+1)+ ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "ResourceMgr{}";
    }
}
