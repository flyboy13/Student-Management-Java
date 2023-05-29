
import java.io.IOException;
import java.sql.SQLException;
// import java.util.Enumeration;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * 
 * @birthday www.codejava.net
 */
@WebServlet(urlPatterns = { "/liststudent", "/newstudent", "/updatestudent", "/editstudent", "/insertstudent",
        "/insertcourse", "/editcourse", "/newcourse", "/updatecourse", "/editcourse" }, asyncSupported = true)

public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private listStudent listStudent;
    private listCourse listCourse;

    public void init() {
        // String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        // String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        // String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        String jdbcURL = "jdbc:mysql://localhost:3306/student_database";
        String jdbcUsername = "root";
        String jdbcPassword = "1234";

        listStudent = new listStudent(jdbcURL, jdbcUsername, jdbcPassword);
        listCourse = new listCourse(jdbcURL, jdbcUsername, jdbcPassword);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/newstudent":
                    showNewForm(request, response);
                    break;
                case "/insertstudent":
                    insertstudent(request, response);
                    break;
                case "/deletestudent":
                    deletestudent(request, response);
                    break;
                case "/editstudent":
                    showEditForm(request, response);
                    break;
                case "/updatestudent":
                    updatestudent(request, response);
                    break;
                case "/liststudent":
                    liststudent(request, response);
                    break;
                case "/findstudent":
                    showFindForm(request, response);
                    break;

                case "/listcourse":
                    listCourse(request, response);
                    break;
                case "/addcourse":
                    showNewFormCourse(request, response);
                    break;
                case "/insertcourse":
                    insertCourse(request, response);
                    break;

                case "/deletecourse":
                    deletecourse(request, response);
                    break;
                case "/editcourse":
                    showEditFormCourse(request, response);
                    break;
                case "/updatecourse":
                    updateCourse(request, response);
                    break;
                case "/findcourse":
                    showFindCourseForm(request, response);
                    break;
                case "/managecourse":
                    showManageCourse(request, response);
                    break;
                case "/addStudentCourse":
                    addStudentCourse(request, response);
                    break;
                case "/removestudentfromcourse":
                    RemoveStudentCourse(request, response);
                    break;
                case "/mycourse":
                    getCourseYear(request, response);
                    break;
                case "/":
                    showHomepage(request, response);
                    break;
                default:
                    showHomepage(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showHomepage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Homepage");
        RequestDispatcher dispatcher = request.getRequestDispatcher("Homepage.jsp");
        dispatcher.forward(request, response);
    }

    private void liststudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        List<student> liststudent = null;
        liststudent = listStudent.listAllstudents();
        System.out.println("List student: " + liststudent);
        request.setAttribute("listStudent", liststudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("studentList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("showNewForm");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("student.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        student existingstudent = listStudent.getStudent(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student.jsp");
        request.setAttribute("student", existingstudent);
        dispatcher.forward(request, response);

    }

    private void showFindForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("nameInput");
        student liststudent = null;
        System.out.println("Name find: " + name);
        liststudent = listStudent.FindStudent(name);
        request.setAttribute("listStudent", liststudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("FindList.jsp");

        dispatcher.forward(request, response);

    }

    private void insertstudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        System.out.println("InsertStudent");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String address = request.getParameter("address");
        String notes = request.getParameter("notes");
        String grade = request.getParameter("grade");

        System.out.println("name:" + name);
        System.out.println("grade:" + grade);
        System.out.println("birthday:" + birthday);
        System.out.println("address:" + address);
        System.out.println("notes:" + notes);

        student newstudent = new student(id, name, grade, birthday, address, notes);
        listStudent.insertStudent(newstudent);

        response.sendRedirect("liststudent");
    }

    private void updatestudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        System.out.println("updateStudent");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        // Enumeration<String> enumeration = request.getParameterNames();
        // while (enumeration.hasMoreElements()) {
        // String parameterName = enumeration.nextElement();
        // System.out.println("paraname: ");
        // System.out.println(parameterName);
        // }

        int id = Integer.parseInt(request.getParameter("id"));

        String name = request.getParameter("name");
        String grade = request.getParameter("grade");
        String birthday = request.getParameter("birthday");
        String address = request.getParameter("address");
        String notes = request.getParameter("notes");

        System.out.println("Id:" + id);
        System.out.println("name:" + name);
        System.out.println("grade:" + grade);
        System.out.println("birthday:" + birthday);
        System.out.println("address:" + address);
        System.out.println("notes:" + notes);

        student student = new student(id, name, grade, birthday, address, notes);
        listStudent.updatestudent(student);
        response.sendRedirect("liststudent");
    }

    private void deletestudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("Id:" + id);
        student student = new student(id);
        listStudent.deletestudent(student);
        response.sendRedirect("liststudent");

    }

    // Course
    private void listCourse(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        List<course> listcourse = null;
        listcourse = listCourse.listAllCourses();
        System.out.println("List course: " + listcourse);
        request.setAttribute("listCourse", listcourse);
        RequestDispatcher dispatcher = request.getRequestDispatcher("courseList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewFormCourse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        System.out.println("showNewFormCourse");
        RequestDispatcher dispatcher = request.getRequestDispatcher("course.jsp");
        dispatcher.forward(request, response);
    }

    private void insertCourse(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        System.out.println("InsertCourse");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("classId"));
        String name = request.getParameter("name");
        String lecture = request.getParameter("lecture");
        String notes = request.getParameter("notes");
        int year = Integer.parseInt(request.getParameter("year"));
        System.out.println("id:" + id);
        System.out.println("name:" + name);
        System.out.println("lecture:" + lecture);
        System.out.println("year:" + year);
        System.out.println("notes:" + notes);

        course newcourse = new course(id, name, lecture, year, notes);
        listCourse.insertCourse(newcourse);
        response.sendRedirect("listcourse");
    }

    private void showEditFormCourse(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("classId"));
        course existingcourse = listCourse.getCourse(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("course.jsp");
        request.setAttribute("course", existingcourse);
        dispatcher.forward(request, response);

    }

    private void updateCourse(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        System.out.println("updateCourse");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        // Enumeration<String> enumeration = request.getParameterNames();
        // while (enumeration.hasMoreElements()) {
        // String parameterName = enumeration.nextElement();
        // System.out.println("paraname: ");
        // System.out.println(parameterName);
        // }

        int id = Integer.parseInt(request.getParameter("classId"));

        String name = request.getParameter("name");
        int year = Integer.parseInt(request.getParameter("year"));
        String lecture = request.getParameter("lecture");
        String notes = request.getParameter("notes");

        course course = new course(id, name, lecture, year, notes);
        listCourse.updateCourse(course);
        response.sendRedirect("listcourse");
    }

    private void deletecourse(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("classId"));
        course course = new course(id);
        listCourse.deleteCourse(course);
        response.sendRedirect("listcourse");

    }

    private void showFindCourseForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("courseInput");
        course listcourse = null;
        System.out.println("course find: " + name);
        listcourse = listCourse.findCourse(name);
        request.setAttribute("course", listcourse);
        RequestDispatcher dispatcher = request.getRequestDispatcher("FindListCourse.jsp");
        dispatcher.forward(request, response);

    }

    private void showManageCourse(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int classId = Integer.parseInt(request.getParameter("classId"));
        course existingcourse = listCourse.getCourse(classId);
        request.setAttribute("course", existingcourse);
        List<student> listStudent = listCourse.listStudentCourse(classId);
        request.setAttribute("listStudent", listStudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Management.jsp");
        dispatcher.forward(request, response);

    }

    private void addStudentCourse(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int id = -1025;
        int check = -1;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            check = 0;
        }

        int classId = Integer.parseInt(request.getParameter("classId"));
        System.out.println("id: " + id);
        System.out.println("classId: " + classId);

        // 0 string, 1 exist, 2 duplicate , 3 success
        int year = listCourse.getYear(classId);
        System.out.println("Add course year: " + year);
        if (check != 0) {
            check = listCourse.addStudentCourse(id, classId, year);
        }
        List<student> listStudent = listCourse.listStudentCourse(classId);
        System.out.println("check: " + check);
        request.setAttribute("check", check);

        course existingcourse = listCourse.getCourse(classId);
        request.setAttribute("course", existingcourse);
        request.setAttribute("listStudent", listStudent);
        // response.sendRedirect("managecourse");
        RequestDispatcher dispatcher = request.getRequestDispatcher("Management.jsp");
        dispatcher.forward(request, response);

    }

    private void RemoveStudentCourse(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        int classId = Integer.parseInt(request.getParameter("classId"));

        listCourse.deleteStudentCourse(id, classId);
        List<student> listStudent = listCourse.listStudentCourse(classId);
        course existingcourse = listCourse.getCourse(classId);

        request.setAttribute("course", existingcourse);
        request.setAttribute("listStudent", listStudent);

        RequestDispatcher dispatcher = request.getRequestDispatcher("Management.jsp");
        dispatcher.forward(request, response);

    }

    private void getCourseYear(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        int year = 0;
        String check = request.getParameter("year");

        if (check != null) {
            year = Integer.parseInt(request.getParameter("year"));
        } else
            year = 0;

        List<course> listCCourse = null;
        if (year != 0) {
            listCCourse = listCourse.getYearCourse(id, year);

        }
        request.setAttribute("id", id);

        request.setAttribute("listCourse", listCCourse);
        System.out.println("ListCourseOf Student:" + listCCourse);

        RequestDispatcher dispatcher = request.getRequestDispatcher("myCourse.jsp");
        dispatcher.forward(request, response);

    }

}