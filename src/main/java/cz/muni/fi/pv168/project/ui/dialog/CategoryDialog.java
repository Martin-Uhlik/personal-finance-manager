package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.model.Category;
import cz.muni.fi.pv168.project.model.Department;
import cz.muni.fi.pv168.project.model.Employee;
import cz.muni.fi.pv168.project.model.Gender;
import cz.muni.fi.pv168.project.ui.model.ComboBoxModelAdapter;
import cz.muni.fi.pv168.project.ui.model.LocalDateModel;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.time.LocalDate;

public final class CategoryDialog extends EntityDialog<Category> {

    private final JTextField firstNameField = new JTextField(20);

    public CategoryDialog() {

        addFields();
    }
/*
    private void setValues() {
        firstNameField.setText(category.getFirstName());
        lastNameField.setText(category.getLastName());
        genderModel.setSelectedItem(category.getGender());
        departmentModel.setSelectedItem(category.getDepartment());
        birthDateModel.setValue(category.getBirthDate());
    }
*/
    private void addFields() {
        add("Name:", firstNameField);
    }

    @Override
    Category getEntity() {
        return new Category(0, "name");
        /*
        category.setFirstName(firstNameField.getText());
        category.setLastName(lastNameField.getText());
        category.setGender((Gender) genderModel.getSelectedItem());
        category.setDepartment((Department) departmentModel.getSelectedItem());
        category.setBirthDate(birthDateModel.getValue());
        return category;
        */
    }
}
