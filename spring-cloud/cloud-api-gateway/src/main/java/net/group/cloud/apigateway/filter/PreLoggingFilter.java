package net.group.cloud.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * pre filters – are invoked before the request is routed.
 * This class is a PreLoggingFilter (fake-PreFilter) before true-PreFilter
 * filterOrder() = FilterConstants.PRE_DECORATION_FILTER_ORDER // This config to do this
 */
@Slf4j
public class PreLoggingFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
//        RequestContext context = RequestContext.getCurrentContext();
        log.info("---Starts Zuul Filters---");
//        log.info("Pre routing mapping: {} ---> {}", context.get("requestURI"), context.get("proxy"));
        return null;
    }
}
