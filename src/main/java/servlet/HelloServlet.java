package servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(HelloServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("DoGet start");
        resp.getWriter().println("Hi");
        try {
            throw new NullPointerException();
        } catch (Exception e) {
            logger.error("Null pointer exeception");
        }
        logger.debug("DoGoet end");
    }
}
