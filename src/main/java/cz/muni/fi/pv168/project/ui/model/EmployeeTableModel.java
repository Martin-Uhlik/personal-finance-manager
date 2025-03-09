package cz.muni.fi.pv168.project.ui.model;

import cz.muni.fi.pv168.project.model.Department;
import cz.muni.fi.pv168.project.model.Employee;
import cz.muni.fi.pv168.project.model.Gender;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTableModel extends AbstractTableModel {

    private final List<Employee> employees;

    public EmployeeTableModel(List<Employee> employees) {
        this.employees = new ArrayList<>(employees);
    }

    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var employee = getEntity(rowIndex);
        switch (columnIndex) {
            case 0:
                return employee.getGender();
            case 1:
                return Period.between(employee.getBirthDate(), LocalDate.now()).getYears();
            case 2:
                return employee.getLastName();
            case 3:
                return employee.getFirstName();
            case 4:
                return employee.getBirthDate();
            case 5:
                return employee.getDepartment();
            default:
                throw new IndexOutOfBoundsException("Invalid column index: " + columnIndex);
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Gender";
            case 1:
                return "Age";
            case 2:
                return "Last Name";
            case 3:
                return "First Name";
            case 4:
                return "Birth date";
            case 5:
                return "Department";
            default:
                throw new IndexOutOfBoundsException("Invalid column index: " + columnIndex);
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Gender.class;
            case 1:
                return int.class;
            case 2:
            case 3:
                return String.class;
            case 4:
                return LocalDate.class;
            case 5:
                return Department.class;
            default:
                throw new IndexOutOfBoundsException("Invalid column index: " + columnIndex);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 2:
            case 3:
                return true;
            case 1:
            case 4:
            case 5:
                return false;
            default:
                throw new IndexOutOfBoundsException("Invalid column index: " + columnIndex);
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        var employee = employees.get(rowIndex);
        switch (columnIndex) {
            case 0:
                employee.setGender(Gender.valueOf(value.toString()));
                break;
            case 3:
                employee.setFirstName((String) value);
                break;
            case 2:
                employee.setLastName((String) value);
                break;
            default:
                throw new IndexOutOfBoundsException("Invalid column index: " + columnIndex);
        }
    }

    public void deleteRow(int rowIndex) {
        employees.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(Employee employee) {
        int newRowIndex = employees.size();
        employees.add(employee);
        fireTableRowsInserted(newRowIndex, newRowIndex);
    }

    public void updateRow(Employee employee) {
        int rowIndex = employees.indexOf(employee);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    public Employee getEntity(int rowIndex) {
        return employees.get(rowIndex);
    }
}
