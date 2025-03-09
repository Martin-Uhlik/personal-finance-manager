package cz.muni.fi.pv168.project.ui.dialog;

import cz.muni.fi.pv168.project.model.Department;
import cz.muni.fi.pv168.project.model.Employee;
import cz.muni.fi.pv168.project.model.Gender;
import cz.muni.fi.pv168.project.model.Transaction;
import cz.muni.fi.pv168.project.model.TransactionType;
import cz.muni.fi.pv168.project.ui.model.ComboBoxModelAdapter;
import cz.muni.fi.pv168.project.ui.model.LocalDateModel;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.time.LocalDate;

public final class RecurringTransactionDialog extends EntityDialog<Transaction> {

    private final JTextField firstNameField = new JTextField(20);
    private final JTextField lastNameField = new JTextField(20);
    private final ComboBoxModel<TransactionType> genderModel = new DefaultComboBoxModel<>(TransactionType.values());

    private final DateModel<LocalDate> birthDateModel = new LocalDateModel();
    private final DateModel<LocalDate> begin = new LocalDateModel();
    private final DateModel<LocalDate> end = new LocalDateModel();

    private final JButton button = new JButton("Select categories");

    public RecurringTransactionDialog() {
        setValues();
        addFields();
    }

    private void setValues() {
    }

    private void addFields() {
        add("Description:", firstNameField);
        add("Date of recurrence:", new JDatePicker(birthDateModel));
        add("Date of begin:", new JDatePicker(begin));
        add("Date of end:", new JDatePicker(end));
        add("Amount:", lastNameField);
        add("Type:", new JComboBox<>(genderModel));
        add("Select categories", button);
    }

    @Override
    Transaction getEntity() {
        return new Transaction();
    }
}
