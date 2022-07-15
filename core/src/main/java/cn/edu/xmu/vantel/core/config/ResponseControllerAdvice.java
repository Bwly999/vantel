package cn.edu.xmu.vantel.core.config;

import cn.edu.xmu.vantel.core.model.Response;
import cn.edu.xmu.vantel.core.util.Common;
import cn.edu.xmu.vantel.core.util.JacksonUtil;
import cn.edu.xmu.vantel.core.util.ReturnNo;
import cn.edu.xmu.vantel.core.util.ReturnObject;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getParameterType().equals(ReturnObject.class);
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (data instanceof ReturnObject) {
            ReturnObject<?> returnObject = ((ReturnObject<?>) data);
            response.setStatusCode(Common.getHttpStatus(returnObject));
        }
        return data;
    }
}
