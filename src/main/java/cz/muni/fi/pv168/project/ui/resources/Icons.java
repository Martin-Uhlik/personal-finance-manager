package cz.muni.fi.pv168.project.ui.resources;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.*;
import java.net.URL;

public final class Icons {

    public static final Icon DELETE_ICON = createIcon("Crystal_Clear_action_button_cancel.png");
    public static final Icon EDIT_ICON = createIcon("Crystal_Clear_action_edit.png");
    public static final Icon ADD_ICON = createIcon("Crystal_Clear_action_edit_add.png");
    public static final Icon DETAILS_ICON = createIcon("Crystal_Clear_action_details.png");
    public static final Icon FILTER_ICON = createIcon("Crystal_Clear_action_filter.png");
    public static final Icon RESET_ICON = createIcon("Crystal_Clear_action_reload.png");

    public static final ImageIcon MAIN_ICON = createIcon("Bitcoin_icon.png");

    private Icons() {
        throw new AssertionError("This class is not instantiable");
    }

    private static ImageIcon createIcon(String name) {
        URL url = Icons.class.getResource(name);
        if (url == null) {
            throw new IllegalArgumentException("Icon resource not found on classpath: " + name);
        }
        return new ImageIcon(url);
    }
}
