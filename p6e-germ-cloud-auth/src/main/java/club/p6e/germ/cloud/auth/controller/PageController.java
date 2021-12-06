package club.p6e.germ.cloud.auth.controller;

import club.p6e.germ.cloud.auth.P6eCloudAuthProperties;
import club.p6e.germ.cloud.auth.controller.support.ApiResultModel;
import club.p6e.germ.cloud.auth.controller.support.model.VoucherModel;
import club.p6e.germ.cloud.auth.infrastructure.model.ErrorModel;
import club.p6e.germ.cloud.auth.application.service.VoucherService;
import club.p6e.germ.cloud.auth.controller.support.BaseController;
import com.p6e.germ.common.utils.P6eJsonUtil;
import com.p6e.germ.common.utils.P6eResourceUtil;
import com.p6e.germ.common.utils.P6eTransformUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
@Controller
@RequestMapping("/")
public class PageController extends BaseController {

    /** 配置文件 */
    private final P6eCloudAuthProperties properties;

    /** 我们页面 */
    private final String mePage;
    private final Map<String, String> mePageContent;

    /** 登录页面 */
    private final String loginPage;
    private final Map<String, String> loginPageContent;

    /** 注册页面 */
    private final String registerPage;
    private final Map<String, String> registerPageContent;

    /** 忘记密码页面 */
    private final String forgetPasswordPage;
    private final Map<String, String> forgetPasswordPageContent;

    /** 注入凭证服务 */
    @Resource
    private VoucherService voucherService;

    /**
     * 初始化页面配置文件内容
     */
    @Autowired
    public PageController(P6eCloudAuthProperties properties) {
        try {
            this.properties = properties;
            this.mePageContent = properties.getMe().getContent();
            this.loginPageContent = properties.getLogin().getContent();
            this.registerPageContent = properties.getRegister().getContent();
            this.forgetPasswordPageContent = properties.getForgetPassword().getContent();
            this.mePage = P6eResourceUtil.getResourceContent(properties.getLogin().getFile());
            this.loginPage = P6eResourceUtil.getResourceContent(properties.getLogin().getFile());
            this.registerPage = P6eResourceUtil.getResourceContent(properties.getRegister().getFile());
            this.forgetPasswordPage = P6eResourceUtil.getResourceContent(properties.getForgetPassword().getFile());

            LOGGER.info("mePage --> " + this.mePage);
            LOGGER.info("loginPage --> " + this.loginPage);
            LOGGER.info("registerPage --> " + this.registerPage);
            LOGGER.info("forgetPasswordPage --> " + this.forgetPasswordPage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "${p6e.cloud.auth.me.page}")
    public void me(HttpServletResponse response) {
        // 验证是否开启服务
        if (!properties.getMe().isOpen()) {
            writeContent(response, P6eJsonUtil.toJson(ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE)));
        }
        // 读取配置文件的属性
        final Map<String, String> content = new HashMap<>(mePageContent.size() + 1);
        for (final String key : mePageContent.keySet()) {
            content.put(key, mePageContent.get(key));
        }
        // 写入特定的数据
        content.put("__PAGE__", "ME");
        writeContent(response, P6eTransformUtil.template(mePage, content));
    }

    @GetMapping(value = "${p6e.cloud.auth.login.page}")
    public void login(HttpServletResponse response) {
        // 验证是否开启服务
        if (!properties.getLogin().isOpen()) {
            writeContent(response, P6eJsonUtil.toJson(ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE)));
        }
        // 读取配置文件的属性
        final Map<String, String> content = new HashMap<>(loginPageContent.size() + 2);
        for (final String key : loginPageContent.keySet()) {
            content.put(key, loginPageContent.get(key));
        }
        write(response, content, "LOGIN", loginPage);
    }

    @GetMapping(value = "${p6e.cloud.auth.register.page}")
    public void register(HttpServletResponse response) {
        // 验证是否开启服务
        if (!properties.getRegister().isOpen()) {
            writeContent(response, P6eJsonUtil.toJson(ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE)));
        }
        // 读取配置文件的属性
        final Map<String, String> content = new HashMap<>(registerPageContent.size() + 2);
        for (final String key : registerPageContent.keySet()) {
            content.put(key, registerPageContent.get(key));
        }
        write(response, content, "REGISTER", registerPage);
    }

    @GetMapping(value = "${p6e.cloud.auth.forget-password.page}")
    public void forgetPassword(HttpServletResponse response) {
        // 验证是否开启服务
        if (!properties.getForgetPassword().isOpen()) {
            writeContent(response, P6eJsonUtil.toJson(ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE)));
        }
        // 读取配置文件的属性
        final Map<String, String> content = new HashMap<>(forgetPasswordPageContent.size() + 2);
        for (final String key : forgetPasswordPageContent.keySet()) {
            content.put(key, forgetPasswordPageContent.get(key));
        }
        write(response, content, "FORGET_PASSWORD", forgetPasswordPage);
    }

    /**
     * 写入返回的页面数据
     * @param response HttpServletResponse 对象
     */
    private void write(HttpServletResponse response, Map<String, String> content, String page, String pageData) {
        // 创建凭证数据
        final VoucherModel.ResultDto result =
                voucherService.create(new VoucherModel.ParamDto().setDevice(getDevice()));
        if (result.getError() != null) {
            writeContent(response, P6eJsonUtil.toJson(ApiResultModel.build(result.getError())));
        } else {
            // 写入特定的数据
            content.put("__PAGE__", page);
            content.put("__VOUCHER__", result.getVoucher());
            writeContent(response, P6eTransformUtil.template(pageData, content));
        }
    }

    /**
     * 写入返回的页面数据
     * @param response HttpServletResponse 对象
     * @param content 返回的页面内容
     */
    private static void writeContent(HttpServletResponse response, String content) {
        PrintWriter pw = null;
        try {
            response.addHeader(CONTENT_TYPE, HTML_CONTENT_TYPE);
            pw = response.getWriter();
            pw.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.flush();
                pw.close();
            }
        }
    }
}
