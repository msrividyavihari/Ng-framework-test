package com.deloitte.nextgen.security.interceptors;

import com.deloitte.nextgen.security.autoconfig.SecurityPropertiesBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    WebClient.Builder builder;

    @Autowired
    SecurityPropertiesBean securityPropertiesBean;

    /**
     * Intercept the execution of a handler. Called after HandlerMapping determined
     * an appropriate handler object, but before HandlerAdapter invokes the handler.
     * <p>DispatcherServlet processes a handler in an execution chain, consisting
     * of any number of interceptors, with the handler itself at the end.
     * With this method, each interceptor can decide to abort the execution chain,
     * typically sending an HTTP error or writing a custom response.
     * <p><strong>Note:</strong> special considerations apply for asynchronous
     * request processing. For more details see
     * {@link AsyncHandlerInterceptor}.
     * <p>The default implementation returns {@code true}.
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return {@code true} if the execution chain should proceed with the
     * next interceptor or the handler itself. Else, DispatcherServlet assumes
     * that this interceptor has already dealt with the response itself.
     * @throws Exception in case of errors
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("AuthorizationInterceptor -- preHandle");
        try {
//            Optional<String> internal = Optional.of(request.getHeader("isInternalRequest"));
//            internal.ifPresent( service ->  builder.build().get().uri(securityPropertiesBean.getValid())
//                    .header("Authorization",request.getHeader("Authorization"))
//                    .retrieve()
//                    .bodyToMono(Object.class)
//                    .block());
            if (Boolean.TRUE.equals(securityPropertiesBean.getTesting())) {
                return true;
            }
            builder.build().get().uri(securityPropertiesBean.getValid())
                    .header("Authorization", request.getHeader("Authorization"))
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();

        } catch (Exception e) {
            log.error("AuthorizationInterceptor -- preHandle -- Failed", e);
            return false;
        }
        log.info("AuthorizationInterceptor -- preHandle -- Passed");
        return true;
    }

    /**
     * Intercept the execution of a handler. Called after HandlerAdapter actually
     * invoked the handler, but before the DispatcherServlet renders the view.
     * Can expose additional model objects to the view via the given ModelAndView.
     * <p>DispatcherServlet processes a handler in an execution chain, consisting
     * of any number of interceptors, with the handler itself at the end.
     * With this method, each interceptor can post-process an execution,
     * getting applied in inverse order of the execution chain.
     * <p><strong>Note:</strong> special considerations apply for asynchronous
     * request processing. For more details see
     * {@link AsyncHandlerInterceptor}.
     * <p>The default implementation is empty.
     *
     * @param request      current HTTP request
     * @param response     current HTTP response
     * @param handler      the handler (or {@link org.springframework.web.method.HandlerMethod}) that started asynchronous
     *                     execution, for type and/or instance examination
     * @param modelAndView the {@code ModelAndView} that the handler returned
     *                     (can also be {@code null})
     * @throws Exception in case of errors
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("AuthorizationInterceptor -- postHandle");
    }

    /**
     * Callback after completion of request processing, that is, after rendering
     * the view. Will be called on any outcome of handler execution, thus allows
     * for proper resource cleanup.
     * <p>Note: Will only be called if this interceptor's {@code preHandle}
     * method has successfully completed and returned {@code true}!
     * <p>As with the {@code postHandle} method, the method will be invoked on each
     * interceptor in the chain in reverse order, so the first interceptor will be
     * the last to be invoked.
     * <p><strong>Note:</strong> special considerations apply for asynchronous
     * request processing. For more details see
     * {@link AsyncHandlerInterceptor}.
     * <p>The default implementation is empty.
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  the handler (or {@link org.springframework.web.method.HandlerMethod}) that started asynchronous
     *                 execution, for type and/or instance examination
     * @param ex       any exception thrown on handler execution, if any; this does not
     *                 include exceptions that have been handled through an exception resolver
     * @throws Exception in case of errors
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("AuthorizationInterceptor -- afterCompletion");
    }
}
