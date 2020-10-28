package by.halatsevich.company.controller.filter;

import by.halatsevich.company.controller.PagePath;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PageRedirectSecurityFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(PageRedirectSecurityFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        logger.log(Level.ERROR, "Invalid action");
        httpResponse.sendError(404, PagePath.ERROR);
    }
}
