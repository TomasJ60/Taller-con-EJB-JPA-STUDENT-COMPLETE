<%-- 
    Document   : studentInfo
    Created on : 7/09/2024, 10:57:35 PM
    Author     : tomas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Information</title>
        <style>
            .large-select {
                width: 300px; /* Ajusta el ancho según tus necesidades */
                height: 35px; /* Ajusta la altura según tus necesidades */
                font-size: 16px; /* Ajusta el tamaño de la fuente según tus necesidades */
            }
        </style>
    </head>
    <body>
        <h1>Student Information</h1>
        <form action="./StudentServlet" method="POST">
            <table>
                <tr>
                    <td>Student Id</td>
                    <td><input type="text" name="studentId" value="${student.studentid}" /></td>
                </tr>
                <tr>
                    <td>First Name</td>
                    <td><input type="text" name="firstName" value="${student.firstname}" /></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" name="lastName" value="${student.lastname}" /></td>
                </tr>
                <tr>
                    <td>Year Level</td>
                    <td><input type="text" name="yearLevel" value="${student.yearlevel}" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="action" value="Add" />
                        <input type="submit" name="action" value="Edit" />
                        <input type="submit" name="action" value="Delete" />
                        <input type="submit" name="action" value="Search" />
                        <input type="submit" name="action" value="See Table" />
                    </td>
                </tr>
            </table>
        </form>
        <br>
        <table border="1">
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Year Level</th>
                <c:forEach items="${allStudents}" var="stud">
                <tr>
                    <td>${stud.studentid}</td>
                    <td>${stud.firstname}</td>
                    <td>${stud.lastname}</td>
                    <td>${stud.yearlevel}</td>
                </tr>
            </c:forEach> 
        </table>
        <h1>Course Information</h1>
        <form action="./CourseServlet" method="POST">
            <table>
                <tr>
                    <td>Course Code</td>
                    <td><input type="text" name="courseCode" value="${course.coursecode}" /></td>
                </tr>
                <tr>
                    <td>Name Course</td>
                    <td><input type="text" name="nameCourse" value="${course.namecourse}" /></td>
                </tr>
                <tr>
                    <td>Credits</td>
                    <td><input type="text" name="credits" value="${course.credits}" /></td>
                </tr>
                <tr>
                    <td>Semestre</td>
                    <td><input type="text" name="semestre" value="${course.semestre}" /></td>
                </tr>
                <tr>
                    <td>Admitted</td>
                    <td><input type="text" name="admitted" value="${course.admitted}" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="action" value="Add" />
                        <input type="submit" name="action" value="Edit" />
                        <input type="submit" name="action" value="Delete" />
                        <input type="submit" name="action" value="Search" />
                        <input type="submit" name="action" value="See Table" />
                    </td>
                </tr>
            </table>
        </form>
        <br>
        <table border="1">
            <th>Course Code</th>
            <th>Name Course</th>
            <th>Credits</th>
            <th>Semestre</th>
            <th>Admitted</th>
                <c:forEach items="${allCorses}" var="cours">
                <tr>
                    <td>${cours.coursecode}</td>
                    <td>${cours.namecourse}</td>
                    <td>${cours.credits}</td>
                    <td>${cours.semestre}</td>
                    <td>${cours.admitted}</td>
                </tr>
            </c:forEach> 
        </table>
        <h1>Inscribir estudiantes</h1>
        <form action="./StudentCourseServlet" method="POST">
            <table>
                <tr>
                    <td>Student Id</td>
                    <td><input type="text" name="studentId" /></td>
                </tr>
                <tr>
                    <td>Course</td>
                    <td>
                        <select name="courseCode" id="courseCode">
                            <c:forEach var="course" items="${allCourses}">
                                <option value="${course.coursecode}">${course.namecourse} holaaa</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="action" value="Enroll Student" />
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
