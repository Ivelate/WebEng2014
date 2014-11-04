package ivelate.p1.soapserver;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import ivelate.p1.todo.*;

@WebService
public class TodoWebService {
	
	@WebMethod
	public void addToDo(ToDo t)
	{
		ToDoFileManager.getToDoList().addToDo(t);
	}
	@WebMethod
	public void removeToDo(ToDo t)
	{
		ToDoFileManager.getToDoList().removeToDo(t);
	}
	/*@WebMethod
	public List<ToDo> listToDos()
	{
		ToDoFileManager.getToDoList().;
	}*/

}
