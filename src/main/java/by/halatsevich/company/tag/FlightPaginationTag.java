package by.halatsevich.company.tag;

import by.halatsevich.company.controller.ParameterName;
import by.halatsevich.company.entity.Flight;
import by.halatsevich.company.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * The class represents custom flight pagination tag.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class FlightPaginationTag extends TagSupport {
    private static final Logger logger = LogManager.getLogger(FlightPaginationTag.class);
    private static final String DATA_PATTERN = "d-MMMM-yyyy HH:mm";
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
        List<Flight> flights = (List<Flight>) session.getAttribute(ParameterName.FLIGHT_LIST);
        User mainUser = (User) session.getAttribute(ParameterName.USER);
        String lang = (String) session.getAttribute(ParameterName.LANG);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATA_PATTERN, new Locale(lang));
        int firstIndex = currentPageNumber * COUNT_OF_ITEMS - COUNT_OF_ITEMS;
        int lastIndex = Math.min(currentPageNumber * COUNT_OF_ITEMS, flights.size());
        try {
            JspWriter out = pageContext.getOut();
            for (int i = firstIndex; i < lastIndex; i++) {
                Flight flight = flights.get(i);
                out.write("<tbody>");
                out.write("<tr>");
                out.write("<td>" + flight.getDepartureAirport().getAirportName() + ", " + flight.getDepartureAirport().getCity() + ", " + flight.getDepartureAirport().getCountry() + "</td>");
                out.write("<td>" + flight.getDestinationAirport().getAirportName() + ", " + flight.getDestinationAirport().getCity() + ", " + flight.getDestinationAirport().getCountry() + "</td>");
                out.write("<td>" + dateFormat.format(flight.getDepartTime()) + "</td>");
                out.write("<td>" + dateFormat.format(flight.getArriveTime()) + "</td>");
                if (mainUser != null) {
                    out.write("<td>" + flight.getCrew().getCrewName() + "</td>");
                    out.write("<td>" + flight.getStatus().getStatusName() + "</td>");
                    out.write("<td>" + flight.getOperator().getFirstName() + " " + flight.getOperator().getLastName() + "</td>");
                    if (mainUser.getRole() == User.Role.ADMIN || mainUser.getRole() == User.Role.OPERATOR) {
                        out.write("<td><div class=\"d-inline-flex\"><div class=\"ml-2\">");
                        out.write("<a href=\"controller?command=update_flight_page&flightId=" + flight.getId());
                        out.write("\" class=\"btn btn-info\">Edit</a>");
                        out.write("</div></div></td>");
                    }
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
            if (lastIndex < flights.size()) {
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
