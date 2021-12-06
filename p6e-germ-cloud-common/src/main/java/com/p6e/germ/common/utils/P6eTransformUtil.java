package com.p6e.germ.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public final class P6eTransformUtil {

    private static final int M = 2;
    private static final char CHAR1 = '$';
    private static final char CHAR2 = '{';
    private static final char CHAR3 = '}';

    public static String template(String source, String ...dictionary) {
        if (dictionary == null || dictionary.length % M != 0) {
            throw new RuntimeException();
        } else {
            final Map<String, String> dMap = new HashMap<>(dictionary.length / M);
            for (int i = 0; i < dictionary.length; i++) {
                dMap.put(dictionary[i], dictionary[++i]);
            }
            return template(source, dMap);
        }
    }

    public static String template(String source, Map<String, String> dictionary) {
        if (source == null || source.isEmpty()) {
            return source;
        } else {
            StringBuilder name = null;
            final int sl = source.length();
            final StringBuilder result = new StringBuilder();
            for (int i = 0; i < sl; i++) {
                final char ch = source.charAt(i);
                if (CHAR1 == ch && i + 1 < sl
                        && CHAR2 == source.charAt(i + 1)) {
                    i++;
                    if (name == null) {
                        name = new StringBuilder();
                    } else {
                        result.append(CHAR1);
                        result.append(CHAR2);
                        result.append(name);
                        name.delete(0, name.length());
                    }
                } else if (name != null) {
                    if (CHAR3 == source.charAt(i)) {
                        final String key = name.toString();
                        final String value = dictionary.get(key);
                        result.append(value == null ? key : value);
                        name = null;
                    } else {
                        name.append(ch);
                    }
                } else {
                    result.append(ch);
                }
            }
            return result.toString();
        }
    }
}
