//package club.p6e.germ.cloud.gateway.jurisdiction;
//
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * 权限的实现
// * @author lidashuang
// * @version 1.0
// */
//@Service
//public class JurisdictionServiceImpl implements JurisdictionService {
//
//    @Resource
//    private JurisdictionCache cache;
//
//    @Override
//    public Mono<ServerWebExchange> execute(ServerWebExchange exchange) {
//        final ServerHttpRequest request = exchange.getRequest();
//        final String httpPath = request.getPath().value();
//        final String httpMethod = request.getMethodValue();
//
//        System.out.println(httpPath + "   " + httpMethod);
//
//        final List<String> users = request.getHeaders().get(HKSI_USER_HEADER);
//        if (users != null && users.size() > 0) {
//            return Mono.just(users.get(0)).flatMap(content ->
//                    Mono.just(JsonUtil.fromJson(content, AuthModel.class))).flatMap(authModel -> {
//                System.out.println("authModel  " + authModel);
//                        // 读取用户的权限
//                        final List<String> uJurisdictions = authModel.getJurisdictions();
//                System.out.println("uJurisdictions " + uJurisdictions);
//                        // 去搜索路径相关的权限数据
//                        return cache.getPathJurisdictions((httpPath + "." + httpMethod)).flatMap(pJurisdictions ->
//                                // 判断是否验证通过
//                                verification(pJurisdictions, uJurisdictions) ? Mono.just(exchange) : Mono.empty());
//                    });
//        } else {
//            return Mono.empty();
//        }
//    }
//
//    /**
//     * 验证
//     * @param cj 需要的数据
//     * @param uj 存在的数据
//     * @return 验证结果
//     */
//    private boolean verification(List<String> cj, List<String> uj) {
//        System.out.println(" gg  " + cj + " hh " + uj);
//        for (final String j1 : cj) {
//            for (String j2 : uj) {
//                if (j1.equals(j2)) {
//                    System.out.println(" gg true " + cj + " hh " + uj);
//                    return true;
//                }
//            }
//        }
//        System.out.println(" gg false " + cj + " hh " + uj);
//        return false;
//    }
//}
