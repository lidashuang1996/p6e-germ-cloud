package club.p6e.germ.cloud.gateway.error;


import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
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

import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class CustomErrorWebExceptionHandler extends DefaultErrorWebExceptionHandler implements Ordered {



    public CustomErrorWebExceptionHandler(ServerCodecConfigurer serverCodecConfigurer,
                                          ErrorAttributes errorAttributes,
                                          ResourceProperties resourceProperties,
                                          ServerProperties serverProperties,
                                          ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, serverProperties.getError(),  applicationContext);
        this.setMessageReaders(serverCodecConfigurer.getReaders());
        this.setMessageWriters(serverCodecConfigurer.getWriters());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return r -> Mono.just((HandlerFunction<ServerResponse>) request -> {
            Map<String, Object> error = this.getErrorAttributes(request, false);
            error.put("code", error.get("status"));
            error.put("message", error.get("error"));
            error.remove("error");
            error.remove("status");
            return ServerResponse.status(HttpStatus.OK).contentType(
                    MediaType.APPLICATION_JSON).body(Mono.just(error), Map.class);
        });
    }

    @Override
    public int getOrder() {
        return -1000;
    }
}
