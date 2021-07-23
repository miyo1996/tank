package tank.test;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImageTest{

    @Test
    public void test() {

        try {
//            BufferedImage image = ImageIO.read(new File("/Users/shijiapeng/Downloads/5245d01373f08202ad7753ec49fbfbedaa641b0f.png"));
//            assertNotNull(image);

            BufferedImage image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
//            this.getClass()
            assertNotNull(image2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}