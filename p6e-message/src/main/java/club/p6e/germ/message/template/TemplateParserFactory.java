package club.p6e.germ.message.template;

import com.p6e.germ.common.utils.P6eSpringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public final class TemplateParserFactory {

    /** 默认名称 */
    private static final String DEFAULT_NAME = "default";
    private static final String SYSTEM_DEFAULT_NAME = "__default__";
    /** 处理器 */
    private static final Map<String, TemplateParserInterface> PARSER = new HashMap<>();

    /** 初始化一次 */
    public synchronized static void load() {
        PARSER.clear();
        final Map<String, TemplateParserInterface> map = P6eSpringUtil.getBeans(TemplateParserInterface.class);
        for (final TemplateParserInterface template : map.values()) {
            PARSER.put(template.name(), template);
        }
    }

    /**
     * 获取处理器
     * @param name 名称
     * @return 处理器对象
     */
    public static TemplateParserInterface getParser(String name) {
        return PARSER.get(name) == null ? PARSER.get(DEFAULT_NAME) == null ?
                PARSER.get(SYSTEM_DEFAULT_NAME) : PARSER.get(DEFAULT_NAME) : PARSER.get(name);
    }

}
