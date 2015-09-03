package ivelate.p1.soapserver;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import ivelate.p1.todo.*;

@WebService
public class TodoWebService {
	
	@WebMethod
	public void addToDo(String task,String context,String project,Priority priority)
	{
		ToDoFileManager.readFile();
		ToDoFileManager.getToDoList().addToDo(new ToDo(task,context,project,priority));
		ToDoFileManager.saveFile();
	}
	@WebMethod
	public boolean removeToDo(String task)
	{
		ToDoFileManager.readFile();
		boolean removed=ToDoFileManager.getToDoList().removeToDoWithTask(task);
		
		
		if(removed) ToDoFileManager.saveFile();
		
		return removed;
	}
	@WebMethod
	public List<ToDo> listToDos()
	{
		ToDoFileManager.readFile();
		return ToDoFileManager.getToDoList().getRawList();
	}

}
