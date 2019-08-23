package cn.luyijun.fitness.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>日志拦截器</p>
 *
 * @author lili
 * @version 1.0: LoggingClientHttpRequestInterceptor.java, v0.1 2019-01-12 14:03 PM lili Exp $
 */
@Component("loggingClientHttpRequestInterceptor")
public class LoggingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private final static Logger LOGGER = LoggerFactory
        .getLogger(LoggingClientHttpRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        traceResponse(response);
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        LOGGER.debug(
            "===========================request begin================================================");
        LOGGER.debug("URI         : {}", request.getURI());
        LOGGER.debug("Method      : {}", request.getMethod());
        LOGGER.debug("Headers     : {}", request.getHeaders());
        LOGGER.debug("Request body: {}", new String(body, "UTF-8"));
        LOGGER.debug(
            "==========================request end================================================");
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {

        LOGGER.debug(
            "============================response begin==========================================");
        LOGGER.debug("Status code  : {}", response.getStatusCode());
        LOGGER.debug("Status text  : {}", response.getStatusText());
        LOGGER.debug("Headers      : {}", response.getHeaders());
        //WARNING: comment out in production to improve performance
      //  LOGGER.debug("Response body: {}", inputStringBuilder.toString());
        LOGGER.debug(
            "=======================response end=================================================");
    }

}
