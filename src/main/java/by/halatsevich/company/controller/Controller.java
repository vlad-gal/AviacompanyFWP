package by.halatsevich.company.controller;

import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebServlet(urlPatterns = "/controller")
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommandProvider provider = CommandProvider.getInstance();
        String commandName = req.getParameter(ParameterName.COMMAND);
        Command command = provider.defineCommand(commandName);
        logger.log(Level.INFO, "Command name - {}", commandName);
        String page = command.execute(req);
        HttpSession session = req.getSession();
        session.setAttribute(ParameterName.CURRENT_PAGE,page);
        RequestHandler handler = new RequestHandler();
        handler.setAttributes(req);
        session.setAttribute(ParameterName.REQUEST_ATTRIBUTES,handler);
        req.getRequestDispatcher(page).forward(req,resp);
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }
}
