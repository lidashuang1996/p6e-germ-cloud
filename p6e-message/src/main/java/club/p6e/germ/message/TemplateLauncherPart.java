package club.p6e.germ.message;

import club.p6e.germ.message.exception.TemplateNullPointerException;
import club.p6e.germ.message.mybatis.mapper.MessageTemplateMapper;
import club.p6e.germ.message.cache.ICacheTemplate;
import club.p6e.germ.message.model.MessageTemplateDb;
import club.p6e.germ.message.template.TemplateParserFactory;
import com.p6e.germ.common.utils.P6eCopyUtil;
import com.p6e.germ.common.utils.P6eJsonUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 发送装置部分-模版
 * @author lidashuang
 * @version 1.0
 */
@Component("TemplateLauncherPart")
public class TemplateLauncherPart implements LauncherPart {

    /** 缓存处理对象注入 */
    @Resource
    private ICacheTemplate cache;

    /** 数据库处理对象注入 */
    @Resource
    private MessageTemplateMapper mapper;

    @Override
    public int order() {
        return 100;
    }

    @Override
    public void execute(LauncherPartModel model) throws TemplateNullPointerException {
        final String type = model.getType();
        final int id = model.getTemplateId();
        final String sid = String.valueOf(id);
        LauncherPartModel.Template template = null;
        final String cacheContent = cache.get(sid);
        if (cacheContent == null) {
            final MessageTemplateDb db = mapper.selectById(id);
            if (db != null) {
                template = P6eCopyUtil.run(db, LauncherPartModel.Template.class);
                cache.set(sid, P6eJsonUtil.toJson(template));
            }
        } else {
            template = P6eJsonUtil.fromJson(cacheContent, LauncherPartModel.Template.class);
        }
        // 处理结果
        if (template == null) {
            throw new TemplateNullPointerException(this.getClass() + " not found ID corresponding template data.");
        } else {
            if (template.getType().equals(type)) {
                // 写入数据
                model.setTemplateTitle(template.getTitle());
                model.setTemplateParser(template.getParser());
                model.setTemplateContent(template.getContent());
                // 读取数据生成解析后的模版内容
                final String parser = model.getTemplateParser();
                final String content = model.getTemplateContent();
                final Map<String, String> param = model.getTemplateParam();
                model.setTemplateFullText(TemplateParserFactory.getParser(parser).execute(content, param));
            } else {
                throw new TemplateNullPointerException(this.getClass() + " type does not correspond.");
            }
        }
    }
}
