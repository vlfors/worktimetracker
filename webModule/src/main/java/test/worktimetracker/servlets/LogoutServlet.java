package test.worktimetracker.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by vlad on 05.01.2016.
 */
public class LogoutServlet extends HttpServlet{


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // if (sessionus.getSession(user)!=null){
        try {
            String redirectUrl;
            HttpSession session = request.getSession(false);
           // System.out.println("User="+session.getAttribute("UserState"));
            if(session != null){
                session.removeAttribute("UserState");
                session.invalidate();
            }
            redirectUrl = "index.jsp";

            //request.getSession().setAttribute("user_name",user);
            response.sendRedirect(redirectUrl);
        }catch (Exception e) {
            response.sendRedirect("errologin.jsp");
        }

        // }

    }

    //  request.getRequestDispatcher("/index.jsp").forward(request, response);
    //response.sendRedirect(redictedURL);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
