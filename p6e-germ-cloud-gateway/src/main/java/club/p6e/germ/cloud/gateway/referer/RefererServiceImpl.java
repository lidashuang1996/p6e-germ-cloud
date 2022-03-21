package club.p6e.germ.cloud.gateway.referer;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.List;

/**
 * Referer 服务的默认实现
 * @author lidashuang
 * @version 1.0
 */
@Service
public class RefererServiceImpl implements RefererService {

    @Override
    public @NonNull Mono<ServerWebExchange> execute(@NonNull ServerWebExchange exchange, @NonNull List<String> whiteList) {
        final ServerHttpRequest request = exchange.getRequest();
        System.out.println("-------------");
        request.getHeaders().keySet().forEach(System.out::println);
        System.out.println("-------------");
        final List<String> refererList = request.getHeaders().get(HttpHeaders.REFERER);
        if (refererList != null && refererList.size() > 0) {
            System.out.println("refererList  " + refererList);
            return Mono.just(refererList.get(0)).flatMap(s -> {
                final String referer = s == null ? "" : s;
                for (final String item : whiteList) {
                    if (item.equalsIgnoreCase("*") || referer.startsWith(item)) {
                        return Mono.just(exchange);
                    }
                }
                return Mono.empty();
            });
        }
        return whiteList.contains("*") ? Mono.just(exchange) : Mono.empty();
    }
}
