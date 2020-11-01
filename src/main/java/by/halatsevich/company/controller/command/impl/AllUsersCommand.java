package by.halatsevich.company.controller.command.impl;

import by.halatsevich.company.controller.PagePath;
import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.controller.command.Command;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.exception.ServiceException;
import by.halatsevich.company.model.service.ServiceFactory;
import by.halatsevich.company.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AllUsersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ParameterName.USER);
        String page = null;// TODO: 01.11.2020  
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService service = factory.getUserService();
        List<User> userList = null;
        switch (user.getRole()){
            case ADMIN:{
                try {
                    userList = service.findAllUsers();
                } catch (ServiceException e) {
                    page = PagePath.ERROR_500;
                    e.printStackTrace();
                }
            }
            break;
            case DISPATCHER:{
//                try {
////                    List<User> userList = service.findAllActiveUsers();
//                    request.getSession().setAttribute("userList",userList);
//                    page = PagePath.USER_ACCOUNT;
//                } catch (ServiceException e) {
//                    page = PagePath.ERROR_500;
//                    e.printStackTrace();
//                }
            }
            break;
        }
        request.getSession().setAttribute("userList",userList);
        page = PagePath.USER_ACCOUNT;
        return page;

    }
}
