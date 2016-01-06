package test.worktimetracker.servlets.trash;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;


/**
 * Created by vlad on 28.12.2015.
 */

public class LoginFilter implements Filter {
    protected FilterConfig filterConfig = null;
    public void destroy() {
        filterConfig.getServletContext()
                .log(":: PostLoginFilter - destroy");
        filterConfig = null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws ServletException, IOException {

        filterConfig.getServletContext()
                .log(":: PostLoginFilter - doFilter");
        // Get the IP address of client machine.
        String ipAddress = req.getRemoteAddr();

        // Log the IP address and current timestamp.
        System.out.println("IP "+ ipAddress + ", Time "
                + new Date().toString());
        //=
        PrintWriter out = resp.getWriter();
        out.println("my-param (InitParameter): " + filterConfig.getInitParameter("my-param"));
        out.println("<br/><br/>Parameters:<br/>");
        Enumeration<String> parameterNames = req.getParameterNames();
        if (parameterNames.hasMoreElements()) {
            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement();
                String value = req.getParameter(name);
                out.println("name:" + name + ", value: " + value + "<br/>");
            }
        } else {
            out.println("---None---<br/>");
        }
        out.println("<br/>Start Regular Content:<br/><hr/>");
        chain.doFilter(req, resp);
        out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!<br/>!!!!!!!!!!!!!!!!!!!!<hr/>End Regular Content:<br/>");
        ///
        //chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        filterConfig = config;
        filterConfig.getServletContext().log(":: PostLoginFilter - init");
    }
}