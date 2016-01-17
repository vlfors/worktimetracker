package test.worktimetracker.servlets;

import test.worktimetracker.beans.SessionOfUserLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 05.01.2016.
 */
public class LogoutServlet extends HttpServlet{
    @EJB
    private SessionOfUserLocal sessionus;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // if (sessionus.getSession(user)!=null){
        try {


            String redirectUrl;
            if (sessionus.closeSession()) {
                redirectUrl = "login.jsp";
            } else
                redirectUrl = "errorlogin.jsp";
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
