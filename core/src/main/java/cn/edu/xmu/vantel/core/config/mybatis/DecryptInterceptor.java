package cn.edu.xmu.vantel.core.config.mybatis;

import cn.edu.xmu.vantel.core.util.entrypt.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Intercepts({
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = Statement.class)
})
public class DecryptInterceptor implements Interceptor {
    private EncryptFieldCacheHolder.EncryptFieldCache encryptFieldCache = EncryptFieldCacheHolder.getInstance();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object resultObject = invocation.proceed();
        try {
            if (Objects.isNull(resultObject)) {
                return null;
            }
            if (resultObject instanceof List) {
                // 基于selectList

                List<?> resultList = (ArrayList<?>) resultObject;
                if (!resultList.isEmpty()) {
                    Class<?> paramClass = resultList.get(0).getClass();
                    List<Field> encryptFieldList = encryptFieldCache.getAndAutoSet(paramClass);
                    for (Field field : encryptFieldList) {
                        EncryptValue annotation = field.getAnnotation(EncryptValue.class);
                        if (annotation != null) {
                            for (Object obj : resultList) {
                                EncryptUtil.decrypt(obj, field, annotation.method());
                            }

                        }
                    }
                }
            } else {
                // 基于selectOne
                Class<?> paramClass = resultObject.getClass();
                List<Field> encryptFieldList = encryptFieldCache.getAndAutoSet(paramClass);
                for (Field field : encryptFieldList) {
                    EncryptValue annotation = field.getAnnotation(EncryptValue.class);
                    if (annotation != null) {
                        EncryptUtil.decrypt(resultObject, field, annotation.method());
                    }
                }
            }
            return resultObject;
        } catch (Exception e) {
            log.error("解密失败", e);
        }
        return resultObject;
    }

    @Override
    public Object plugin(Object target) {
        // 将这个拦截器接入拦截器链
        return Plugin.wrap(target, this);
    }
}
