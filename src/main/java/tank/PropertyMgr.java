package tank;

import java.io.IOException;
import java.util.Properties;

/*
 * 读配置文件
 */
public class PropertyMgr {
    private static final Properties INSTANCE = new Properties();

    private PropertyMgr() {}

    static {
        try {
            INSTANCE.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key) {
        if (INSTANCE == null) return null;
        return INSTANCE.get(key);
    }

}
