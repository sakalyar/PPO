package notes.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class DefaultNoteTableModel extends AbstractTableModel implements NoteTableModel  {

	private static final long serialVersionUID = 1L;
	private List<Object[]> rows;
	
	public DefaultNoteTableModel() {}

	@Override
	public int getColumnCount() {
		return ColumnFeature.values().length;
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		return rows.get(row)[column];
	}


	@Override
	public void addEmptyRow() {
		rows.add(new Object[getColumnCount()-1]);
		fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
	}

	@Override
	public void addRow(Object[] row) {
		rows.add(row);
		fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
	}

	@Override
	public void clearRows() {
		rows.clear();
		fireTableDataChanged();
	}

	@Override
	public void insertEmptyRow(int index) {
		rows.add(index, new Object[getColumnCount()-1]);
		fireTableRowsInserted(index, index);
	}

	@Override
	public void insertRow(int index, Object[] row) {
		rows.add(index, row);
		fireTableRowsInserted(index, index);

	}

	@Override
	public void removeRow(int index) {
		rows.remove(index);
		fireTableRowsDeleted(index, index);
	}

	@Override
	public void setValueAt(Object value, int row, int column) {
		Object[] x = new Object[] {rows.get(row)};
		rows.set(row, x);
		fireTableDataChanged();
	}

}
