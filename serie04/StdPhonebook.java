package serie04;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableMap;
import java.util.NavigableSet;

public class StdPhonebook<C extends Contact & Comparable<C>, N> implements PhoneBook<C, N> {

	
	
	NavigableSet<C> contactsSet;
	NavigableMap<C, List<N>> contactsMap;
	// Bob 07010203
	// Bob 
	// Alice 08040506
	// ...
	
	PhoneBook<C, N> phoneBook;
	
	
	//?
	public NavigableSet<C> contacts() {
		for (C contact : contactsMap.keySet()) {
			contactsSet.add(contact);
		}
		return contactsSet;
	}
	
	public StdPhonebook() {
		super();
		
	}
	
	//?
	public boolean contains(C p) {
		if (p != null && phoneBook.contacts().contains(p))
			return true;
		return false;
	}

	
	// ?
	@Override
	public boolean isEmpty() {
		return contacts().size() == 0;
	}

	@Override
	public List<N> phoneNumbers(C p) {
		List<N> phoneNumbersList = new ArrayList<N>();
		for (C contact : contactsMap.keySet()) {
			if (contact.equals(p)) {
				phoneNumbersList =  contactsMap.get(contact);
				return phoneNumbersList; 
			}
		}
		return phoneNumbersList;
	}

	@Override
	public void addEntry(C p, N n) {
		List<N> phoneNumbersList = phoneNumbers(p);
		phoneNumbersList.add(n);
		contactsMap.put(p, phoneNumbersList);
	}

	@Override
	public void addEntry(C p, List<N> nums) {
		List<N> phoneNumbersList = phoneNumbers(p);
		phoneNumbersList.addAll(nums);
		contactsMap.put(p, phoneNumbersList);
	}

	@Override
	public void addPhoneNumber(C p, N n) {
		List<N> phoneNumbersList = phoneNumbers(p);
		phoneNumbersList.add(n);
		contactsMap.put(p, phoneNumbersList);
	}

	//?
	@Override
	public void clear() {
		contactsMap.clear();
		contactsSet.clear();
		phoneBook.clear();
	}

	@Override
	public void deletePhoneNumber(C p, N n) {
		Iterator phonesIter = contacts().descendingIterator();
		while (phonesIter.hasNext()) {
			Object obj = phonesIter.next();
		}
	}

	@Override
	public void removeEntry(C p) {
		Iterator<C> iter = contactsSet.iterator();
		while (iter.hasNext()) {
			C next = iter.next();
			//????
			if (next == p)
				iter.remove();
		}
		
	}


}
