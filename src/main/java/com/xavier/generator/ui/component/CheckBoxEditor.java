package com.xavier.generator.ui.component;

import javax.swing.*;

public class CheckBoxEditor extends DefaultCellEditor {

    public CheckBoxEditor(JCheckBox checkBox) {
        super(checkBox);
    }

    public CheckBoxEditor() {
        super(new JCheckBox());
    }

}
