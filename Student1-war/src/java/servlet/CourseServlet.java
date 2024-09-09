/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Course;
import entity.Student;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.CourseFacade;

/**
 *
 * @author tomas
 */
@WebServlet(name = "CourseServlet", urlPatterns = {"/CourseServlet"})
public class CourseServlet extends HttpServlet {

    @EJB
    private CourseFacade courseFacade;

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
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");

        String courseCodestr = request.getParameter("courseCode");
        Integer coursecode = null;
        if (courseCodestr != null && !courseCodestr.trim().isEmpty()) {
            try {
                coursecode = Integer.parseInt(courseCodestr);
            } catch (NumberFormatException e) {
            }
        }

        String namecourse = request.getParameter("nameCourse");
        String credits = request.getParameter("credits");
        String semestre = request.getParameter("semestre");

        String admittedstr = request.getParameter("admitted");
        Integer admitted = null;
        if (admittedstr != null && !admittedstr.trim().isEmpty()) {
            try {
                admitted = Integer.parseInt(admittedstr);
            } catch (NumberFormatException e) {

            }
        }

        Course curso = new Course(coursecode, namecourse, credits, semestre, admitted);

        if ("Add".equalsIgnoreCase(action)) {
            courseFacade.create(curso);
        } else if ("Edit".equalsIgnoreCase(action)) {
            courseFacade.edit(curso);
        } else if ("Delete".equalsIgnoreCase(action)) {
            courseFacade.remove(curso);
        } else if ("Search".equalsIgnoreCase(action)) {
            curso = courseFacade.find(coursecode);
            List<Course> cursos = new ArrayList<>();
            if (curso != null) {
                //estudiantes.add(estudiante);
                cursos.add(curso);
            }
            request.setAttribute("cours", curso);
            request.setAttribute("cours", curso);
            request.setAttribute("allCorses", courseFacade.findAll()); 
            return;
        }

        List<Course> cursos = courseFacade.findAll();
        request.setAttribute("cours", curso);
        request.setAttribute("allCorses", cursos); 
        request.getRequestDispatcher("studentInfo.jsp").forward(request, response);

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CourseServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CourseServlet at " + request.getContextPath() + "</h1>");
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
