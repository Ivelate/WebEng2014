package ivelate.p1.todo;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import ivelate.p1.soapserver.ToDo;

public class ToDoList 
{
	public final static int PRIORITYFILTER_GE = 1;
	public final static int PRIORITYFILTER_LE = -1;
	public final static int PRIORITYFILTER_EQ = 0;
	
	private List<ToDo> todoList = new ArrayList<ToDo>();
	
	public ToDoList(List<ivelate.p1.soapserver.ToDo> listToDos) {
		for(ToDo td:listToDos)
		{
			this.addToDo(td);
		}
	}
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
	public List<ToDo> getToDosWhoMatches(String task,String context,String project,Priority priority,int prioritySearchType)
	{
		List<ToDo> toRet=new ArrayList<ToDo>();
		for(ToDo t: this.todoList)
		{
			boolean valid=true;
			
			//Check task if filter not empty
			if(task!=null&&!task.isEmpty())
			{
				if(!task.equals(t.getTask())) {valid=false; continue; }
			}
			//Check context if filter not empty
			if(context!=null&&!context.isEmpty())
			{
				if(!context.equals(t.getContext())) {valid=false; continue; }
			}
			//Check project if filter not empty
			if(project!=null&&!project.isEmpty())
			{
				if(!project.equals(t.getProject())) {valid=false; continue; }
			}
			//Check priority if filter not null
			if(priority!=null)
			{
				switch(prioritySearchType)
				{
				case PRIORITYFILTER_EQ:
					if(!priority.equals(t.getPriority())) valid=false;
					break;
				case PRIORITYFILTER_GE:
					if(priority.ordinal()<t.getPriority().ordinal()) valid=false;
					break;
				case PRIORITYFILTER_LE:
					if(priority.ordinal()>t.getPriority().ordinal()) valid=false;
					break;
				}
			}
			
			if(valid) toRet.add(t);
		}
		return toRet;
	}
}
