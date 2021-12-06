package club.p6e.germ.cloud.gateway.log;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Log
 * @author lidashuang
 * @version 1.0
 */
@Component
public class LogGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    /** 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(LogGatewayFilterFactory.class);
    private static final DataBufferFactory DATA_BUFFER_FACTORY = new DefaultDataBufferFactory();

    @Override
    public GatewayFilter apply(Object config) {
        return new CustomGatewayFilter();
    }

    /**
     * 自定义过滤器
     */
    public static class CustomGatewayFilter implements GatewayFilter, Ordered {
        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            final Model model = new Model();
            final ServerHttpRequest request = new LogServerHttpRequestDecorator(exchange.getRequest(), model);
            final ServerHttpResponse response = new LogServerHttpResponseDecorator(exchange.getResponse(), model);
            return chain.filter(exchange.mutate().request(request).response(response).build());
        }

        @Override
        public int getOrder() {
            return -900;
        }
    }

    /**
     * 日志模型
     */
    public static class Model implements Serializable {
        private String id;
        private String path;
        private LocalDateTime requestDateTime;
        private String requestMethod;
        private String requestCookies;
        private String requestHeaders;
        private String requestBody;
        private String requestQueryParams;
        private String responseBody;
        private String responseHeaders;
        private String responseCookies;
        private LocalDateTime responseDateTime;
        private long intervalDateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public LocalDateTime getRequestDateTime() {
            return requestDateTime;
        }

        public void setRequestDateTime(LocalDateTime requestDateTime) {
            this.requestDateTime = requestDateTime;
        }

        public String getRequestMethod() {
            return requestMethod;
        }

        public void setRequestMethod(String requestMethod) {
            this.requestMethod = requestMethod;
        }

        public String getRequestCookies() {
            return requestCookies;
        }

        public void setRequestCookies(String requestCookies) {
            this.requestCookies = requestCookies;
        }

        public String getRequestHeaders() {
            return requestHeaders;
        }

        public void setRequestHeaders(String requestHeaders) {
            this.requestHeaders = requestHeaders;
        }

        public String getRequestBody() {
            return requestBody;
        }

        public void setRequestBody(String requestBody) {
            this.requestBody = requestBody;
        }

        public String getRequestQueryParams() {
            return requestQueryParams;
        }

        public void setRequestQueryParams(String requestQueryParams) {
            this.requestQueryParams = requestQueryParams;
        }

        public String getResponseBody() {
            return responseBody;
        }

        public void setResponseBody(String responseBody) {
            this.responseBody = responseBody;
        }

        public String getResponseHeaders() {
            return responseHeaders;
        }

        public void setResponseHeaders(String responseHeaders) {
            this.responseHeaders = responseHeaders;
        }

        public String getResponseCookies() {
            return responseCookies;
        }

        public void setResponseCookies(String responseCookies) {
            this.responseCookies = responseCookies;
        }

        public LocalDateTime getResponseDateTime() {
            return responseDateTime;
        }

        public void setResponseDateTime(LocalDateTime responseDateTime) {
            this.responseDateTime = responseDateTime;
        }

        public long getIntervalDateTime() {
            return intervalDateTime;
        }

        public void setIntervalDateTime(long intervalDateTime) {
            this.intervalDateTime = intervalDateTime;
        }

        @Override
        public String toString() {
            return "{"
                    + "\"id\":\""
                    + id + '\"'
                    + ",\"path\":\""
                    + path + '\"'
                    + ",\"requestDateTime\":"
                    + requestDateTime
                    + ",\"requestMethod\":\""
                    + requestMethod + '\"'
                    + ",\"requestCookies\":\""
                    + requestCookies + '\"'
                    + ",\"requestHeaders\":\""
                    + requestHeaders + '\"'
                    + ",\"requestBody\":\""
                    + requestBody + '\"'
                    + ",\"requestQueryParams\":\""
                    + requestQueryParams + '\"'
                    + ",\"responseBody\":\""
                    + responseBody + '\"'
                    + ",\"responseHeaders\":\""
                    + responseHeaders + '\"'
                    + ",\"responseCookies\":\""
                    + responseCookies + '\"'
                    + ",\"responseDateTime\":"
                    + responseDateTime
                    + ",\"intervalDateTime\":"
                    + intervalDateTime
                    + "}";
        }
    }

    /**
     * request
     */
    public static class LogServerHttpRequestDecorator extends ServerHttpRequestDecorator {
        private final ServerHttpRequest request;
        private final Model model;
        public LogServerHttpRequestDecorator(ServerHttpRequest request, Model model) {
            super(request);
            this.request = request;
            this.model = model;
        }

        @Override
        public Flux<DataBuffer> getBody() {
            return super.getBody().map(dataBuffer -> {
                // 读取数据
                final byte[] bytes = new byte[dataBuffer.readableByteCount()];
                dataBuffer.read(bytes);
                // 释放掉内存
                DataBufferUtils.release(dataBuffer);
                return bytes;
            }).defaultIfEmpty(new byte[0])
            .map(bytes -> {
                // 重新写入到返回
                model.setId(request.getId());
                model.setPath(request.getPath().toString());
                model.setRequestDateTime(LocalDateTime.now());
                model.setRequestMethod(Objects.requireNonNull(request.getMethod()).name());
                model.setRequestCookies(request.getCookies().toString());
                model.setRequestHeaders(request.getHeaders().toString());
                model.setRequestQueryParams(request.getQueryParams().toString());

                // 写入指定的请求数据类型
                final List<String> types = request.getHeaders().get(HttpHeaders.CONTENT_TYPE);
                if (types == null || types.size() == 0) {
                    model.setRequestBody("type: unknown, size: " + bytes.length);
                } else {
                    final List<String> rBody = new ArrayList<>();
                    for (String type : types) {
                        if (type.startsWith(MediaType.APPLICATION_JSON_VALUE)
                                || type.startsWith(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) {
                            rBody.add("type: " + type + ", content: " + new String(bytes, StandardCharsets.UTF_8)
                                    .replaceAll("\r", "").replaceAll("\n", ""));
                        } else {
                            rBody.add("type: " + type + ", size: " + bytes.length);
                        }
                    }
                    model.setRequestBody(rBody.toString());
                }
                return DATA_BUFFER_FACTORY.wrap(bytes);
            });
        }
    }

    /**
     * response
     */
    private static class LogServerHttpResponseDecorator extends ServerHttpResponseDecorator {
        private final Model model;
        private final ServerHttpResponse response;
        public LogServerHttpResponseDecorator(ServerHttpResponse response, Model model) {
            super(response);
            this.model = model;
            this.response = response;
        }

        @Override
        public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
            return super.writeWith(DataBufferUtils.join(body).map(dataBuffer -> {
                // 读取数据
                final byte[] bytes = new byte[dataBuffer.readableByteCount()];
                dataBuffer.read(bytes);
                // 释放掉内存
                DataBufferUtils.release(dataBuffer);
                return bytes;
            }).defaultIfEmpty(new byte[0])
            .map(bytes -> {
                // 重新写入到返回
                // 写入指定的请求数据类型
                final List<String> types = response.getHeaders().get(HttpHeaders.CONTENT_TYPE);
                if (types == null || types.size() == 0) {
                    model.setRequestBody("type: unknown, size: " + bytes.length);
                } else {
                    final List<String> rBody = new ArrayList<>();
                    for (String type : types) {
                        if (type.startsWith(MediaType.APPLICATION_JSON_VALUE)) {
                            rBody.add("type: " + type + ", content: " + new String(bytes, StandardCharsets.UTF_8)
                                    .replaceAll("\r", "").replaceAll("\n", ""));
                        } else {
                            rBody.add("type: " + type + ", size: " + bytes.length);
                        }
                    }
                    model.setResponseBody(rBody.toString());
                }
                model.setResponseHeaders(response.getHeaders().toString());
                model.setResponseCookies(response.getCookies().toString());
                model.setResponseDateTime(LocalDateTime.now());
                if (model.getRequestDateTime() != null && model.getResponseDateTime() != null) {
                    final long s = model.getRequestDateTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
                    final long e = model.getResponseDateTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
                    // 写入间隔的时间
                    model.setIntervalDateTime(e - s);
                }
                LOGGER.info(model.toString());
                return DATA_BUFFER_FACTORY.wrap(bytes);
            }));
        }
    }
}
