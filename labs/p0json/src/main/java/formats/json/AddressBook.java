package formats.json;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {

	private List<Person> personList = new ArrayList<Person>();

	public List<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Person> persons) {
		this.personList = persons;
	}

	public void addPerson(Person person) {
		personList.add(person);
	}
}
