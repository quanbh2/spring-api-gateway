package net.group.cloud.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.util.ReflectionUtils;

/**
 * error filters – are invoked when an error occurs while handling the request.
 * This class is a ErrorLoggingFilter (fake-ErrorFilter) before true-ErrorFilter
 * filterOrder() = FilterConstants.SEND_ERROR_FILTER_ORDER - 1 // This config to do this
 */
@Slf4j
public class ErrorLoggingFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_ERROR_FILTER_ORDER - 1; // Needs to run before SendErrorFilter which has filterOrder == 0
    }

    @Override
    public boolean shouldFilter() {
        // only forward to errorPath if it hasn't been forwarded to already
        //return RequestContext.getCurrentContext().containsKey("error.status_code");
        return true;
    }

    @Override
    public Object run() {
        try {
            RequestContext context = RequestContext.getCurrentContext();
            Object e = context.get("throwable");

            if (e instanceof ZuulException) {
                ZuulException zuulException = (ZuulException) e;
                String rootCauseMessage = ExceptionUtils.getRootCauseMessage(zuulException);
                log.error("RootCauseMessage: {}", rootCauseMessage);
                log.error("FullStackTrace: {}", ExceptionUtils.getFullStackTrace(zuulException));

                // Remove error code to prevent further error handling in follow up filters
                context.remove("throwable");

                // Populate context with new response values
                context.setResponseBody(rootCauseMessage);
                context.getResponse().setContentType("application/json");
                context.setResponseStatusCode(500); //Can set any error code as excepted
            }
        } catch (Exception ex) {
            log.error("Exception filtering in custom error filter: {}", ExceptionUtils.getRootCauseMessage(ex));
            //ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }
}
