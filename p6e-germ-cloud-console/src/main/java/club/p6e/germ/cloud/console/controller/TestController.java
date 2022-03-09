package club.p6e.germ.cloud.console.controller;

import club.p6e.germ.cloud.console.controller.support.ApiResultContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${server.port}")
    private String a;

    @RequestMapping("/")
    public ApiResultContext aaa() {
        return ApiResultContext.build("xxxxxxxxxxxxxxxxxxxxxx  " + a);
    }

}
