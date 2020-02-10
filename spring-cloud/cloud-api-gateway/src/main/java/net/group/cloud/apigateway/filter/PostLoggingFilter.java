package net.group.cloud.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;


/**
 * post filters – are invoked after the request has been routed.
 * This class is a PostLoggingFilter (fake-PostFilter) after true-PostFilter
 * filterOrder() = FilterConstants.SEND_RESPONSE_FILTER_ORDER + 1 // This config to do this
 */
@Slf4j
public class PostLoggingFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
//        RequestContext context = RequestContext.getCurrentContext();
//        log.info("post routing mapping: {} ---> {}", context.get("requestURI"), context.get("proxy"));
        log.info("---Ends Zuul Filters---");
        return null;
    }
}

