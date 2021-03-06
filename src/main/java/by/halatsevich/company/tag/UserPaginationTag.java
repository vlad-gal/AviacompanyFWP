package by.halatsevich.company.tag;

import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 * The class represents custom user pagination tag.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class UserPaginationTag extends TagSupport {
    private static final Logger logger = LogManager.getLogger(UserPaginationTag.class);
    private static final int COUNT_OF_ITEMS = 10;
    private int currentPageNumber;

    /**
     * Sets current page number.
     *
     * @param currentPageNumber the current page number
     */
    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        User mainUser = (User) session.getAttribute(ParameterName.USER);
        List<User> users = (List<User>) session.getAttribute(ParameterName.ALL_USERS_LIST);
        int firstIndex = currentPageNumber * COUNT_OF_ITEMS - COUNT_OF_ITEMS;
        int lastIndex = Math.min(currentPageNumber * COUNT_OF_ITEMS, users.size());
        try {
            JspWriter out = pageContext.getOut();
            for (int i = firstIndex; i < lastIndex; i++) {
                User user = users.get(i);
                out.write("<tbody>");
                out.write("<tr>");
                out.write("<td>" + user.getFirstName() + "</td>");
                out.write("<td>" + user.getLastName() + "</td>");
                out.write("<td>" + user.getTelephoneNumber() + "</td>");
                out.write("<td>" + user.getEmail() + "</td>");
                out.write("<td>" + user.getRole().getRoleName() + "</td>");
                out.write("<td>" + user.getStatus().getStatusName() + "</td>");
                if (mainUser.getRole() == User.Role.ADMIN) {
                    out.write("<td><div class=\"d-inline-flex\"><div class=\"ml-2\">");
                    out.write("<a href=\"controller?command=admin_update_user_page&login=" + user.getLogin());
                    out.write("\" class=\"btn btn-info\">Edit</a>");
                    out.write("</div></div></td>");
                }
                if (mainUser.getRole() == User.Role.DISPATCHER) {
                    out.write("<td><div class=\"d-inline-flex\"><div class=\"ml-2\">");
                    out.write("<a href=\"controller?command=add_user_into_crew_page&login=" + user.getLogin());
                    out.write("\" class=\"btn btn-info\">Add into crew</a>");
                    out.write("</div></div></td>");
                }
                out.write("</tr>");
            }
            out.write("</tbody>");
            out.write("</table>");
            out.write("<div>");
            out.write("<ul class=\"pagination justify-content-center\">");
            out.write("<li class=\"page-item\">");
            if (firstIndex >= COUNT_OF_ITEMS) {
                out.write("<a class=\"page-link\" href=\"controller?command=pagination&direction=previous\" aria-label=\"Previous\">\n" +
                        "<span aria-hidden=\"true\">&laquo;</span>\n" +
                        "<span class=\"sr-only\">Previous</span>\n" +
                        "</a>\n" +
                        "</li>");
            }
            out.write("<li class=\"page-item\"><div class=\"page-link\">" + currentPageNumber + "</div></li>");
            if (lastIndex < users.size()) {
                out.write("<a class=\"page-link\" href=\"controller?command=pagination&direction=next\" aria-label=\"Next\">\n" +
                        "<span aria-hidden=\"true\">&raquo;</span>\n" +
                        "<span class=\"sr-only\">Next</span>\n" +
                        "</a>\n" +
                        "</li>");
            }
            out.write("</ul></div>");
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error while writing into out stream", e);
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
