package phrase.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListDataListener;

import util.Contract;

public class StdFilteringListModel extends AbstractListModel implements FilteringListModel {

	private List<String> values;
	private List<String> unfilteredValues;
	private Filter filter;
	private PropertyChangeListener pcl;
	
	public StdFilteringListModel() {
		values = new ArrayList<String>();
		unfilteredValues = new ArrayList<String>();
		pcl = new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				filter = getFilter();
				if (filter == null)
					values = new ArrayList<String>(unfilteredValues);
				else
					values = filter.filter(unfilteredValues);
			}
		};
	}

	@Override
	public String getElementAt(int i) {
		return values.get(i);
	}

	@Override
	public Filter getFilter() {
		return filter;
	}

	@Override
	public int getSize() {
		return values.size();
	}

	@Override
	public String getUnfilteredElementAt(int i) {
		return unfilteredValues.get(i);
	}

	@Override
	public int getUnfilteredSize() {
		return unfilteredValues.size();
	}

	@Override
	public void addElement(String element) {
		Contract.checkCondition(element != null, "Un élément est null");
		
		unfilteredValues.add(element);
		if (filter == null || filter.accept(element)) {
			values.add(element);
			fireContentsChanged(this.filter, getSize()-1, getSize()-1);
		}
	}

	@Override
	public void setElements(Collection<String> c) {
		values = new ArrayList<String>(c);
	}

	@Override
	public void setFilter(Filter filter) {
		if (this.filter != null)
			this.filter.removePropertyChangeListener(Filter.PROP_VALUE, pcl);
		this.filter = filter;
		this.filter.addPropertyChangeListener(Filter.PROP_VALUE, pcl);
		values = this.filter.filter(unfilteredValues);
		fireContentsChanged(this.filter, 0, getSize()-1);
	}
	
}
