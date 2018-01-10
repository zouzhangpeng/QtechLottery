package com.util;

import com.model.Constant;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TableUtil {
    private JTable table;

    public JTable getTable(TableModelUtil tableModelUtil, JPanel parentPanel) {
    	int tableHeight = Integer.parseInt(PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, "tableHeight"));
        int tableFont = Integer.parseInt(PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, "tableFont"));
    	table = new JTable(tableModelUtil);
        table.setForeground(Color.WHITE);
        table.setFont(new Font("微软雅黑", Font.BOLD, tableFont));
        table.setRowHeight(tableHeight);//设置行高
        table.setRowMargin(0);//设置行距0
        table.setEnabled(false);//设置不可编辑
        table.setShowGrid(false);//取消网格线
        table.setOpaque(false);//设置表格透明
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();//设置表格渲染器
        render.setOpaque(false); //将渲染器设置为透明
        table.setDefaultRenderer(Object.class, render);//通过渲染器设置表格内容透明
        JTableHeader tableHeader = table.getTableHeader();//获取表头
        tableHeader.setOpaque(false);//设置表头透明
        tableHeader.getTable().setOpaque(false);//设置表头里的表格透明
        tableHeader.setDefaultRenderer(render);//设置表头内容透明
        TableCellRenderer headerRenderer = tableHeader.getDefaultRenderer();
        //表格内容居中
        if (headerRenderer instanceof JLabel) {
            ((JLabel) headerRenderer).setHorizontalAlignment(JLabel.CENTER);
            ((JLabel) headerRenderer).setOpaque(false);
        }
        table.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeTable(true, parentPanel);
            }
        });
        return table;
    }

    public void resizeTable(boolean bool, JPanel parent) {
        Dimension containerwidth = null;
        if (!bool) {
            //初始化时，父容器大小为首选大小，实际大小为0
            containerwidth = parent.getPreferredSize();
        } else {
            //界面显示后，如果父容器大小改变，使用实际大小而不是首选大小
            containerwidth = parent.getSize();
        }
        //计算表格总体宽度 getTable().
        int allwidth = table.getIntercellSpacing().width;
        for (int j = 0; j < table.getColumnCount(); j++) {
            //计算该列中最长的宽度
            int max = 0;
            for (int i = 0; i < table.getRowCount(); i++) {
                int width = table.getCellRenderer(i, j).getTableCellRendererComponent
                        (table, table.getValueAt(i, j), false,
                                false, i, j).getPreferredSize().width;
                if (width > max) {
                    max = width;
                }
            }
            //计算表头的宽度
            int headerwidth = table.getTableHeader().
                    getDefaultRenderer().getTableCellRendererComponent(
                    table, table.getColumnModel().
                            getColumn(j).getIdentifier(), false, false,
                    -1, j).getPreferredSize().width;
            //列宽至少应为列头宽度
            max += headerwidth;
            //设置列宽
            table.getColumnModel().
                    getColumn(j).setPreferredWidth(max);
            //给表格的整体宽度赋值，记得要加上单元格之间的线条宽度1个像素
            allwidth += max + table.getIntercellSpacing().width;
        }
        allwidth += table.getIntercellSpacing().width;
        //如果表格实际宽度大小父容器的宽度，则需要我们手动适应；否则让表格自适应
        if (allwidth > containerwidth.width) {
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } else {
            table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        }
    }
}
