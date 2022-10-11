package serie04;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class StdContact implements Contact, Comparable {

	String firstName;
	String lastName;
	Civ civility;
	
	public StdContact(String n, String p) {
		if (n != null && p != null &&
			n != "" && p != "") {
			lastName = n;
			firstName = p;
			civility = Civ.UKN;
		}
	}
	
	public StdContact(Civ c, String n, String p) {
		if (n != null && p != null &&
				n != "" && p != "") {
				lastName  = n;
				firstName = p;
				civility = c;
			}
		}
	
	@Override
	public int compareTo(Object o) {
		// isInstanceOf
		if ((o instanceof Contact))
			return 2;
//		try {
//			Contact newContact = (Contact) o;
//			if (sgn(((StdContact) contact).compareTo(newContact)) == -sgn(newContact.compareTo(contact)))
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		return 1;
	}

	// Correcte
	@Override
	public Civ getCivility() {
		return civility;
	}

	// Correcte
	@Override
	public String getFirstName() {
		return firstName;
	}

	// Correcte
	@Override
	public String getLastName() {
		return lastName;
	}

	//??
	@Override
	public Contact evolve(Civ newCivility) {
		return new StdContact(newCivility, firstName, lastName);
	}
}
