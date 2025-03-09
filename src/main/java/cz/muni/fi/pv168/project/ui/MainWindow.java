package cz.muni.fi.pv168.project.ui;

import cz.muni.fi.pv168.project.ui.resources.Icons;

import javax.swing.*;
import java.awt.*;

/**
 * Personal Cash Flow Evidence
 *
 * @author Martin Uhlik
 */
public class MainWindow {
    private final JFrame frame;

    private final OverviewPanel overviewPanel = new OverviewPanel();
    private final TransactionsPanel transactionsPanel = new TransactionsPanel();
    private final RecurringTransactionPanel recurringTransactionPanel = new RecurringTransactionPanel();
    private final CategoriesPanel categoriesPanel = new CategoriesPanel();

    public MainWindow() {
        frame = createFrame();
        frame.add(createTabbedPane());
        //frame.setJMenuBar(createMenuBar()); //TODO delete
        frame.pack();
    }

    /**
     * Sets application window visible.
     */
    public void show() {
        frame.setVisible(true);
    }

    /**
     * Creates card layout
     *
     * @return jTabbedPane
     */
    private JTabbedPane createTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Overview", overviewPanel);
        tabbedPane.add("Transactions", transactionsPanel);
        tabbedPane.add("Recurring Transactions", recurringTransactionPanel);
        tabbedPane.add("Transaction Categories", categoriesPanel);

        return tabbedPane;
    }

    /**
     * Creates application frame (window).
     *
     * @return frame
     */
    private JFrame createFrame() {
        var frame = new JFrame(" Personal Cash Flow Evidence");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension dimension = new Dimension(600, 400);
        frame.setMinimumSize(dimension);
        frame.setPreferredSize(dimension);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(Icons.MAIN_ICON.getImage());
        return frame;
    }

    //TODO delete
    private JMenuBar createMenuBar() {
        var menuBar = new JMenuBar();

        JMenuItem overview = new JMenuItem("Overview");
        JMenuItem transactions = new JMenuItem("Transactions");
        JMenuItem recurringTransactions = new JMenuItem("Recurring Transactions");
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(overview);
        menuBar.add(transactions);
        menuBar.add(recurringTransactions);

        menuBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        return menuBar;
    }
}
