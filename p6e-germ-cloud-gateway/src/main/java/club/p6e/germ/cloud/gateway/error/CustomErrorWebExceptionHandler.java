package club.p6e.germ.cloud.gateway.error;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Global Exception 全局异常处理
 * @author lidashuang
 * @version 1.0
 */
@Component
public class CustomErrorWebExceptionHandler extends DefaultErrorWebExceptionHandler implements Ordered {

    /** 顺序（越小越先被执行） */
    private static final int ORDER = -2000;

    /** 格式化时间对象 */
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );

    public CustomErrorWebExceptionHandler(ServerCodecConfigurer serverCodecConfigurer,
                                          ErrorAttributes errorAttributes,
                                          WebProperties webProperties,
                                          ApplicationContext applicationContext) {
        super(errorAttributes, webProperties.getResources(), null, applicationContext);
        this.setMessageReaders(serverCodecConfigurer.getReaders());
        this.setMessageWriters(serverCodecConfigurer.getWriters());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return r -> Mono.just((HandlerFunction<ServerResponse>) request -> {
            final Map<String, Object> error = this.getErrorAttributes(request, ErrorAttributeOptions.defaults());
            error.put("code", error.get("status"));
            error.put("message", error.get("error"));
            int status = 500;
            if (error.get("status") != null) {
                status = Double.valueOf(String.valueOf(error.get("status"))).intValue();
            }
            final Object date = error.get("timestamp");
            if (date instanceof Date) {
                error.remove("timestamp");
                error.put("date", SIMPLE_DATE_FORMAT.format(date));
            }
            error.remove("error");
            error.remove("status");
            return ServerResponse.status(HttpStatus.valueOf(status)).contentType(MediaType.APPLICATION_JSON).body(Mono.just(error), Map.class);
        });
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
