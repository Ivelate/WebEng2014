package ivelate.p1.soapclient;

import ivelate.p1.soapserver.Priority;
import ivelate.p1.soapserver.TodoWebService;
import ivelate.p1.soapserver.TodoWebServiceService;
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
@WebServlet(urlPatterns = { "/todoAdd" })
public class TodoAdd extends HttpServlet 
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
		String context = req.getParameter("context_text");
		String project = req.getParameter("project_text");
		String priorityS = req.getParameter("priority_text");
		if(priorityS==null) priorityS="";
		Priority priority=	priorityS.equals("VERY-HIGH")? Priority.VERY_HIGH:
							priorityS.equals("HIGH")? Priority.HIGH:
							priorityS.equals("LOW")? Priority.LOW:
							priorityS.equals("VERY-LOW")? Priority.VERY_LOW:
							Priority.MEDIUM;
	
		String warningMessage="";
		for(Priority p:Priority.values())
		{
			if(priorityS.toUpperCase().equals(p.name())) {
				priority=p;
				break;
			}
		}
		//Shows a warning if the introduced priority doesnt exists and the field wasn't empty (Considering that the user misswrited the priority)
		if(priority==null && !priorityS.isEmpty()){
			warningMessage+="<p>Unknown priority. Setting default to MEDIUM </p>";
		}
		
		this.tws.addToDo(task,context,project,priority);
		
		//Writes response
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.print("<html><head><title>ToDo added</title></head>"
				+ "<body><h1> ToDo succesfully added </h1>"+warningMessage+"<table>");

		//Writes added ToDo
		out.print("<tr><td>"+task+"</td><td>"+context+"</td><td>"+project+"</td><td>"+priority+"</td></tr>");
		out.println("</table></body></html>");
	}
}
