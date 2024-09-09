/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

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
import session.StudentFacadeLocal;

/**
 *
 * @author tomas
 */
@WebServlet(name = "StudentServlet", urlPatterns = {"/StudentServlet"})
public class StudentServlet extends HttpServlet {

    @EJB
    private StudentFacadeLocal studentFacade;

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
        String studentIdstr = request.getParameter("studentId");
        Integer id = null;
        if (studentIdstr != null && !studentIdstr.trim().isEmpty()) {
            try {
                id = Integer.parseInt(studentIdstr);
            } catch (NumberFormatException e) {
            }
        }

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        String yearLevelstr = request.getParameter("yearLevel");
        Integer yearLevel = null;
        if (yearLevelstr != null && !yearLevelstr.trim().isEmpty()) {
            try {
                yearLevel = Integer.parseInt(yearLevelstr);
            } catch (NumberFormatException e) {

            }
        }

        Student estudiante = new Student(id, firstName, lastName, yearLevel);

        if ("Add".equalsIgnoreCase(action)) {
            studentFacade.create(estudiante);
        } else if ("Edit".equalsIgnoreCase(action)) {
            studentFacade.edit(estudiante);
        } else if ("Delete".equalsIgnoreCase(action)) {
            studentFacade.remove(estudiante);
        } else if ("Search".equalsIgnoreCase(action)) {
            estudiante = studentFacade.find(id);
            List<Student> estudiantes = new ArrayList<>();
            if (estudiante != null) {
                estudiantes.add(estudiante);
            }
            request.setAttribute("stud", estudiante);
            request.setAttribute("allStudents", estudiantes);
            request.getRequestDispatcher("studentInfo.jsp").forward(request, response);
            return;
        } else if ("See Table".equalsIgnoreCase(action)) {
            List estudiantes = new ArrayList();
            estudiantes = studentFacade.findAll();
            request.setAttribute("stud", estudiante);
            request.setAttribute("allStudents", estudiantes);
            request.getRequestDispatcher("studentInfo.jsp").forward(request, response);
        }

        List<Student> estudiantes = studentFacade.findAll();
        request.setAttribute("stud", estudiante);
        request.setAttribute("allStudents", estudiantes);
        request.getRequestDispatcher("studentInfo.jsp").forward(request, response);

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StudentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudentServlet at " + studentIdstr + "</h1>");
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
