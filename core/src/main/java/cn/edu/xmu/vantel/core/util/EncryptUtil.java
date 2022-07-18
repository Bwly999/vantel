package cn.edu.xmu.vantel.core.util;

import cn.edu.xmu.vantel.core.util.entrypt.AESCoder;
import cn.edu.xmu.vantel.core.util.entrypt.Coder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class EncryptUtil {
    private static Logger log = LoggerFactory.getLogger(EncryptUtil.class);

    private static Map<String, Coder> coderMap = new HashMap<>();

    static {
        coderMap.put("aes", new AESCoder());
    }

    public static void encrypt(Object obj, Field encryptField, String method) {
        try {
            encryptField.setAccessible(true);
            Object value = encryptField.get(obj);
            if (value instanceof String) {
                String content = (String) value;
                Coder coder = coderMap.get(content);
                if (coder != null) {
                    String encryptValue = coder.encrypt(content);
                    encryptField.set(obj, encryptValue);
                }
            }
        } catch (IllegalAccessException e) {
            log.error(e.getStackTrace()[0] + e.getMessage());
        }
    }
}
