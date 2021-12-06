package club.p6e.germ.message.template;

import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface TemplateParserInterface {

    /**
     * 处理名称
     * @return 处理名称
     */
    public String name();

    /**
     * 执行模版分析
     * @param content 内容数据
     * @param map 参数对象
     * @return 解析后的数据
     */
    public String execute(String content, Map<String, String> map);

}
