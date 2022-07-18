package cn.edu.xmu.vantel.core.config.mybatis;

import cn.edu.xmu.vantel.core.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;

@Slf4j
@Component
@Intercepts({
        @Signature(type = ParameterHandler.class, method = "setParameters", args = PreparedStatement.class)
})
public class EncryptInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        try {
            ParameterHandler parameterHandler = (ParameterHandler) invocation;
            Object parameterObject = parameterHandler.getParameterObject();
            // update时的情况
            if (parameterObject != null) {
                if (parameterObject instanceof MapperMethod.ParamMap<?>) {
                    MapperMethod.ParamMap<?> paramMap = (MapperMethod.ParamMap<?>) parameterObject;
                    parameterObject = paramMap.get("et");
                }
                if (parameterObject != null) {
                    Class<?> paramClass = parameterObject.getClass();
                    Field[] fields = paramClass.getDeclaredFields();
                    for (Field field : fields) {
                        EncryptValue annotation = AnnotationUtils.findAnnotation(field, EncryptValue.class);
                        if (annotation != null) {
                            EncryptUtil.encrypt(parameterObject, field, annotation.method());
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
