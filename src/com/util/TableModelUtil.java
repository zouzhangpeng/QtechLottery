package com.util;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModelUtil extends AbstractTableModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String[]> tableData;
    private String[] tableTitle;

    public TableModelUtil(List<String[]> tableData, String[] tableTitle) {
        this.tableData = tableData;
        this.tableTitle = tableTitle;
    }

    @Override
    public int getRowCount() {
        return tableData.size();
    }

    @Override
    public int getColumnCount() {
        return tableTitle.length;
    }

    @Override
    public String getColumnName(int column) {
        return tableTitle[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return tableData.get(rowIndex)[columnIndex];
    }
}
