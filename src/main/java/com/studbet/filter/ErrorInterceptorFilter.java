package com.studbet.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

@WebFilter("/*")
public class ErrorInterceptorFilter implements Filter {

    private static final String FTL_PATH_404 = "/WEB-INF/ftl/error/404.ftl";
    private static final String FTL_PATH_400 = "/WEB-INF/ftl/error/400.ftl";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {



        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpServletResponse wrappedResponse = new HttpServletResponseWrapper(httpResponse) {
            @Override
            public void sendError(int sc, String msg) throws IOException {
                if(sc == HttpServletResponse.SC_NOT_FOUND) {
                    try {
                        request.getRequestDispatcher(FTL_PATH_404).forward(request, response);
                    } catch (ServletException e) {
                        throw new RuntimeException(e);
                    }
                } else if (sc == HttpServletResponse.SC_BAD_REQUEST) {
                    try {
                        request.getRequestDispatcher(FTL_PATH_400).forward(request, response);
                    } catch (ServletException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        chain.doFilter(request, wrappedResponse);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}

