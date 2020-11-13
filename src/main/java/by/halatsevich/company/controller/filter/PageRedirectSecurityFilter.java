package by.halatsevich.company.controller.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class represents security filter which redirect to 403 error page when user using /jsp/* pattern in url.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class PageRedirectSecurityFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(PageRedirectSecurityFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        logger.log(Level.ERROR, "Invalid action from {}", httpRequest.getRequestURL());
        httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
    }
}
