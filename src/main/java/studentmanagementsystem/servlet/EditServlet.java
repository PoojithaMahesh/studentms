package studentmanagementsystem.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentmanagementsystem.dao.StudentDao;
import studentmanagementsystem.dto.Student;

@WebServlet("/edit")
public class EditServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
int id=Integer.parseInt(req.getParameter("id"));
String name=req.getParameter("name");
String email=req.getParameter("email");
String password=req.getParameter("password");
Long phone=Long.parseLong(req.getParameter("phone"));
String address=req.getParameter("address");


Student student=new Student();
student.setName(name);
student.setAddress(address);
student.setEmail(email);
student.setPassword(password);
student.setPhone(phone);


StudentDao dao=new StudentDao();
Student dbStudent=dao.updateStudent(id,student);
if(dbStudent!=null) {
	List<Student> list=dao.getAllStudents();
	req.setAttribute("list", list);
	RequestDispatcher dispatcher=req.getRequestDispatcher("display.jsp");
	dispatcher.forward(req, resp);
}


}
}
