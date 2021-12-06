package com.dyy.p6e.germ.file2.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author lidashuang
 * @version 1.0
 */
@Configuration
public class Cros implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        final ServerHttpRequest request = exchange.getRequest();
        final ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().add("Access-Control-Allow-Origin", "*");
        response.getHeaders().add("Access-Control-Allow-Methods", "PUT, POST, GET, DELETE, OPTIONS");
        response.getHeaders().add("Access-Control-Max-Age", "3600");
        // 设置允许 cookie
        response.getHeaders().add("Access-Control-Allow-Credentials", "true");
        response.getHeaders().add("Access-Control-Allow-Headers", "Authorization, Content-Type, Depth, "
                + "User-Agent, X-File-Size, X-Requested-With, X-Requested-By, If-Modified-Since, "
                + "X-File-Name, X-File-Type, Cache-Control, Origin, Client");

        if (Objects.requireNonNull(request.getMethod()).name().equals(HttpMethod.OPTIONS.name())) {
            response.setStatusCode(HttpStatus.OK);
            return Mono.empty();
        }

        return chain.filter(exchange);
    }
}
