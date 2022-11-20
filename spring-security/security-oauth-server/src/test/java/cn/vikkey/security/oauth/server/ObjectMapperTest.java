package cn.vikkey.security.oauth.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.security.web.jackson2.WebJackson2Module;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ObjectMapperTest {

    @Test
    void testDefaultCsrfTokenObjectMapper() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String expectJson = "{\"token\":\"x-csrf-token\",\"parameterName\":\"x-csrf-token\",\"headerName\":\"x-csrf-token\"}";
        DefaultCsrfToken token = new DefaultCsrfToken("x-csrf-token", "x-csrf-token", "x-csrf-token");
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        mapper.writeValue(outputStream, token);
        // 默认情况下可以序列化
        Assertions.assertEquals(expectJson, outputStream.toString());
        // 序列化结果在进行反序列化的时候会报错，不满足jsckson的要求，默认构造函数，getter/setter方法（内省）
        Assertions.assertThrowsExactly(InvalidDefinitionException.class, () -> mapper.readValue(outputStream.toString(), DefaultCsrfToken.class));
    }

    @Test
    void testDefaultCsrfTokenObjectMapperMixIn() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new WebJackson2Module());
        // 默认增加了type id @class
        String expectJson = "{\"@class\":\"org.springframework.security.web.csrf.DefaultCsrfToken\",\"headerName\":\"x-csrf-token\",\"parameterName\":\"x-csrf-token\",\"token\":\"x-csrf-token\"}";
        DefaultCsrfToken token = new DefaultCsrfToken("x-csrf-token", "x-csrf-token", "x-csrf-token");
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        mapper.writeValue(outputStream, token);
        // 默认情况下可以序列化
        Assertions.assertEquals(expectJson, outputStream.toString());
        // 正常反序列化
        DefaultCsrfToken token1 = mapper.readValue(expectJson, DefaultCsrfToken.class);
        Assertions.assertEquals(token.getToken(), token1.getToken());
        Assertions.assertEquals(token.getHeaderName(), token1.getHeaderName());
        Assertions.assertEquals(token.getParameterName(), token1.getParameterName());
    }
}
