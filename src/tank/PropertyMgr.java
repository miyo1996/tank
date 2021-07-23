package tank;

import java.io.IOException;
import java.util.Properties;

/*
 * 读配置文件
 */
public class PropertyMgr {
    static Properties pros = new Properties();

    static {
        try {
            pros.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key) {
        if (pros == null) return null;
        return pros.get(key);
    }

}
