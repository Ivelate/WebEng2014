package ivelate.p1.soapclient;

import ivelate.p1.soapserver.TodoWebService;
import ivelate.p1.soapserver.TodoWebServiceService;
import ivelate.p1.todo.Priority;
import ivelate.p1.todo.ToDoList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/todoRemove" })
public class TodoRemove extends HttpServlet 
{
	
	private TodoWebService tws;
	
	@Override
	public void init() throws ServletException {
		TodoWebServiceService twss=new TodoWebServiceService();
		tws=twss.getTodoWebServicePort();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String task = req.getParameter("task_text");
		boolean removed=this.tws.removeToDo(task);
		
		//Writes response
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.print("<html><head><title>Removing ToDo</title></head>"
				+ "<body><h1> ToDo with task "+task+" has "+(removed?"removed succesfully":"not been found in the ToDo database")+" </h1>");
		
		out.println("</body></html>");
	}
}
