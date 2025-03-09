package cz.muni.fi.pv168.project.ui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv168.project.model.Category;
import cz.muni.fi.pv168.project.model.Gender;
import cz.muni.fi.pv168.project.ui.model.CategoriesTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * @author Martin Uhlik
 */
public class OverviewPanel extends JPanel {
    private final JPanel leftPannel;
    private final ChartPanel rightPanel;
    private final JPanel middlePanel;
    private final JSplitPane leftSplitPane;
    private final JSplitPane splitPane;

    private int income = 420; //TODO calculate income
    private int outcome = 42; //TODO calculate outcome

    public OverviewPanel() {
        super();
        setLayout(new BorderLayout());

        leftPannel = new JPanel();
        leftPannel.setLayout(new BorderLayout());
        //leftPannel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        rightPanel = CreateChart();
        middlePanel = CreateRightPanel();

        leftSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPannel, middlePanel);
        leftSplitPane.setResizeWeight(0.7);
        leftSplitPane.setEnabled(false);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSplitPane, rightPanel);
        splitPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        splitPane.setResizeWeight(0.3);
        splitPane.setEnabled(false);
        add(splitPane);

        var employeeTable = createEmployeeTable(new ArrayList<>()); //TODO load data from database
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        leftPannel.add(scrollPane);
    }

    private JPanel CreateRightPanel() {
        JPanel panel =  new JPanel();
        //panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(new GridLayout(3, 1));

        JPanel upperSubPanel = new JPanel();
        upperSubPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Transactions from selected categories",0,2));
        upperSubPanel.setLayout(new GridLayout(2, 1));
        upperSubPanel.add(new JLabel("Income: 420"));
        upperSubPanel.add(new JLabel("Outcome: 42"));

        JPanel middleSubPanel = new JPanel();
        middleSubPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Total Transactions",0,2));
        middleSubPanel.setLayout(new GridLayout(2, 1));
        middleSubPanel.add(new JLabel("Income: 420"));
        middleSubPanel.add(new JLabel("Outcome: 42"));

        JPanel lowerSubPanel = new JPanel();
        lowerSubPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Balance summary",0,2));
        lowerSubPanel.setLayout(new GridLayout(2, 1));
        lowerSubPanel.add(new JLabel("Current balance: 378"));
        lowerSubPanel.add(new JLabel("Predicted balance at the end of the month: 378"));

        panel.add(upperSubPanel);
        panel.add(middleSubPanel);
        panel.add(lowerSubPanel);

        return panel;
    }

    private ChartPanel CreateChart() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "",
                "",
                "",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        Color alphaColor = new Color(255,255,255,0);

        barChart.setBackgroundPaint(alphaColor);
        CategoryPlot cplot = (CategoryPlot) barChart.getPlot();
        cplot.setBackgroundPaint(alphaColor);

        ((BarRenderer)cplot.getRenderer()).setBarPainter(new StandardBarPainter());

        BarRenderer r = (BarRenderer)barChart.getCategoryPlot().getRenderer();
        r.setSeriesPaint(0, Color.blue);
        r.setSeriesPaint(1, Color.red);

        return new ChartPanel(barChart);
    }

    private JTable createEmployeeTable(List<Category> categories) {
        var model = new CategoriesTableModel(categories);
        var table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.getSelectionModel().addListSelectionListener(this::rowSelectionChanged);
        var genderComboBox = new JComboBox<>(Gender.values());
        table.setDefaultEditor(Gender.class, new DefaultCellEditor(genderComboBox));
        return table;
    }

    private void rowSelectionChanged(ListSelectionEvent listSelectionEvent) {
        var selectionModel = (ListSelectionModel) listSelectionEvent.getSource();
        //TODO
    }

    private CategoryDataset createDataset( ) {
        final String incomeLabel = "Income";
        final String outcomeLabel = "Outcome";
        final String amountLabel = "Amount";

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

        dataset.addValue( income , incomeLabel , amountLabel );
        dataset.addValue( outcome , outcomeLabel , amountLabel );

        return dataset;
    }
}
