package solutions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dennisschmock
 */
@WebServlet(name = "httpHeader", urlPatterns = {"/headers"})
public class httpHeader extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name != null) {
            request.getSession().setAttribute("name", name);
        } else {
        }
        name = (String) request.getSession().getAttribute("name");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SessionDemo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div><h1>Header exercise</h1></div>");

            out.println("<table><tr>");

            Enumeration en = request.getHeaderNames();
            while (en.hasMoreElements()) {
                String headerName = (String) en.nextElement(); //nextElement() returns Object need type cast
                String headerValue = request.getHeader(headerName);
                out.print("<td>" + headerName + "</td>" + "<td>" + headerValue + "</td></tr><tr>"
                );

            }
            out.println("</tr></table>");
            out.println("<div><h1>GET/Post exercise</h1></div>");

            out.println("<h2>Please enter your name, and submit - using POST</h2>");
            out.println("<div>When using POST, the parameters are not passed to the URL</div>");

            out.println("<form action='#' method='POST'>");
            out.println("<label>First Name</label><input type='input' name='name'>");
            out.println("<label>Last Name</label><input type='input' name='name'>");
            out.println("<input type='hidden' name='hidden' value='12345678'>");
            out.println("<input type='submit'></form>");

            out.println("<h2>Please enter your name, and submit - using GET</h2>");
            out.println("<div>When using GET, the parameters (including hidden) ARE passed to the URL</div>");

            out.println("<form action='#' method='GET'>");
            out.println("<label>First Name</label><input type='input' name='name'>");
            out.println("<label>Last Name</label><input type='input' name='name'>");
            out.println("<input type='hidden' name='hidden' value='12345678'>");
            out.println("<input type='submit'></form>");

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
