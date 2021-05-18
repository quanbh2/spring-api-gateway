package producer.eureka.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilterCustom implements Filter {

    @Value("${app.allow.domains}")
    private String allowDomains;

    @Value("${app.allow.httpMethods}")
    private String allowMethods;

    @Value("${app.allow.headers:x-requested-with}")
    private String allowHeaders;

    @Value("${app.max.age}")
    private String maxAge;

    @Value("${app.allow.credentials}")
    private String allowCredentials;

    @Value("${app.allow.options.headers}")
    private String optionAllowHeaders;

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Origin", allowDomains);
        response.setHeader("Access-Control-Allow-Methods", allowMethods);
        response.setHeader("Access-Control-Allow-Headers", allowHeaders);
        response.setHeader("Access-Control-Max-Age", maxAge);
        response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
        if (!(request.getMethod().equalsIgnoreCase("OPTIONS"))) {
            try {
                chain.doFilter(req, res);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.setHeader("Access-Control-Max-Age", maxAge);
            response.setHeader("Access-Control-Allow-Headers", optionAllowHeaders);
            response.setStatus(HttpServletResponse.SC_OK);
        }

    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}
