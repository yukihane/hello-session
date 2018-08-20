package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/*")
public class Hello extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);

        out.println("<html>");
        out.println("<head>");
        out.println("<title>session test</title>");
        out.println("</head>");
        out.println("<body>");

        if (session == null) {
            out.println("<p>first time</p>");
            session = request.getSession(true);
            session.setAttribute("visited", "1");
        } else {
            String visitedStr = (String) session.getAttribute("visited");
            int visited = Integer.parseInt(visitedStr);
            visited++;

            Date prevDate = (Date) session.getAttribute("date");

            out.println("<p>");
            out.println(visited);
            out.println("times, ");
            out.println("(previous date) " + prevDate.toString() + "</p?");

            session.setAttribute("visited", Integer.toString(visited));
        }
        session.setAttribute("date", new Date());

        out.println("</body>");
        out.println("</html>");
    }
}
