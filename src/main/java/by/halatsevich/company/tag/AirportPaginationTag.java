package by.halatsevich.company.tag;

import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.entity.Airport;
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
 * The class represents custom airport pagination tag.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class AirportPaginationTag extends TagSupport {
    private static final Logger logger = LogManager.getLogger(AirportPaginationTag.class);
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
        List<Airport> airports = (List<Airport>) session.getAttribute(ParameterName.AIRPORT_LIST);
        User mainUser = (User) session.getAttribute(ParameterName.USER);
        int firstIndex = currentPageNumber * COUNT_OF_ITEMS - COUNT_OF_ITEMS;
        int lastIndex = Math.min(currentPageNumber * COUNT_OF_ITEMS, airports.size());
        try {
            JspWriter out = pageContext.getOut();
            for (int i = firstIndex; i < lastIndex; i++) {
                Airport airport = airports.get(i);
                out.write("<tbody>");
                out.write("<tr>");
                out.write("<td>" + airport.getAirportName() + "</td>");
                out.write("<td>" + airport.getCountry() + "</td>");
                out.write("<td>" + airport.getCity() + "</td>");
                if (mainUser != null && mainUser.getRole() == User.Role.ADMIN) {
                    out.write("<td><div class=\"d-inline-flex\"><div class=\"ml-2\">");
                    out.write("<a href=\"controller?command=update_airport_page&airportId=" + airport.getId());
                    out.write("\" class=\"btn btn-info\">Edit</a>");
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
            if (lastIndex < airports.size()) {
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
