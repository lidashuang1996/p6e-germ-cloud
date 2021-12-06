package club.p6e.germ.message.task;

import club.p6e.germ.message.LauncherPartModel;
import club.p6e.germ.message.model.MessageGroupDb;
import club.p6e.germ.message.model.MessageTemplateDb;
import club.p6e.germ.message.mybatis.mapper.MessageGroupMapper;
import club.p6e.germ.message.mybatis.mapper.MessageTemplateMapper;
import club.p6e.germ.message.cache.ICacheGroup;
import club.p6e.germ.message.cache.ICacheTemplate;
import com.p6e.germ.common.utils.P6eCopyUtil;
import com.p6e.germ.common.utils.P6eJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
@Configuration
@EnableScheduling
public class DbToCacheTask {

    /** 数据库长度 */
    private static final int DB_SIZE = 100;
    /** 注入日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(DbToCacheTask.class);

    /** 注入缓存对象 */
    private final ICacheGroup groupCache;
    private final ICacheTemplate templateCache;
    /** 注入数据库对象 */
    private final MessageGroupMapper groupMapper;
    private final MessageTemplateMapper templateMapper;

    @Autowired
    public DbToCacheTask(ICacheGroup groupCache, ICacheTemplate templateCache,
                         MessageGroupMapper groupMapper, MessageTemplateMapper templateMapper) {
        this.groupCache = groupCache;
        this.templateCache = templateCache;
        this.groupMapper = groupMapper;
        this.templateMapper = templateMapper;
    }

    @Scheduled(initialDelay = 10000, fixedRate = 1800000)
    public void run() {
        LOGGER.info("start run task [ DbToCacheTask ].");
        // 组数据缓存
        int groupPage = 1;
        List<MessageGroupDb> groups;
        final MessageGroupDb groupDb = new MessageGroupDb();
        groupDb.setSize(DB_SIZE);
        do {
            groupDb.setPage(groupPage++);
            groups = groupMapper.selectComplete(groupDb);

            if (groups != null && groups.size() > 0) {
                for (final MessageGroupDb group : groups) {
                    groupCache.set(String.valueOf(group.getId()),
                            P6eJsonUtil.toJson(P6eCopyUtil.run(group, LauncherPartModel.Group.class)));
                }
            }

        } while (groups != null && groups.size() == DB_SIZE);

        // 模版数据缓存
        int templatePage = 1;
        List<MessageTemplateDb> templates;
        final MessageTemplateDb templateDb = new MessageTemplateDb();
        templateDb.setSize(DB_SIZE);
        do {
            templateDb.setPage(templatePage++);
            templates = templateMapper.select(templateDb);

            if (templates != null && templates.size() > 0) {
                for (final MessageTemplateDb template : templates) {
                    templateCache.set(String.valueOf(template.getId()),
                            P6eJsonUtil.toJson(P6eCopyUtil.run(template, LauncherPartModel.Template.class)));
                }
            }
        } while (templates != null && templates.size() == DB_SIZE);


        LOGGER.info("end run task [ DbToCacheTask ].");
    }

}
