package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.model.Department;
import cz.muni.fi.pv168.project.ui.dialog.EmployeeDialog;
import cz.muni.fi.pv168.project.ui.model.EmployeeTableModel;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class DetailsAction extends AbstractAction {

    private final JTable employeeTable;
    private final ListModel<Department> departmentListModel;

    public DetailsAction(JTable employeeTable, ListModel<Department> departmentListModel) {
        super("Edit", Icons.DETAILS_ICON);
        this.employeeTable = employeeTable;
        this.departmentListModel = departmentListModel;
        putValue(SHORT_DESCRIPTION, "Show details of selected item");
        putValue(MNEMONIC_KEY, KeyEvent.VK_D);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl D"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int[] selectedRows = employeeTable.getSelectedRows();
        if (selectedRows.length != 1) {
            throw new IllegalStateException("Invalid selected rows count (must be 1): " + selectedRows.length);
        }
        if (employeeTable.isEditing()) {
            employeeTable.getCellEditor().cancelCellEditing();
        }
        var employeeTableModel = (EmployeeTableModel) employeeTable.getModel();
        int modelRow = employeeTable.convertRowIndexToModel(selectedRows[0]);
        var employee = employeeTableModel.getEntity(modelRow);
        var dialog = new EmployeeDialog(employee, departmentListModel);
        dialog.show(employeeTable, "Edit Employee")
                .ifPresent(employeeTableModel::updateRow);
    }
}
