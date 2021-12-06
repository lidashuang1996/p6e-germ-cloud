package club.p6e.germ.message;

import club.p6e.germ.message.destination.DestinationFactory;
import club.p6e.germ.message.template.TemplateParserFactory;
import com.p6e.germ.common.P6e;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class EnableP6eMessageApplicationRunner implements ApplicationRunner {

    private final ApplicationContext application;

    public EnableP6eMessageApplicationRunner(ApplicationContext application) {
        this.application = application;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        P6e.init(application);
        /* 加载终点处理器 */
        DestinationFactory.load();
        /* 加载模版的处理器 */
        TemplateParserFactory.load();
        /* 初始化线程池 */
        DestinationFactory.initExecutor();
        /* 关闭线程池 */
        Runtime.getRuntime().addShutdownHook(new Thread(DestinationFactory::shutdownExecutor));
    }

}
