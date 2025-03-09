package cz.muni.fi.pv168.project.ui.dialog;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Optional;

import static javax.swing.JOptionPane.*;

abstract class EntityDialog<E> {

    private final JPanel panel = new JPanel();
    private int nextComponentRow = 0;

    EntityDialog() {
        panel.setLayout(new GridBagLayout());
    }

    void add(String labelText, JComponent component) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = nextComponentRow++;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.weightx = 0.0;
        var label = new JLabel(labelText);
        label.setLabelFor(component);
        panel.add(label, c);
        c.gridx = 1;
        c.weightx = 1.0;
        panel.add(component, c);
    }

    abstract E getEntity();

    public Optional<E> show(JComponent parentComponent, String title) {
        int result = JOptionPane.showOptionDialog(parentComponent, panel, title,
                OK_CANCEL_OPTION, PLAIN_MESSAGE, null, null, null);
        if (result == OK_OPTION) {
            return Optional.of(getEntity());
        } else {
            return Optional.empty();
        }
    }
}
