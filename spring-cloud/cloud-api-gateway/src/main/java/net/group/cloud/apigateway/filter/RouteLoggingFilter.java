package net.group.cloud.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.ribbon.RibbonHttpResponse;
import org.springframework.cloud.netflix.ribbon.apache.RibbonApacheHttpResponse;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * route filters – are used to route the request.
 * This class is a RouteLoggingFilter (fake-RouteFilter) after true-RouteFilter
 * filterOrder() = FilterConstants.SIMPLE_HOST_ROUTING_FILTER_ORDER - 1 // This config to do this
 */
@Slf4j
public class RouteLoggingFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SIMPLE_HOST_ROUTING_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        log.info("Route mapping: {} ---> {}", context.get("requestURI"), context.get("proxy"));

        HttpServletRequest servletRequest = context.getRequest();
        log.info("requestURL: {}", servletRequest.getRequestURL());

        RibbonApacheHttpResponse ribbonResponse = (RibbonApacheHttpResponse) context.get("ribbonResponse");
        log.info("ribbonResponseUri: {}", ribbonResponse.getRequestedURI().toString());

//        RibbonHttpResponse zuulResponse = (RibbonHttpResponse) context.get("zuulResponse");
        return null;
    }
}
