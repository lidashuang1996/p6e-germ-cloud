package club.p6e.germ.jurisdiction.mybatis;

import club.p6e.germ.jurisdiction.config.Config;
import club.p6e.germ.jurisdiction.model.JurisdictionBaseDb;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static club.p6e.germ.jurisdiction.mybatis.DataSourceConfig.HEAD;

/**
 * MyBatis 拦截器
 * 1. 处理默认参数
 * 2. 设置查询的最大值
 *
 * 强制定义所有到 db 层做 CRUD 的操作，只能用对象且用 "DB" 作为参数的别名且必须继承基础的 db 类
 * 这样做的目的是为了更好的管理和操作 db 层的数据
 *
 * 1. mybatis-config.xml 文件配置
 * 2. spring boot 注解注入方式配置 @Component
 * 3. 采用 spring boot 的 yml 配置文件配置
 * 但是三者只能选择其中一个，不能重复配置，否者会重复加载拦截器
 *
 * @author lidashuang
 * @version 1.0
 */
@Component(HEAD + "PagingPlugin")
@Intercepts(@Signature(type = Executor.class, method = "query",
        args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }))
public class PagingPlugin implements Interceptor {

    /**
     * 默认的查询页码数
     *
     * 这里是可以采用注解方式注入参数
     * 这样就可以读取 spring uml 配置文件信息了
     * 也就是表示可以在配置文件中，配置这些你可能需要动态配置的参数了
     */
    private final int DEFAULT_PAGE;
    /** 默认的查询长度 */
    private final int DEFAULT_SIZE;
    /** 默认的查询最大长度 */
    private final int DEFAULT_MAX_SIZE;

    @Autowired
    public PagingPlugin(Config config) {
        DEFAULT_PAGE = config.getDataSource().getDefaultPage();
        DEFAULT_SIZE = config.getDataSource().getDefaultSize();
        DEFAULT_MAX_SIZE = config.getDataSource().getDefaultMaxSize();
    }

    /**
     * 拦截器的核心方法
     * @param invocation 代理的对象
     * @return 继续执行的代理对象
     * @throws Throwable 拦截器可能出现的错误
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取参数
        final Object param = invocation.getArgs()[1];
        if (param instanceof JurisdictionBaseDb) {
            // 初始化一下分页信息
            execute((JurisdictionBaseDb) param);
        }
        return invocation.proceed();
    }

    /**
     * 初始化分页数据的方法
     * @param db db 操作的请求参数
     */
    private void execute(JurisdictionBaseDb db) {
        if (db.getPage() == null || db.getPage() <= 0) {
            db.setPage(DEFAULT_PAGE);
        }
        if (db.getSize() == null || db.getSize() < 0) {
            db.setSize(DEFAULT_SIZE);
        }
        if (db.getSize() > DEFAULT_MAX_SIZE) {
            db.setSize(DEFAULT_MAX_SIZE);
        }
        db.setOffset((db.getPage() - 1) * db.getSize());
    }
}

