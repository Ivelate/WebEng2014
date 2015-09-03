package ivelate.p1.todo;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
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
	public void removeToDoAt(int index)
	{
		this.todoList.remove(index);
	}
	public boolean removeToDoWithTask(String task)
	{
		boolean removed=false;
		Iterator<ToDo> iterator=this.todoList.iterator();
		while(iterator.hasNext())
		{
			ToDo t=iterator.next();
			if((t.getTask()==null&&task==null )|| (t.getTask()!=null&&t.getTask().equals(task)))
			{
				iterator.remove();
				removed=true;
			}
		}
		
		return removed;
	}
	public void printList(PrintStream ps)
	{
		if(todoList.size()==0) ps.println("Empty list");
		int cont=1;
		for(ToDo td:this.todoList)
		{
			ps.println("ToDo "+cont+":");
			ps.println(td);
			ps.println();
			cont++;
		}
	}
	public int getSize()
	{
		return this.todoList.size();
	}
	
	public List<ToDo> getRawList()
	{
		return this.todoList;
	}
}
