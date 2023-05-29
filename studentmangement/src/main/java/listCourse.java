
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table course
 * in the database.
 * 
 * @birthday www.codejava.net
 *
 */
@WebServlet("/listCourse")
public class listCourse {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public listCourse(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    public listCourse() {
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertCourse(course course) throws SQLException {
        String sql = "INSERT INTO course (classId, name, lecture, year, notes) VALUES (?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);

        statement.setInt(1, course.getClassId());
        statement.setString(2, course.getName());
        statement.setString(3, course.getLecture());
        statement.setInt(4, course.getYear());
        statement.setString(5, course.getNotes());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<course> listAllCourses() throws SQLException {
        List<course> listCourse = new ArrayList<>();

        String sql = "SELECT * FROM course";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int classId = resultSet.getInt("classId");
            String name = resultSet.getString("name");
            int year = resultSet.getInt("year");
            String lecture = resultSet.getString("lecture");
            String notes = resultSet.getString("notes");

            course course = new course(classId, name, lecture, year, notes);
            listCourse.add(course);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listCourse;
    }

    public boolean deleteCourse(course course) throws SQLException {
        String sql = "DELETE FROM course where classId = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, course.getClassId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateCourse(course course) throws SQLException {
        String sql = "UPDATE course SET name = ?,lecture = ?, year = ?,notes = ?";
        sql += " WHERE classId = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);

        statement.setString(1, course.getName());
        statement.setInt(3, course.getYear());
        statement.setString(2, course.getLecture());
        statement.setString(4, course.getNotes());
        statement.setInt(5, course.getClassId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public course getCourse(int id) throws SQLException {
        course course = null;
        String sql = "SELECT * FROM course WHERE classId = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String lecture = resultSet.getString("lecture");
            String notes = resultSet.getString("notes");
            int year = resultSet.getInt("year");

            course = new course(id, name, lecture, year, notes);
        }

        resultSet.close();
        statement.close();
        disconnect();

        return course;
    }

    public course findCourse(String name) throws SQLException {
        course course = null;
        String sql = "SELECT * FROM course WHERE name = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, name);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("classId");
            String lecture = resultSet.getString("lecture");
            int year = resultSet.getInt("year");
            String notes = resultSet.getString("notes");

            course = new course(id, name, lecture, year, notes);
        }

        resultSet.close();
        statement.close();
        disconnect();
        return course;
    }

    // 1 exist, 2 duplicate , 3 success, 4 something wrong
    public int checkExist(int id) throws SQLException {
        String sql = "SELECT * FROM student WHERE id = " + id;
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            resultSet.close();
            statement.close();
            disconnect();
            return 1;
        }
        resultSet.close();
        statement.close();
        disconnect();
        return -1;
    }

    public int checkDuplicate(int id, int classId) throws SQLException {
        String sql = "SELECT * FROM course_student WHERE classId = " + classId;
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int checkid = resultSet.getInt("id");
            // System.out.println("check id" + checkid);
            if (checkid == id) {
                resultSet.close();
                statement.close();
                disconnect();
                // System.out.println("Duplicate studuent");
                return 2;

            }

        }
        // System.out.println("pass Check duplicate");
        return -2;
    }

    public int addStudentCourse(int id, int classId, int year) throws SQLException {
        if (checkExist(id) == -1) {
            return -1;
        }
        if (checkDuplicate(id, classId) == 2) {
            return 2;
        }
        if (checkExist(id) == 1 & checkDuplicate(id, classId) == -2) {
            String sql = "INSERT INTO course_student (id, classId, year) VALUES (?, ?, ?)";

            connect();

            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, classId);
            statement.setInt(3, year);

            boolean rowInserted = statement.executeUpdate() > 0;
            statement.close();
            disconnect();
            if (rowInserted == true) {
                return 3;
            }

        }
        return 4;
    }

    public List<student> listStudentCourse(int classId) throws SQLException {
        List<student> listStudent = new ArrayList<>();

        List<Integer> studentID = new ArrayList<Integer>();
        String sql = "SELECT * FROM course_student WHERE classId = " + classId;
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int studentId = resultSet.getInt("id");
            studentID.add(studentId);

        }

        student student = null;
        for (int i : studentID) {
            String sql1 = "SELECT * FROM student WHERE id = " + i;
            statement = jdbcConnection.prepareStatement(sql1);
            resultSet = statement.executeQuery(sql1);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String birthday = resultSet.getString("birthday");
                String address = resultSet.getString("address");
                String notes = resultSet.getString("notes");
                String grade = resultSet.getString("grade");

                student = new student(i, name, grade, birthday, address, notes);
                listStudent.add(student);

            }
        }

        return listStudent;

    }

    public boolean deleteStudentCourse(int id, int classId) throws SQLException {
        String sql = "DELETE FROM course_student where id = ? AND classId =?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.setInt(2, classId);

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public int getYear(int classId) throws SQLException {
        String sql = "SELECT year FROM course WHERE classId = " + classId;
        connect();
        int year = -1;
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            year = resultSet.getInt("year");
            return year;
        }
        return year;
    }

    public List<course> getYearCourse(int id, int year) throws SQLException {
        String sql = "SELECT classId FROM course_student WHERE id = " + id + " AND year = " + year;
        List<course> listCCourse = new ArrayList<>();
        connect();
        List<Integer> classID = new ArrayList<Integer>();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int classId = resultSet.getInt("classId");
            System.out.println("Get Year course from sql: " + classId);
            classID.add(classId);

        }

        for (int i : classID) {
            String sql1 = "SELECT * FROM course WHERE classId = " + i;
            statement = jdbcConnection.prepareStatement(sql1);
            resultSet = statement.executeQuery(sql1);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lecture = resultSet.getString("lecture");
                String notes = resultSet.getString("notes");
                int yearcourse = resultSet.getInt("year");
                int iddd = resultSet.getInt("classId");
                listCCourse.add(new course(iddd, name, lecture, yearcourse, notes));
            }

        }
        return listCCourse;
    }

}