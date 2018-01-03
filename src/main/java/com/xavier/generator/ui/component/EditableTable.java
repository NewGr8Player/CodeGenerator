package com.xavier.generator.ui.component;

import javax.swing.*;
import javax.swing.table.TableCellEditor;

public class EditableTable extends JTable {

	int myCol = -1;
	transient TableCellEditor myEditor;/* 边框编辑器 */

	public void setComboCell(int col, TableCellEditor editor) {
		this.myCol = col;
		this.myEditor = editor;
	}

	@Override
	public TableCellEditor getCellEditor(int row, int col) {
		if (col == myCol && myEditor != null) {
			return myEditor;
		}
		return super.getCellEditor(row, col);
	}

	/**
	 * 返回数据类型
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public Class getColumnClass(int myCol) {
		return getValueAt(0, myCol).getClass();
	}

}
