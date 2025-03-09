package cz.muni.fi.pv168.project.ui.model;

import cz.muni.fi.pv168.project.model.Department;
import cz.muni.fi.pv168.project.model.Employee;
import cz.muni.fi.pv168.project.model.Gender;
import cz.muni.fi.pv168.project.model.Transaction;
import cz.muni.fi.pv168.project.model.TransactionType;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class RecurringTransactionTableModel extends AbstractTableModel {

    private final List<Transaction> transactions;

    public RecurringTransactionTableModel(List<Transaction> transactions) {
        this.transactions = new ArrayList<>(transactions);
    }

    @Override
    public int getRowCount() {
        return transactions.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var transaction = getEntity(rowIndex);
        switch (columnIndex) {
            case 0:
                return transaction.getDescription();
            case 1:
                return transaction.getDate();
            case 2:
                return transaction.getEndOfRecurrence();
            case 3:
                return transaction.getCategories();
            case 4:
                return transaction.getAmount();
            case 5:
                return transaction.getTransactionType();
            default:
                throw new IndexOutOfBoundsException("Invalid column index: " + columnIndex);
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Description";
            case 1:
                return "Date";
            case 2:
                return "End of recurrence";
            case 3:
                return "Categories";
            case 4:
                return "Amount";
            case 5:
                return "Type";
            default:
                throw new IndexOutOfBoundsException("Invalid column index: " + columnIndex);
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
            case 2:
                return LocalDate.class;
            case 3:
                return String.class; //TODO not sure how to handle list
            case 4:
                return int.class;
            case 5:
                return TransactionType.class;
            default:
                throw new IndexOutOfBoundsException("Invalid column index: " + columnIndex);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void deleteRow(int rowIndex) {
        transactions.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(Transaction transaction) {
        int newRowIndex = transactions.size();
        transactions.add(transaction);
        fireTableRowsInserted(newRowIndex, newRowIndex);
    }

    public void updateRow(Transaction transaction) {
        int rowIndex = transactions.indexOf(transaction);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    public Transaction getEntity(int rowIndex) {
        return transactions.get(rowIndex);
    }
}
