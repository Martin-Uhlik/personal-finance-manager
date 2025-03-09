package cz.muni.fi.pv168.project.model;

import java.time.LocalDate;

/**
 * @author Martin Uhlik
 */
public class Transaction {
    private int id;
    private String description;
    private LocalDate date;
    private int amount;
    private TransactionType transactionType;
    private boolean isRecurring;
    private LocalDate dateOfRecurrence;
    private LocalDate startOfRecurrence;
    private LocalDate endOfRecurrence;
    private Category[] categories;

    public Transaction() {
        //TODO
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public LocalDate getDateOfRecurrence() {
        return dateOfRecurrence;
    }

    public LocalDate getStartOfRecurrence() {
        return startOfRecurrence;
    }

    public LocalDate getEndOfRecurrence() {
        return endOfRecurrence;
    }

    public Category[] getCategories() {
        return categories;
    }
}
