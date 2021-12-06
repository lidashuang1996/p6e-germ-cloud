package club.p6e.germ.message.limit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public abstract class LimiterAbstract implements LimiterInterface {

    /** 注入日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(LimiterAbstract.class);

    /** 日期时间格式化对象 */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    /**
     * 执行限流规则判断
     * @param limit 限流规则
     * @param list 限流数据列表
     * @return 是否被限流
     */
    protected boolean dispose(String limit, List<String> list) {
        try {
            if (limit == null
                    || list == null
                    || "".equals(limit)
                    || list.size() == 0) {
                return true;
            } else {
                final String[] ls = limit.split("/");
                if (ls.length == 0) {
                    return true;
                } else {
                    final int x1 = 0, x2 = 1, x3 = 2;
                    final int t1 = 1, t5= 5, t15 = 15;
                    boolean bool1 = false, bool2 = false, bool3 = false;
                    if (Integer.parseInt(ls[x1]) == 0 || Integer.parseInt(ls[x1]) > getLimitCount(t1, list)) {
                        bool1 = true;
                    }
                    if (ls.length > x2) {
                        if (Integer.parseInt(ls[x2]) == 0 || Integer.parseInt(ls[x2]) > getLimitCount(t5, list)) {
                            bool2 = true;
                        }
                    } else {
                        bool2 = true;
                        bool3 = true;
                    }
                    if (ls.length > x3) {
                        if (Integer.parseInt(ls[x3]) == 0 || Integer.parseInt(ls[x3]) > getLimitCount(t15, list)) {
                            bool3 = true;
                        }
                    } else {
                        bool3 = true;
                    }
                    return bool1 && bool2 && bool3;
                }
            }
        } catch (Exception e) {
            LOGGER.warn("limit dispose error, " + e.getMessage());
            return false;
        }
    }

    /**
     * 获取限流的长度
     * @param time 倒计时长度
     * @param list 匹配的列表
     * @return 获取倒计时长度的总数
     */
    private int getLimitCount(int time, List<String> list) {
        int result = 0;
        final int dLength = 14;
        final long dateTime = Long.parseLong(DATE_TIME_FORMATTER.format(LocalDateTime.now().plusMinutes(-time)));
        for (final String item : list) {
            if (item.length() > dLength) {
                final String it = item.substring(item.length() - dLength);
                if (Long.parseLong(it) > dateTime) {
                    result++;
                }
            }
        }
        return result;
    }

}
