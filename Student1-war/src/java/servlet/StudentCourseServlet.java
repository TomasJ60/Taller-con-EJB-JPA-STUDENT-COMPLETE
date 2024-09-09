/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Course;
import entity.EstudianteCurso;
import entity.EstudianteCursoPK;
import entity.Student;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.CourseFacade;
import session.EstudianteCursoFacadeLocal;
import session.StudentFacadeLocal;

/**
 *
 * @author tomas
 */
@WebServlet(name = "StudentCourseServlet", urlPatterns = {"/StudentCourseServlet"})
public class StudentCourseServlet extends HttpServlet {

    
    
    
    @EJB
    private StudentFacadeLocal studentFacade;

    @EJB
    private CourseFacade courseFacade;

    @EJB
    private EstudianteCursoFacadeLocal estudianteCursoFacade;

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

        // Obtener parámetros del request
        String studentIdStr = request.getParameter("studentId");
        String courseCodeStr = request.getParameter("courseCode");

        Integer studentId = null;
        Integer courseCode = null;

        if (studentIdStr != null && !studentIdStr.trim().isEmpty()) {
            try {
                studentId = Integer.parseInt(studentIdStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if (courseCodeStr != null && !courseCodeStr.trim().isEmpty()) {
            try {
                courseCode = Integer.parseInt(courseCodeStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // Lógica para inscribir un estudiante a un curso
        if ("Enroll Student".equalsIgnoreCase(action)) {
            if (studentId != null && courseCode != null) {
                EstudianteCursoPK pk = new EstudianteCursoPK(studentId, courseCode);
                EstudianteCurso estudianteCurso = new EstudianteCurso(pk);

                // Guardar en la base de datos
                estudianteCursoFacade.create(estudianteCurso);
            }
        }

        // Recuperar listas de estudiantes y cursos para mostrar en el JSP
        List<Student> allStudents = studentFacade.findAll();
        List<Course> allCourses = courseFacade.findAll();
        for (Course course : allCourses) {
            System.out.println("Course Code: " + course.getCoursecode() + " Name: " + course.getNamecourse());
        }

        if (allCourses.isEmpty()) {
            System.out.println("No courses found!");
        }

        // Pasar las listas al JSP
        request.setAttribute("allCourses", allCourses);
        request.getRequestDispatcher("studentInfo.jsp").forward(request, response);

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StudentCourseServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudentCourseServlet at " + request.getContextPath() + "</h1>");
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
