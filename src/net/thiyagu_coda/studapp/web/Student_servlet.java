package net.thiyagu_coda.studapp.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.thiyagu_coda.studapp.dao.StudentDAO;
import net.thiyagu_coda.studapp.model.Student_user;

import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

@WebServlet("/")
public class Student_servlet extends HttpServlet{
	private static final long serialVersionUID = 5L;
	private StudentDAO studDAO;
	
	public void init() {
		studDAO = new StudentDAO();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if(action.equals("/new"))
		{
				new_form(request,response);
		}
		else if (action.equals("/insert"))
		{
			try {
				insertUser(request,response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				listUser(request,response);
			} catch (SQLException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	private void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
    {
		// TODO Auto-generated method stub
		List<Student_user> listUser = StudentDAO.selectAllUsers(); 
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-user-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void new_form(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Let me navigate to student user form creation");
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-user-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		String dept = request.getParameter("dept");
		String phone = request.getParameter("phoneNum");
		Student_user newUser = new Student_user(name, email, country,dept,Integer.parseInt(phone.trim()));
		StudentDAO.insertUser(newUser);
		response.sendRedirect("list");
	}
}