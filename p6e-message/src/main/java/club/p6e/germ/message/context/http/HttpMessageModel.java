package club.p6e.germ.message.context.http;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 参数模型
 * @author lidashuang
 * @version 1.0
 */
@Data
public class HttpMessageModel {
    private List<String> recipients;
    private Integer templateId;
    private Map<String, String> templateParams = new HashMap<>();
}
