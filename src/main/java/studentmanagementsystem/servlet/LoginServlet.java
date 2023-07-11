package studentmanagementsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentmanagementsystem.dao.StudentDao;
import studentmanagementsystem.dto.Student;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String passwordFromFrontend = req.getParameter("password");
		StudentDao dao = new StudentDao();
		List<Student> list = dao.getAllStudents();
		boolean value = false;

		for (Student student : list) {
			if (student.getEmail().equals(email)) {
				value = true;
				break;
			}
		}
		if (value == false) {
//    	when your email is not present in the database
			req.setAttribute("message", "InvalidEMail");
			RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
			dispatcher.include(req, resp);

		} else {
//    	email is present
			Student databaseStudent = dao.fetchStudentByEmail(email);
			if (databaseStudent.getPassword().equals(passwordFromFrontend)) {
//    		login success
				req.setAttribute("list", list);
				RequestDispatcher dispatcher = req.getRequestDispatcher("display.jsp");
				dispatcher.forward(req, resp);
			} else {
//    		login failure

				req.setAttribute("message", "invalidpassword");
				RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
				dispatcher.include(req, resp);
			}
		}

	}
}
