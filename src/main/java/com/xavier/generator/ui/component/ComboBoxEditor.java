package com.xavier.generator.ui.component;

import javax.swing.*;

public class ComboBoxEditor extends DefaultCellEditor {

    public ComboBoxEditor(JCheckBox checkBox) {
        super(checkBox);
    }

    public ComboBoxEditor(String[] value) {
        super(new JComboBox(value));
    }

}
