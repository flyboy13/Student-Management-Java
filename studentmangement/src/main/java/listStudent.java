
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
 * This DAO class provides CRUD database operations for the table student
 * in the database.
 * 
 * @birthday www.codejava.net
 *
 */
@WebServlet("/listStudent")
public class listStudent {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public listStudent(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    public listStudent() {
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

    public boolean insertStudent(student student) throws SQLException {
        String sql = "INSERT INTO student (id, name, grade, birthday, address, notes) VALUES (?, ?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);

        statement.setInt(1, student.getId());
        statement.setString(2, student.getName());
        statement.setString(3, student.getGrade());
        statement.setString(4, student.getBirthday());
        statement.setString(5, student.getAddress());
        statement.setString(6, student.getNotes());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<student> listAllstudents() throws SQLException {
        List<student> liststudent = new ArrayList<>();

        String sql = "SELECT * FROM student";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String grade = resultSet.getString("grade");
            String birthday = resultSet.getString("birthday");
            String address = resultSet.getString("address");
            String notes = resultSet.getString("notes");

            student student = new student(id, name, grade, birthday, address, notes);
            liststudent.add(student);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return liststudent;
    }

    public boolean deletestudent(student student) throws SQLException {
        String sql = "DELETE FROM student where id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, student.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updatestudent(student student) throws SQLException {
        String sql = "UPDATE student SET name = ?,grade = ?, birthday = ?,address = ?,notes = ?";
        sql += " WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);

        statement.setString(1, student.getName());
        statement.setString(2, student.getGrade());
        statement.setString(3, student.getBirthday());
        statement.setString(4, student.getAddress());
        statement.setString(5, student.getNotes());
        statement.setInt(6, student.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public student getStudent(int id) throws SQLException {
        student student = null;
        String sql = "SELECT * FROM student WHERE id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String birthday = resultSet.getString("birthday");
            String address = resultSet.getString("address");
            String notes = resultSet.getString("notes");
            String grade = resultSet.getString("grade");

            student = new student(id, name, grade, birthday, address, notes);
        }

        resultSet.close();
        statement.close();

        return student;
    }

    public student FindStudent(String name) throws SQLException {
        student student = null;
        String sql = "SELECT * FROM student WHERE name = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, name);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String birthday = resultSet.getString("birthday");
            String grade = resultSet.getString("grade");
            String address = resultSet.getString("address");
            String notes = resultSet.getString("notes");

            student = new student(id, name, grade, birthday, address, notes);
        }

        resultSet.close();
        statement.close();

        return student;
    }
}