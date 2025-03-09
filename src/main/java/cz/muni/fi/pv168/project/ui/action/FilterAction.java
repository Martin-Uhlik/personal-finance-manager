package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.model.Department;
import cz.muni.fi.pv168.project.ui.dialog.EmployeeDialog;
import cz.muni.fi.pv168.project.ui.model.EmployeeTableModel;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class FilterAction extends AbstractAction {

    private final JTable employeeTable;
    private final TestDataGenerator testDataGenerator;
    private final ListModel<Department> departmentListModel;

    public FilterAction(JTable employeeTable, TestDataGenerator testDataGenerator, ListModel<Department> departmentListModel) {
        super("Add", Icons.FILTER_ICON);
        this.employeeTable = employeeTable;
        this.testDataGenerator = testDataGenerator;
        this.departmentListModel = departmentListModel;
        putValue(SHORT_DESCRIPTION, "Filter items");
        putValue(MNEMONIC_KEY, KeyEvent.VK_F);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl F"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var employeeTableModel = (EmployeeTableModel) employeeTable.getModel();
        var dialog = new EmployeeDialog(testDataGenerator.createTestEmployee(), departmentListModel);
        dialog.show(employeeTable, "Add Employee")
                .ifPresent(employeeTableModel::addRow);
    }
}
