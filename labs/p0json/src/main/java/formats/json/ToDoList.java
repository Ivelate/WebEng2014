package formats.json;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ToDoList 
{
	private List<ToDo> todoList = new ArrayList<ToDo>();
	
	public void addToDo(ToDo td)
	{
		this.todoList.add(td);
	}
	public void removeToDo(ToDo td)
	{
		this.todoList.remove(td);
	}
	public void printList(PrintStream ps)
	{
		if(todoList.size()==0) ps.println("Empty list");
		for(ToDo td:this.todoList)
		{
			ps.println(td);
			ps.println();
		}
	}
}
