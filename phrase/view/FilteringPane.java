package phrase.view;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import phrase.model.Filter;
import phrase.model.FilteringListModel;
import phrase.model.StdFilteringListModel;
import util.Contract;

public class FilteringPane extends JPanel {

    // ATTRIBUTS

    private FilteringListModel listModel;
    private JComboBox filters;
    private JTextField filterValue;
    private JList filteringList;
    
    private Filter f;
    private String v = "";
    // CONSTRUCTEURS

    public FilteringPane(Filter[] filters) {
        super(new BorderLayout());

        createModel();
        createView(filters);
        placeComponents();
        createController();
        
        setCurrentFilterFromSelectedItem();
        setCurrentFilterValueFromTextField();
    }

    // REQUETES

    public JComboBox getCombo() {
        return filters;
    }

    public JTextField getTextField() {
        return filterValue;
    }

    public JList getList() {
        return filteringList;
    }

    // COMMANDES

    /**
     * Ajoute un élément à la fin du modèle de getList().
     * @pre <pre>
     *     element != null </pre>
     * @post <pre>
     *     Let m ::= (FilteringListModel) getList().getModel()
     *     m.getUnfilteredSize() == old m.getUnfilteredSize() + 1
     *     m.getUnfilteredElementAt(m.getUnfilteredSize() - 1) == element </pre>
     */
    public void addElement(String element) {
        Contract.checkCondition(element != null);

        listModel.addElement(element);
    }

    /**
     * Remplace les éléments du modèle de getList() par ceux de c.
     * @pre <pre>
     *     c != null </pre>
     * @post <pre>
     *     Let m ::= (FilteringListModel) getList().getModel()
     *     m.getUnfiltnulleredSize() == c.getSize()
     *     forall s in c :
     *         exists int i, 0 <= i < m.getUnfilteredSize() :
     *             m.getUnfilteredElementAt(i) == s </pre>
     */
    public void setElements(Collection<String> c) {
        Contract.checkCondition(c != null);

        listModel.setElements(c);
    }

    // OUTILS

    private void createModel() {
        listModel = new StdFilteringListModel();
    }

    private void createView(Filter[] allFilters) {
        filters = new JComboBox(new DefaultComboBoxModel(allFilters));
        filterValue = new JTextField();
        filteringList = new JList(listModel);
    }

    private void placeComponents() {
        JPanel p = new JPanel(new BorderLayout());
        { //--
            p.setBorder(BorderFactory.createTitledBorder("Recherche"));
            p.add(filters, BorderLayout.WEST);
            p.add(filterValue, BorderLayout.CENTER);
            /** A COMPLETER **/
            /*****************/
        } //--
        add(p, BorderLayout.NORTH);

        JScrollPane jsp = new JScrollPane(filteringList);
        { //--
            jsp.setBorder(BorderFactory.createTitledBorder("Résultat"));
        } //--
        add(jsp, BorderLayout.CENTER);
    }

    private void createController() {
    	filters.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				setCurrentFilterFromSelectedItem();
				v = filterValue.getText().toString();
				f.setValue(v);
			}
    	});
    	
    	filterValue.getDocument()
    	.addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				v = filterValue.getText().toString();
				setCurrentFilterValueFromTextField();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
			}
    	});
    }

    private void setCurrentFilterFromSelectedItem() {
    	f = (Filter)filters.getSelectedItem();
    	listModel.setFilter(f);
    }

    private void setCurrentFilterValueFromTextField() {
    	f = (Filter)filters.getSelectedItem();
		f.setValue(v);
    }
}
