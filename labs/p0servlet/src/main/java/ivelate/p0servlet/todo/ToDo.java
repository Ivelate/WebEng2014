package ivelate.p0servlet.todo;

public class ToDo 
{
	private String task;
	private String context;
	private String project;
	private Priority priority;
	
	public ToDo(String task,String context,String project,Priority priority)
	{
		setTask(task);
		setContext(context);
		setProject(project);
		setPriority(priority);
	}
	
	@Override
	public String toString()
	{
		return   "Task:     "+getTask()+"\n"
				+"Context:  "+getContext()+"\n"
				+"Project:  "+getProject()+"\n"
				+"Priority: "+getPriority().name()+"\n";
	}
	public String toHTML()
	{
		return "<tr><td>"+getTask()+"</td><td>"+getContext()+"</td><td>"+getProject()+"</td><td>"+getPriority()+"</td></tr>";
	}
	/******** GETTERS AND SETTERS **********/
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	
}
