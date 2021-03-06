package ivelate.p2.restclient;

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

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import ivelate.p2.todo.Priority;
import ivelate.p2.todo.ToDo;
import ivelate.p2.todo.ToDoList;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/todoList" })
public class TodoList extends HttpServlet 
{
	
	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpResponse<JsonNode> list=null;
		try {
			list = Unirest.get("http://localhost:8081/todoRestService").asJson();
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		ToDoList todoList = new ToDoList(list);
		
		String task = req.getParameter("task_text");
		String context = req.getParameter("context_text");
		String project = req.getParameter("project_text");
		String priorityS = req.getParameter("priority_text");
		String priority_filter=req.getParameter("priority_filter");
		if(priority_filter==null) priority_filter="";
		if(priorityS==null) priorityS="";
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
			out.print("<tr><td>"+t.getTask()+"</td><td>"+t.getContext()+"</td><td>"+t.getProject()+"</td><td>"+t.getPriority()+"</td></tr>");
		}
		out.println("</table></body></html>");
	}
}
