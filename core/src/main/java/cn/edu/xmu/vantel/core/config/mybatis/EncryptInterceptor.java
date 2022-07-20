package cn.edu.xmu.vantel.core.config.mybatis;

import cn.edu.xmu.vantel.core.util.entrypt.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Intercepts({
        @Signature(type = ParameterHandler.class, method = "setParameters", args = PreparedStatement.class)
})
public class EncryptInterceptor implements Interceptor {
    private EncryptFieldCacheHolder.EncryptFieldCache encryptFieldCache = EncryptFieldCacheHolder.getInstance();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        try {
            Object target = invocation.getTarget();
            if (target instanceof ParameterHandler) {
                ParameterHandler parameterHandler = (ParameterHandler) target;
                Object parameterObject = parameterHandler.getParameterObject();
                // update时的情况
                if (parameterObject != null) {
                    if (parameterObject instanceof MapperMethod.ParamMap<?>) {
                        MapperMethod.ParamMap<?> paramMap = (MapperMethod.ParamMap<?>) parameterObject;
                        parameterObject = paramMap.getOrDefault("et", null);
                    }
                    if (parameterObject != null) {
                        Class<?> paramClass = parameterObject.getClass();

                        List<Field> encryptFieldList = encryptFieldCache.getAndAutoSet(paramClass);
                        for (Field field : encryptFieldList) {
                            EncryptValue annotation = field.getAnnotation(EncryptValue.class);
                            if (annotation != null) {
                                EncryptUtil.encrypt(parameterObject, field, annotation.method());
                            }
                        }
                    }
                }
            }

            return invocation.proceed();
        } catch (Throwable e) {
            log.error("encrypt fail");
            throw e;
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
}
