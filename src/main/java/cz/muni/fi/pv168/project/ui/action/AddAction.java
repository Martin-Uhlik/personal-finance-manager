package cz.muni.fi.pv168.project.ui.action;

import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.model.Department;
import cz.muni.fi.pv168.project.ui.dialog.CategoryDialog;
import cz.muni.fi.pv168.project.ui.dialog.EmployeeDialog;
import cz.muni.fi.pv168.project.ui.dialog.RecurringTransactionDialog;
import cz.muni.fi.pv168.project.ui.dialog.TransactionDialog;
import cz.muni.fi.pv168.project.ui.model.EmployeeTableModel;
import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public final class AddAction extends AbstractAction {

    private final JTable table;
    private final ListModel<Department> departmentListModel;
    private final int selector;

    public AddAction(JTable table, ListModel<Department> departmentListModel, int selector) {
        super("Add", Icons.ADD_ICON);
        this.table = table;
        this.departmentListModel = departmentListModel;
        this.selector = selector;
        putValue(SHORT_DESCRIPTION, "Adds new item");
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //var employeeTableModel = (EmployeeTableModel) table.getModel();
        Object dialog;
        switch (selector) {
            case 0:
                dialog = new TransactionDialog();
                ((TransactionDialog) dialog).show(table, "Add Transaction");
                break;
            case 1:
                dialog = new RecurringTransactionDialog();
                ((RecurringTransactionDialog) dialog).show(table, "Add Recurring Transaction");
                break;
            case 2:
                dialog = new CategoryDialog();
                ((CategoryDialog) dialog).show(table, "Add Category");

        }

    }
}
