package net.thiyagu_coda.studapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.thiyagu_coda.studapp.model.Student_user;

public class StudentDAO {
	private static String jdbcURL = "jdbc:mysql://simplesqlcontainer:3306/student_details?useSSL=false";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "password";

	private static final String INSERT_USERS_SQL = "INSERT INTO student" + "  (name, email, country,department,phoneNumber) VALUES "
			+ " (?,?,?,?,?);";

	private static final String SELECT_USER_BY_ID = "select * from student where student_id=?";
	private static final String SELECT_ALL_USERS = "select * from student";
	private static final String DELETE_USERS_SQL = "delete from student where student_id = ?;";
	private static final String UPDATE_USERS_SQL = "update student set name = ?,email= ?, country =? where student_id = ?;";
	
	public StudentDAO() {
		
	}
	
	protected static Connection getConnection() {
		
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;	
	}
	
	public static void insertUser(Student_user user) throws SQLException {
		System.out.println("SUCCESSFUL"+INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(INSERT_USERS_SQL)) {
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getCountry());
			ps.setString(4, user.getDept());
			ps.setInt(5, user.getPhoneNum());
			System.out.println(ps);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("NULL ? SQL EXCEPTION"+e);
		}
	}
	
	public Student_user selectUser(int id) {
		Student_user user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				String dept = rs.getString("department");
				int phone = rs.getInt("phoneNumber");
				user = new Student_user(id, name, email, country,dept,phone);
			}
		} catch (SQLException e) {
			//printSQLException(e);
			System.out.println(e.getMessage());
		}
		return user;
	}
	public static List<Student_user> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Student_user> user = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("student_id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				String dept = rs.getString("department");
				int phone = rs.getInt("phoneNumber");
				user.add(new Student_user(id, name, email, country,dept,phone));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return user;
	}
}