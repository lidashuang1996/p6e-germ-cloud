package club.p6e.germ.message.template;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component("TemplateDefaultParser")
public class TemplateDefaultParser implements TemplateParserInterface {

    /** 默认名称 */
    protected static final String SYSTEM_DEFAULT_NAME = "__default__";

    @Override
    public String name() {
        return SYSTEM_DEFAULT_NAME;
    }

    @Override
    public String execute(String content, Map<String, String> map) {
        if (content == null || content.isEmpty()) {
            return content;
        } else {
            return translator(content, map);
        }
    }

    /**
     * 字符变量
     */
    private static final char CHAR_HEAD = '$';
    private static final char CHAR_LEFT = '{';
    private static final char CHAR_RIGHT = '}';

    /**
     * 解析模版数据
     * @param content 需要解析的内容
     * @param map 参数对象
     * @return 解析后的内容
     */
    private String translator(String content, Map<String, String> map) {
        int mark = -1;
        final StringBuilder sb = new StringBuilder();
        final StringBuilder name = new StringBuilder();
        for (int i = 0; i < content.length(); i++) {
            final char ch = content.charAt(i);
            if (i + 1 < content.length() && CHAR_HEAD == ch && CHAR_LEFT == content.charAt(i + 1)) {
                if (mark >= 0) {
                    sb.append(CHAR_HEAD).append(CHAR_LEFT).append(name);
                    name.delete(0, name.length());
                }
                mark = i++;
            } else if (mark >= 0) {
                if (CHAR_RIGHT == content.charAt(i)) {
                    mark = -1;
                    final String v = map.get(name.toString());
                    if (v == null) {
                        sb.append(CHAR_HEAD).append(CHAR_LEFT).append(name).append(CHAR_RIGHT);
                    } else {
                        sb.append(v);
                    }
                } else {
                    name.append(ch);
                }
            } else {
                sb.append(ch);
            }
        }
        if (mark >= 0) {
            sb.append(CHAR_HEAD).append(CHAR_LEFT).append(name);
        }
        return sb.toString();
    }
}
