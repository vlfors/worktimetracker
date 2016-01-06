package test.worktimetracker.servlets;

import org.apache.catalina.Session;
import test.worktimetracker.beans.SessionOfUserLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by vlad on 29.12.2015.
 */
public class LoginServlet extends HttpServlet    {

    @EJB
    private SessionOfUserLocal sessionus;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String user = request.getParameter("j_username");
        String pass = request.getParameter("j_password");
        sessionus.getSession(user);
       // if (sessionus.getSession(user)!=null){
        String suffix = "";
         if (sessionus.checkStatus())
             suffix ="#/AddNewTask";
         else
             suffix ="#/CurrentTask";
         String redirectUrl = "index.jsp"+suffix;
         //request.getSession().setAttribute("user_name",user);
         response.sendRedirect(redirectUrl);
         return;
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
