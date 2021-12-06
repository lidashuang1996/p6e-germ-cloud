package club.p6e.germ.message.http.filter;

import club.p6e.germ.message.config.Config;
import com.p6e.germ.common.utils.P6eIpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 使用 Filter 实现对不是白名单IP进行过滤
 *
 * @author LiDaShuang
 * @version 1.0
 */
@WebFilter(filterName = "IpFilter", urlPatterns = {"*"})
public class IpFilter implements Filter {

    /** 注入日志系统 */
    private static final Logger LOGGER = LoggerFactory.getLogger(IpFilter.class);

    /** 声明的变量 */
    private static final String SMS_PATH = "/sms/";
    private static final String MAIL_PATH = "/mail/";
    private static final String CONSOLE_PATH = "/console/";

    @Resource
    private Config config;

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info("Filter [ IpFilter ] init complete ... ");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String ip = P6eIpUtil.get(request);
        final String consoleIp = config.getConsole().getIp();
        final String contextIp = config.getContext().getIp();
        try {
            final boolean consoleBool = P6eIpUtil.isInPool(ip, consoleIp);
            final boolean contextBool = P6eIpUtil.isInPool(ip, contextIp);
            if (consoleBool || contextBool) {
                final String path = request.getServletPath();
                if (path.startsWith(SMS_PATH) || path.startsWith(MAIL_PATH)) {
                    if (!contextBool) {
                        response.setStatus(403);
                        return;
                    }
                } else if (path.startsWith(CONSOLE_PATH)) {
                    if (!consoleBool) {
                        response.setStatus(403);
                        return;
                    }
                }
            } else {
                response.setStatus(403);
                return;
            }
        } catch (Exception e) {
            // 忽略异常
            response.setStatus(403);
            return;
        }
        // 进入程序
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        LOGGER.info("Filter [ IpFilter ] destroy complete ... ");
    }
}
