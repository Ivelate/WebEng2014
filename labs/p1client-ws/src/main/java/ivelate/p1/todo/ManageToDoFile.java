package ivelate.p1.todo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

import com.google.gson.Gson;

public class ManageToDoFile {

	public final static String DEFAULT_FILE_NAME = "ToDoList.json";
	
	private static final String  MENU_INFO="info";
	private static final String MENU_ADD="add";
	private static final String MENU_REMOVE="rem";
	private static final String MENU_PRINT="print";
	private static final String MENU_EXIT="exit";
	private static final Priority DEFAULT_PRIORITY=Priority.MEDIUM; //Default priority
	
	/**
	 * Creates a ToDo object based on a provided input
	 */
	private static ToDo createTodoFromInput(Scanner input)
	{
		System.out.println("Insert ToDo task: ");
		String task=input.nextLine();
		System.out.println("Insert ToDo context: ");
		String context=input.nextLine();
		System.out.println("Insert ToDo project: ");
		String project=input.nextLine();
		System.out.print("Insert ToDo priority ( ");
		for(Priority p:Priority.values())
		{
			System.out.print(p.name()+" ");
		}
		System.out.println("):");
		String priorityS=input.nextLine();
		Priority priority=null;
		for(Priority p:Priority.values())
		{
			if(priorityS.toUpperCase().equals(p.name())) {
				priority=p;
				break;
			}
		}
		if(priority==null){
			System.out.println("Unknown priority. Setting to "+DEFAULT_PRIORITY.name());
			priority=DEFAULT_PRIORITY;
		}
		return new ToDo(task,context,project,priority);
	}
	public static void main(String[] args) throws Exception {
		Scanner input=new Scanner(System.in);
		
		boolean finish=false;
		printMenu();
		while(!finish)
		{
			System.out.println();
			System.out.print("Insert command: ");
			String selectedOption="";
			try
			{
				selectedOption=input.nextLine();
				switch(selectedOption)
				{
				case MENU_INFO:
					printMenu();
					break;
				case MENU_ADD:
					todoList.addToDo(createTodoFromInput(input));
					System.out.println("Done");
					break;
				case MENU_REMOVE:
					if(todoList.getSize()==0){
						System.out.println("ToDo file is empty");
					} 
					else {
						System.out.println("Insert ToDo number (1,"+todoList.getSize()+") to delete (0 to cancel)");
						int num=getNumBetween(0,todoList.getSize(),input);
						if(num!=0)
						{
							todoList.removeToDoAt(num-1);
							System.out.println("Done");
						}
					}
					break;
				case MENU_PRINT:
					todoList.printList(System.out);
					break;
				case MENU_EXIT:
					finish=true;
					break;
				default:
					System.out.println("Unknown option. Type "+MENU_INFO+" to show all options available");
	
				}
			}
			catch(NumberFormatException e)
			{
				System.out.println("Invalid input, try again");
			}
		}

		// Write the new ToDo file back to disk.
		FileWriter output = new FileWriter(filename);
		output.write(gson.toJson(todoList));
		input.close();
		output.close();
	}
	/**
	 * Prints the program menu
	 */
	private static void printMenu()
	{
		System.out.println("Select an option: "+MENU_ADD+" - Add a new ToDo to the list");
		System.out.println("                  "+MENU_REMOVE+" - Delete a ToDo from the list");
		System.out.println("                  "+MENU_PRINT+" - Print complete ToDo list");
		System.out.println("                  "+MENU_INFO+" - Print this menu");
		System.out.println("                  "+MENU_EXIT+" - Exit");
	}
	/**
	 * Returns a number between <i> and <f> based on the provided input <s>
	 */
	private static int getNumBetween(int i,int f,Scanner s)
	{
		boolean done=false;
		while(!done)
		{
			try
			{
				int toRet=Integer.parseInt(s.nextLine());
				if(toRet<i||toRet>f) System.out.println("Please insert a number between "+i+" and "+f);
				else return toRet;
			} catch( NumberFormatException e) {
				System.out.println("Please insert a valid number");
			}
		}
		//Program never reaches this part
		return -1;
	}
}
