package club.p6e.germ.cloud.gateway.jurisdiction;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
@Service(value = "JurisdictionServiceDefaultImpl")
public class JurisdictionServiceDefaultImpl implements JurisdictionService {

    @Override
    public Mono<Void> execute(ServerWebExchange exchange, GatewayFilterChain chain) {
        final ServerHttpRequest request = exchange.getRequest();
        final String path = request.getPath().toString();
        final List<String> users = request.getHeaders().get(P6E_USER_HEADER);
        if (users != null) {
            for (final String user : users) {
                // 权限组
                //
                System.out.println("---------------");
                System.out.println("用户 --》 " + user);
                System.out.println("路径 --》" + path);
                System.out.println("判断权限");
                System.out.println("---------------");

                final double d = Math.random();
                if (d < 0.5) {
                    return chain.filter(exchange);
                }
            }
        }
        return JurisdictionService.exceptionResult(exchange);
    }
}
