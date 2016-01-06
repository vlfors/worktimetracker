package test.worktimetracker.servlets.trash;

/**
 * Created by vlad on 26.12.2015.
 */
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.ejb.*;

import test.worktimetracker.beans.CheckHibLocal;
import test.worktimetracker.beans.HelloWorldLocal;

public class TestClient extends HttpServlet implements javax.servlet.Servlet  {
        @EJB
        private HelloWorldLocal hello;
        @EJB
        private CheckHibLocal chk;

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                try {

                        String result = hello.getMessage(request.getParameter("name"));
                        chk.Check();
                        request.getSession().setAttribute("result", result);
                        RequestDispatcher rd = request.getRequestDispatcher( "index.jsp");
                        rd.forward(request, response);
                } catch (Exception e) {
                        throw new ServletException(e.getMessage());
                }
        }
}
