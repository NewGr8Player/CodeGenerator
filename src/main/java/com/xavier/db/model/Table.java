package com.xavier.db.model;

import com.xavier.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class Table implements java.io.Serializable {

	private static final long serialVersionUID = -7246043091254837124L;
	private String tableName;
	private String tableType;
	private String tableAlias;
	private String remarks;
	private String className;
	private String javaProperty;
	private String catalog = null;
	private String schema = null;
	private List<Column> baseColumns = new ArrayList<Column>();
	private List<Column> primaryKeys = new ArrayList<Column>();
	private List<Key> importedKeys = new ArrayList<Key>();
	private List<Key> exportedKeys = new ArrayList<Key>();

	public Table() {
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
		this.tableAlias = tableName;
		this.className = StringUtil.getCamelCaseString(tableAlias, true);
		this.javaProperty = StringUtil.getCamelCaseString(tableAlias, false);
	}

	public String getRemarks() {
		return remarks == null ? "" : remarks.trim();
	}

	public boolean isHasRemarks() {
		return StringUtil.isNotEmpty(remarks);
	}

	public String getRemarksUnicode() {
		return StringUtil.toUnicodeString(getRemarks());
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public Column getColumn(String columnName) {
		for (Column column : primaryKeys) {
			if (column.getColumnName().equals(columnName)) {
				return column;
			}
		}
		for (Column column : baseColumns) {
			if (column.getColumnName().equals(columnName)) {
				return column;
			}
		}
		return null;
	}

	public List<Column> getColumns() {
		List<Column> allColumns = new ArrayList<Column>();
		allColumns.addAll(primaryKeys);
		allColumns.addAll(baseColumns);
		return allColumns;
	}

	public List<Column> getBaseColumns() {
		return baseColumns;
	}

	public void addBaseColumn(Column column) {
		this.baseColumns.add(column);
	}

	public List<Column> getPrimaryKeys() {
		return primaryKeys;
	}

	public void addPrimaryKey(Column primaryKeyColumn) {
		this.primaryKeys.add(primaryKeyColumn);
	}

	public String getJavaProperty() {
		return javaProperty;
	}

	public void setJavaProperty(String javaProperty) {
		this.javaProperty = javaProperty;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public String getTableAlias() {
		return tableAlias;
	}

	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
		this.className = StringUtil.getCamelCaseString(tableAlias, true);
		this.javaProperty = StringUtil.getCamelCaseString(tableAlias, false);
	}

	public boolean isHasDateColumn() {
		for (Column column : getColumns()) {
			if (column.isDate()) {
				return true;
			}
		}
		return false;
	}

	public boolean isHasBigDecimalColumn() {
		for (Column column : getColumns()) {
			if (column.isBigDecimal()) {
				return true;
			}
		}
		return false;
	}

	public boolean isHasNotNullColumn() {
		for (Column column : getColumns()) {
			if (!column.isNullable() && !column.isString()) {
				return true;
			}
		}
		return false;
	}

	public boolean isHasNotBlankColumn() {
		for (Column column : getColumns()) {
			if (!column.isNullable() && column.isString()) {
				return true;
			}
		}
		return false;
	}

	public boolean isHasSearchableColumn() {
		for (Column column : getColumns()) {
			if (column.isSearchable()) {
				return true;
			}
		}
		return false;
	}


	public List<Key> getImportedKeys() {
		return importedKeys;
	}


	public List<Key> getExportedKeys() {
		return exportedKeys;
	}

	public void addImportedKey(Key importedKey) {
		this.importedKeys.add(importedKey);
	}

	public void addExportedKey(Key exportedKey) {
		this.exportedKeys.add(exportedKey);
	}

	public int getBaseColumnsCount() {
		return this.baseColumns.size();
	}
}
