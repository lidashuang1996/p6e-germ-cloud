package club.p6e.germ.cloud.gateway.log;

import club.p6e.germ.cloud.common.utils.P6eJsonUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.lang.NonNull;
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
 * Log 日志对象过滤器
 * @author lidashuang
 * @version 1.0
 */
@Component
public class P6eLogGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    /** 顺序 */
    private static final int ORDER = -1900;

    /** 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(P6eLogGatewayFilterFactory.class);

    /** DATA BUFFER 工厂 */
    private static final DataBufferFactory DATA_BUFFER_FACTORY = new DefaultDataBufferFactory();

    @Override
    public GatewayFilter apply(Object config) {
        return new CustomGatewayFilter();
    }

    /**
     * 自定义过滤器
     */
    private static class CustomGatewayFilter implements GatewayFilter, Ordered {

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            // 创建日志模型
            final Model model = new Model();
            // 请求日志处理
            final ServerHttpRequest request = new LogServerHttpRequestDecorator(exchange.getRequest(), model);
            // 结果日志处理
            final ServerHttpResponse response = new LogServerHttpResponseDecorator(exchange.getResponse(), model);
            // 继续执行
            return chain.filter(exchange.mutate().request(request).response(response).build());
        }

        @Override
        public int getOrder() {
            return ORDER;
        }

    }

    /**
     * 日志模型
     */
    @Data
    @Accessors(chain = true)
    private static class Model implements Serializable {
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

        @Override
        public String toString() {
            return P6eJsonUtil.toJson(this);
        }
    }

    /**
     * 日志 Request 解码器
     */
    private static class LogServerHttpRequestDecorator extends ServerHttpRequestDecorator {
        /** 模型对象 */
        private final Model model;
        /** 返回的 Request 对象 */
        private final ServerHttpRequest request;

        /**
         * 构造方法初始化
         * @param request Request 对象
         * @param model 对象
         */
        public LogServerHttpRequestDecorator(ServerHttpRequest request, Model model) {
            super(request);
            this.model = model;
            this.request = request;

            // 初始化写入数据
            model.setId(request.getId());
            model.setPath(request.getPath().toString());
            model.setRequestDateTime(LocalDateTime.now());
            model.setRequestMethod(Objects.requireNonNull(request.getMethod()).name());
            model.setRequestCookies(request.getCookies().toString());
            model.setRequestHeaders(request.getHeaders().toString());
            model.setRequestQueryParams(request.getQueryParams().toString());
        }

        @Override
        public @NonNull Flux<DataBuffer> getBody() {
            return super.getBody().map(dataBuffer -> {
                // 读取数据
                final byte[] bytes = new byte[dataBuffer.readableByteCount()];
                dataBuffer.read(bytes);
                // 释放掉内存
                DataBufferUtils.release(dataBuffer);
                return bytes;
            }).defaultIfEmpty(new byte[0])
            .map(bytes -> {
                // 写入指定的请求数据类型
                final List<String> types = request.getHeaders().get(HttpHeaders.CONTENT_TYPE);
                if (types == null || types.size() == 0) {
                    model.setRequestBody("type: unknown, size: " + bytes.length);
                } else {
                    final List<String> rBody = new ArrayList<>();
                    for (final String type : types) {
                        // 如果请求的类型为 JSON / FORM 那么就打印全部的信息
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
     * 日志 Response 解码器
     */
    private static class LogServerHttpResponseDecorator extends ServerHttpResponseDecorator {
        /** 模型对象 */
        private final Model model;
        /** 返回的 Response 对象 */
        private final ServerHttpResponse response;

        /**
         * 构造方法初始化
         * @param response Response 对象
         * @param model 对象
         */
        public LogServerHttpResponseDecorator(ServerHttpResponse response, Model model) {
            super(response);
            this.model = model;
            this.response = response;
        }

        @Override
        public @NonNull Mono<Void> writeWith(@NonNull Publisher<? extends DataBuffer> body) {
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
                    for (final String type : types) {
                        // 如果是 APPLICATION_JSON 类型返回数据内容
                        // if (type.startsWith(MediaType.APPLICATION_JSON_VALUE)) {
                        //     rBody.add("type: " + type + ", content: " + new String(bytes, StandardCharsets.UTF_8)
                        //             .replaceAll("\r", "").replaceAll("\n", ""));
                        // }
                        // 写入你想要打印的日志类型
                        // 通用返回结果类型
                        rBody.add("type: " + type + ", size: " + bytes.length);
                    }
                    model.setResponseBody(rBody.toString());
                }
                model.setResponseDateTime(LocalDateTime.now());
                model.setResponseHeaders(response.getHeaders().toString());
                model.setResponseCookies(response.getCookies().toString());
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
