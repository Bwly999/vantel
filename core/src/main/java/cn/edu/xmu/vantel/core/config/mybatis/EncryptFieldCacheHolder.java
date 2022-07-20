package cn.edu.xmu.vantel.core.config.mybatis;

import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EncryptFieldCacheHolder {
    enum EncryptFieldCache {
        // 单例
        INSTANCE;
        private Map<Class<?>, List<Field>> fieldMap = new ConcurrentHashMap<>();

        public List<Field> getAndAutoSet(Class<?> aClass) {
            List<Field> fieldList = fieldMap.get(aClass);
            if (fieldList == null) {
                fieldList = new ArrayList<>();
                Field[] fields = aClass.getDeclaredFields();
                for (Field field : fields) {
                    EncryptValue annotation = AnnotationUtils.findAnnotation(field, EncryptValue.class);
                    if (annotation != null) {
                        fieldList.add(field);
                    }
                }
                fieldMap.put(aClass, fieldList);
            }
            return fieldList;
        }

        public void put(Class<?> aClass, List<Field> fieldList) {
            fieldMap.put(aClass, fieldList);
        }
    }

    public static EncryptFieldCache getInstance() {
        return EncryptFieldCache.INSTANCE;
    }
}
