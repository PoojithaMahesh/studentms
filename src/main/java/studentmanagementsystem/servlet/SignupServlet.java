package studentmanagementsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentmanagementsystem.dao.StudentDao;
import studentmanagementsystem.dto.Student;

public class SignupServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
String name=req.getParameter("name");
String email=req.getParameter("email");
String password=req.getParameter("password");
long phone=Long.parseLong(req.getParameter("phone"));
String address=req.getParameter("address");
ServletContext context=getServletContext();
double fees=Double.parseDouble(context.getInitParameter("fees"));
String schoolName=context.getInitParameter("schoolName");

Student student=new Student();
student.setAddress(address);
student.setEmail(email);
student.setFees(fees);
student.setName(name);
student.setPassword(password);
student.setPhone(phone);
student.setSchoolName(schoolName);

StudentDao dao=new StudentDao();
Student  dbStudent=dao.saveStudent(student);

if(dbStudent!=null) {
	req.setAttribute("message", "SignedUpSuccessfully");
	RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
	dispatcher.forward(req, resp);
}
}
}
