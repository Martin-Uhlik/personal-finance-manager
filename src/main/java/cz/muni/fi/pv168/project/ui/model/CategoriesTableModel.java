package cz.muni.fi.pv168.project.ui.model;

import cz.muni.fi.pv168.project.model.Category;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CategoriesTableModel extends AbstractTableModel {

    private final List<Category> categories;

    public CategoriesTableModel(List<Category> categories) {
        this.categories = new ArrayList<>(categories);
    }

    @Override
    public int getRowCount() {
        return categories.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var category = getEntity(rowIndex);
        if (columnIndex == 0) {
            return category;
        }
        throw new IndexOutOfBoundsException("Invalid column index: " + columnIndex);
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == 0) {
            return "Category Name";
        }
        throw new IndexOutOfBoundsException("Invalid column index: " + columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return String.class;
        }
        throw new IndexOutOfBoundsException("Invalid column index: " + columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void deleteRow(int rowIndex) {
        categories.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(Category category) {
        int newRowIndex = categories.size();
        categories.add(category);
        fireTableRowsInserted(newRowIndex, newRowIndex);
    }

    public void updateRow(Category category) {
        int rowIndex = categories.indexOf(category);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    public Category getEntity(int rowIndex) {
        return categories.get(rowIndex);
    }
}
