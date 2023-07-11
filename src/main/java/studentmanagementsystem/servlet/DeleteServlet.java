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
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 int id=Integer.parseInt(req.getParameter("id"));
 
 StudentDao dao=new StudentDao();
 Student student=dao.deleteStudentById(id);
 
 List<Student> list=dao.getAllStudents();
 if(student!=null) {
	 req.setAttribute("list", list);
	 RequestDispatcher dispatcher=req.getRequestDispatcher("display.jsp");
	 dispatcher.forward(req, resp);
 }
 
}
}
