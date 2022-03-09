//package club.p6e.germ.cloud.gateway.jurisdiction;
//
//import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
//import org.springframework.data.redis.core.ScanOptions;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//import javax.annotation.Resource;
//import java.nio.ByteBuffer;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * 权限缓存服务的实现
// * @author lidashuang
// * @version 1.0
// */
//@Component
//public class JurisdictionRedisCache implements JurisdictionCache {
//
//    private static long CACHE_DATE_TIME = 0;
//    private static final Map<String, List<String>> CACHE = new HashMap<>();
//
//    private final static AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);
//
//    @Resource
//    private ReactiveStringRedisTemplate reactiveStringRedisTemplate;
//
//    @Override
//    public Mono<List<String>> getPathJurisdictions(String data) {
//        if (System.currentTimeMillis() - CACHE_DATE_TIME > 1000 * 60 * 5) {
//            if (ATOMIC_INTEGER.incrementAndGet() == 1) {
//                return reactiveStringRedisTemplate.execute(
//                        connection -> connection.keyCommands().scan(
//                                ScanOptions.scanOptions().match(JURISDICTION_PATH_PREFIX + "*").count(1000).build()).flatMap(
//                                        byteBuffer -> {
//                                            final String key = new String(byteBuffer.array(), StandardCharsets.UTF_8);
//                                            return connection.listCommands().lRange(byteBuffer, 0, -1).collectList().flatMap(byteBuffers -> {
//                                                final List<String> list = new ArrayList<>();
//                                                for (final ByteBuffer bb : byteBuffers) {
//                                                    final byte[] bytes = bb.array();
//                                                    list.add(new String(bytes, StandardCharsets.UTF_8));
//                                                    bb.clear();
//                                                }
//                                                final Map<String, List<String>> map = new HashMap<>();
//                                                System.out.println(key.substring(JURISDICTION_PATH_PREFIX.length()));
//                                                map.put(key.substring(JURISDICTION_PATH_PREFIX.length()), list);
//                                                return Mono.just(map);
//                                            });
//                                        })).collectList().flatMap(maps -> {
//                                            synchronized (CACHE) {
//                                                CACHE.clear();
//                                                for (final Map<String, List<String>> map : maps) {
//                                                    CACHE.putAll(map);
//                                                }
//                                                System.out.println(CACHE);
//                                            }
//                                            CACHE_DATE_TIME = System.currentTimeMillis();
//                                            ATOMIC_INTEGER.decrementAndGet();
//                                            return Mono.just(getPathJurisdictionsCacheData(data));
//                                        });
//            }
//            ATOMIC_INTEGER.decrementAndGet();
//        }
//        return Mono.just(getPathJurisdictionsCacheData(data));
//    }
//
//    /**
//     * 从缓存中读取权限数据
//     * @param data 路径.方法
//     * @return 读取的数据
//     */
//    private List<String> getPathJurisdictionsCacheData(String data) {
//        final List<String> result = new ArrayList<>();
//        final int index = data.lastIndexOf(".");
//        final String path = data.substring(0, index);
//        final String method = data.substring(index + 1);
//
//        System.out.println("::1:: " + path + "  " + method);
//
//        for (final String key : CACHE.keySet()) {
//            final int kIndex = key.lastIndexOf(".");
//            final String kPath = key.substring(0, kIndex);
//            final String kMethod = key.substring(kIndex + 1);
//
//            System.out.println("::2:: " + kPath + "  " + kMethod);
//
//            if ((kMethod.equals(method) || kMethod.equals("ALL"))
//                    && (verificationPath(path, kPath))) {
//                result.addAll(CACHE.get(key));
//            }
//        }
//        System.out.println("放回数据 ................... ");
//        return result;
//    }
//
//    /**
//     * 验证路径是否匹配
//     * @param path 路径
//     * @param sourcePath 源路径
//     * @return 验证结果
//     */
//    private boolean verificationPath(String path, String sourcePath) {
//        final String[] ps = path.split("/");
//        final String[] sps = sourcePath.split("/");
//        if (ps.length == sps.length) {
//            for (int i = 0; i < ps.length; i++) {
//                if (!"*".equals(sps[i]) && !ps[i].equals(sps[i])) {
//                    return false;
//                }
//            }
//            return true;
//        }
//        return false;
//    }
//}
