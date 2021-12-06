//package com.p6e.germ.common.utils;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author lidashuang
// * @version 1.0
// */
//public class P6eIpUtil {
//
//    /**
//     *
//     * @return
//     */
//    public static String get(HttpServletRequest request) {
//        String ip = request.getHeader("x-forwarded-for");
//        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
//            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
//            if (ip.contains(",")) {
//                ip = ip.split(",")[0];
//            }
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_CLIENT_IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("X-Real-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        if (ip == null) {
//            ip = "0.0.0.0";
//        }
//        return ip;
//    }
//
//    private static final String DEF = "0.0.0.0";
//    private static final String CHAR_F = "*";
//    private static final String CHAR_H = ",";
//    private static final int DEF_LENGTH = 4;
//
//    public static boolean isInPool(String ip, String config) {
//        if (config == null || config.isEmpty()) {
//            throw new RuntimeException();
//        }
//        final String[] configs = config.split("\\.");
//        configs[0] = configs[0].replace(" ", "");
//        configs[1] = configs[1].replace(" ", "");
//        configs[2] = configs[2].replace(" ", "");
//        configs[3] = configs[3].replace(" ", "");
//        if (DEF_LENGTH != configs.length
//                || configs[0].isEmpty()
//                || configs[1].isEmpty()
//                || configs[2].isEmpty()
//                || configs[3].isEmpty()) {
//            throw new RuntimeException();
//        }
//
//        if (ip == null || ip.isEmpty()) {
//            return false;
//        }
//        final String[] ips = ip.split("\\.");
//        ips[0] = ips[0].replace(" ", "");
//        ips[1] = ips[1].replace(" ", "");
//        ips[2] = ips[2].replace(" ", "");
//        ips[3] = ips[3].replace(" ", "");
//        if (DEF_LENGTH != ips.length
//                || ips[0].isEmpty()
//                || ips[1].isEmpty()
//                || ips[2].isEmpty()
//                || ips[3].isEmpty()) {
//            return false;
//        }
//        if (DEF.equals(config)) {
//            return true;
//        }
//
//        // 每个区域进行判断
//        return isInTermPool(ips[0], configs[0])
//                && isInTermPool(ips[1], configs[1])
//                && isInTermPool(ips[2], configs[2])
//                && isInTermPool(ips[3], configs[3]);
//    }
//
//    private static boolean isInTermPool (String ipTerm, String configTerm) {
//        try {
//            final int ipData = Integer.parseInt(ipTerm);
//            if (ipData < 0 || ipData > 255) {
//                return false;
//            }
//            if (CHAR_F.equals(configTerm)) {
//                return true;
//            }
//            final String[] cs = configTerm.split(",");
//            for (String c : cs) {
//                final String[] ds = c.split("/");
//                if (ds.length == 1) {
//                    final int d = Integer.parseInt(ds[0]);
//                    if (d < 0 || d > 255) {
//                        throw new RuntimeException();
//                    }
//                    return ipData == d;
//                } else if (ds.length == 2) {
//                    final int s = Integer.parseInt(ds[0]);
//                    final int e = Integer.parseInt(ds[1]);
//                    if (s < 0 || s > 255) {
//                        throw new RuntimeException();
//                    }
//                    if (e < 0 || e > 255) {
//                        throw new RuntimeException();
//                    }
//                    return s <= ipData && ipData <= e;
//                } else {
//                    throw new RuntimeException();
//                }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return false;
//    }
//
//    public static void main(String[] args) {
//        System.out.println(isInPool("123.22.22.0", "123.22/50.*.0"));
//    }
//
//}
