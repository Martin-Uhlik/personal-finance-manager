package cz.muni.fi.pv168.project.ui;

import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.model.Gender;
import cz.muni.fi.pv168.project.model.Transaction;
import cz.muni.fi.pv168.project.ui.action.AddAction;
import cz.muni.fi.pv168.project.ui.action.DeleteAction;
import cz.muni.fi.pv168.project.ui.action.DetailsAction;
import cz.muni.fi.pv168.project.ui.action.EditAction;
import cz.muni.fi.pv168.project.ui.action.FilterAction;
import cz.muni.fi.pv168.project.ui.action.ResetFilterAction;
import cz.muni.fi.pv168.project.ui.model.DepartmentListModel;
import cz.muni.fi.pv168.project.ui.model.RecurringTransactionTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Uhlik
 */
public class RecurringTransactionPanel extends JPanel {

    private final Action addAction;
    private final Action deleteAction;
    private final Action editAction;
    private final Action detailsAction;
    private final Action filterAction;
    private final Action resetFilterAction;

    public RecurringTransactionPanel() {
        super();
        setLayout(new BorderLayout());

        var testDataGenerator = new TestDataGenerator();
        var employeeTable = createEmployeeTable(new ArrayList<>()); //TODO loaad data from database
        var departmentListModel = new DepartmentListModel(testDataGenerator.getDepartments());
        addAction = new AddAction(employeeTable, departmentListModel, 1);
        deleteAction = new DeleteAction(employeeTable);
        deleteAction.setEnabled(false);
        editAction = new EditAction(employeeTable, departmentListModel);
        editAction.setEnabled(false);
        detailsAction = new DetailsAction(employeeTable, departmentListModel);
        detailsAction.setEnabled(false);
        filterAction = new FilterAction(employeeTable, testDataGenerator, departmentListModel); //TODO
        resetFilterAction = new ResetFilterAction(employeeTable, testDataGenerator, departmentListModel); //TODOv
        employeeTable.setComponentPopupMenu(createEmployeeTablePopupMenu());

        add(createToolbar(), BorderLayout.BEFORE_FIRST_LINE);

        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane);
    }

    private JToolBar createToolbar() {
        var toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.add(addAction);
        toolbar.add(deleteAction);
        toolbar.add(editAction);
        toolbar.addSeparator();
        toolbar.add(detailsAction);
        toolbar.addSeparator();
        toolbar.add(filterAction);
        toolbar.add(resetFilterAction);
        return toolbar;
    }

    private JTable createEmployeeTable(List<Transaction> transactions) {
        var model = new RecurringTransactionTableModel(transactions);
        var table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.getSelectionModel().addListSelectionListener(this::rowSelectionChanged);
        var genderComboBox = new JComboBox<>(Gender.values());
        table.setDefaultEditor(Gender.class, new DefaultCellEditor(genderComboBox));
        return table;
    }

    private JPopupMenu createEmployeeTablePopupMenu() {
        var menu = new JPopupMenu();
        menu.add(addAction);
        menu.add(deleteAction);
        menu.add(editAction);
        return menu;
    }

    private void rowSelectionChanged(ListSelectionEvent listSelectionEvent) {
        var selectionModel = (ListSelectionModel) listSelectionEvent.getSource();

        if (selectionModel.getSelectedItemsCount() != 1) {
            editAction.setEnabled(false);
            deleteAction.setEnabled(false);
            detailsAction.setEnabled(false);
        } else {
            editAction.setEnabled(true);
            deleteAction.setEnabled(true);
            detailsAction.setEnabled(true);
        }
    }
}
