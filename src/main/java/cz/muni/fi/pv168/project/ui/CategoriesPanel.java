package cz.muni.fi.pv168.project.ui;

import cz.muni.fi.pv168.project.data.TestDataGenerator;
import cz.muni.fi.pv168.project.model.Category;
import cz.muni.fi.pv168.project.model.Gender;
import cz.muni.fi.pv168.project.ui.action.AddAction;
import cz.muni.fi.pv168.project.ui.action.DeleteAction;
import cz.muni.fi.pv168.project.ui.action.EditAction;
import cz.muni.fi.pv168.project.ui.model.CategoriesTableModel;
import cz.muni.fi.pv168.project.ui.model.DepartmentListModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Uhlik
 */
public class CategoriesPanel extends JPanel {

    private final Action addAction;
    private final Action deleteAction;
    private final Action editAction;

    public CategoriesPanel() {
        super();
        setLayout(new BorderLayout());

        var testDataGenerator = new TestDataGenerator();
        var categoryTable = createCategoryTable(new ArrayList<>()); //TODO load data from database
        var departmentListModel = new DepartmentListModel(new ArrayList<>()); //TODO load data from database
        addAction = new AddAction(categoryTable, departmentListModel, 2);
        deleteAction = new DeleteAction(categoryTable);
        deleteAction.setEnabled(false);
        editAction = new EditAction(categoryTable, departmentListModel);
        editAction.setEnabled(false);
        categoryTable.setComponentPopupMenu(createEmployeeTablePopupMenu());

        add(createToolbar(), BorderLayout.BEFORE_FIRST_LINE);

        JScrollPane scrollPane = new JScrollPane(categoryTable);
        add(scrollPane);
    }

    private JToolBar createToolbar() {
        var toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.add(addAction);
        toolbar.add(deleteAction);
        toolbar.add(editAction);
        return toolbar;
    }

    private JTable createCategoryTable(List<Category> categories) {
        var model = new CategoriesTableModel(categories);
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
        } else {
            editAction.setEnabled(true);
            deleteAction.setEnabled(true);
        }
    }
}
