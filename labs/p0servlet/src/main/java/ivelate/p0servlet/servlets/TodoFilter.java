package ivelate.p0servlet.servlets;

import ivelate.p0servlet.todo.Priority;
import ivelate.p0servlet.todo.ToDo;
import ivelate.p0servlet.todo.ToDoList;

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
@WebServlet(urlPatterns = { "/todoFilter" })
public class TodoFilter extends HttpServlet {
	public final static String DEFAULT_FILE_NAME = "ToDoList.json";
	
	private ToDoList todoList;
	
	@Override
	public void init() throws ServletException {
		todoList = new ToDoList();
		Gson gson = new Gson();

		// Read the existing ToDo list
		try {
			todoList = gson.fromJson(new FileReader(DEFAULT_FILE_NAME),
					ToDoList.class);
		} catch (FileNotFoundException e) {
			System.out.println(DEFAULT_FILE_NAME
					+ ": File not found.  ToDos can't be loaded. (WARNING)");
			todoList=new ToDoList();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String task = req.getParameter("task_text");
		String context = req.getParameter("context_text");
		String project = req.getParameter("project_text");
		String priorityS = req.getParameter("priority_text");
		String priority_filter=req.getParameter("priority_filter");
		int priority_filter_num=priority_filter.equals("le")? 	ToDoList.PRIORITYFILTER_LE : 
			priority_filter.equals("ge")? 						ToDoList.PRIORITYFILTER_GE : 
																ToDoList.PRIORITYFILTER_EQ ;
		Priority priority=null;
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
			warningMessage+="<p>Unknown priority. Not applying priority filter </p>";
		}
		
		//Applies filters
		List<ToDo> matchedToDos=todoList.getToDosWhoMatches(task, context, project, priority, priority_filter_num);
		if(matchedToDos.isEmpty()) warningMessage+="<p>No ToDos found who matches the introduced criteria</p>";

		//Writes response
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.print("<html><head><title>Matching ToDos</title></head>"
				+ "<body><h1> Matching ToDos </h1>"+warningMessage+"<table>");
		//Writes each filtered ToDo in HTML
		for(ToDo t:matchedToDos)
		{
			out.print(t.toHTML());
		}
		out.println("</table></body></html>");
	}
}
