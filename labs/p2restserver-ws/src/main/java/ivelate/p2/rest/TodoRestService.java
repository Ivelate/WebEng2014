package ivelate.p2.rest;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ivelate.p2.todo.Priority;
import ivelate.p2.todo.ToDo;

@Path("todoRestService")
public class TodoRestService
{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ToDo> getTodoList()
	{
		ToDoFileManager.readFile();
		return ToDoFileManager.getToDoList().getRawList();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTodo(@FormParam("task") String task,@FormParam("context") String context,@FormParam("project") String project,@FormParam("priority") Priority priority)
	{
		ToDoFileManager.readFile();
		if(!ToDoFileManager.getToDoList().updateTodoWithTask(task, context, project, priority)){
			ToDoFileManager.getToDoList().addToDo(new ToDo(task,context,project,priority));
		}
		ToDoFileManager.saveFile();
		
		return Response.ok().build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertTodo(@FormParam("task") String task,@FormParam("context") String context,@FormParam("project") String project,@FormParam("priority") Priority priority)
	{
		ToDoFileManager.readFile();
		ToDoFileManager.getToDoList().addToDo(new ToDo(task,context,project,priority));
		ToDoFileManager.saveFile();
		
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{task}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTodo(@PathParam("task") String task)
	{
		ToDoFileManager.readFile();
		if(ToDoFileManager.getToDoList().removeToDoWithTask(task))
		{
			ToDoFileManager.saveFile();
			return Response.ok().build();
		}
		else
		{
			return Response.status(404).build();
		}
	}
}
