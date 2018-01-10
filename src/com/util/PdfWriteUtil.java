package com.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * PDF文件生成
 * @author zhangpeng.zhou
 *
 */
public class PdfWriteUtil {

    Document document = null;
    private static Font headFont;
    private static Font textfont_H;
    int maxWidth = 520;

    static {
        BaseFont bfChineseH;
        try {
            /**
             * 新建一个字体,iText的方法 STSongStd-Light 是字体，在iTextAsian.jar 中以property为后缀
             * UniGB-UCS2-H 是编码，在iTextAsian.jar 中以cmap为后缀 H 代表文字版式是 横版， 相应的 V 代表竖版
             */
            bfChineseH = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

            headFont = new Font(bfChineseH, 10, Font.NORMAL);
            new Font(bfChineseH, 18, Font.BOLD);
            textfont_H = new Font(bfChineseH, 10, Font.NORMAL);
            new Font(bfChineseH, 12, Font.NORMAL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置页面属性
     *
     * @param file
     */
    public PdfWriteUtil(File file) {

        // 自定义纸张
        Rectangle rectPageSize = new Rectangle(350, 620);

        // 定义A4页面大小
     // 加上这句可以实现页面的横置
        rectPageSize = rectPageSize.rotate();
        document = new Document(rectPageSize, 10, 150, 10, 40);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 建表格(以列的数量建)
     *
     * @param colNumber
     * @return
     */
    public PdfPTable createTable(int colNumber) {
        PdfPTable table = new PdfPTable(colNumber);
        try {
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
            table.setSpacingBefore(10);
            table.setWidthPercentage(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    /**
     * 表格中单元格
     *
     * @param value
     * @param font
     * @param align
     * @return
     */
    public PdfPCell createCell(String value, Font font, int align) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }

    /**
     * 表格中单元格
     *
     * @param value
     * @param font
     * @param colspan
     * @param rowspan
     * @return
     */
    public PdfPCell createCell(String value, Font font, int alignV, int alignH, int colspan, int rowspan) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(alignV);
        cell.setHorizontalAlignment(alignH);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }

    /**
     * 建短语
     *
     * @param value
     * @param font
     * @return
     */
    public Phrase createPhrase(String value, Font font) {
        Phrase phrase = new Phrase();
        phrase.add(value);
        phrase.setFont(font);
        return phrase;
    }

    /**
     * 建段落
     *
     * @param value
     * @param font
     * @param align
     * @return
     */
    public Paragraph createParagraph(String value, Font font, int align) {
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Phrase(value, font));
        paragraph.setAlignment(align);
        return paragraph;
    }

    public void generatePDF(List<Map<String, Object>> list, String prizeType) throws Exception {

        // 页头信息
        document.add(createParagraph(prizeType + "中奖名单", headFont, Element.ALIGN_CENTER));

        // 表格信息
        PdfPTable table = createTable(11);

        table.addCell(createCell("序号", textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
        table.addCell(createCell("工号", textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
        table.addCell(createCell("姓名", textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
        table.addCell(createCell("一级部门", textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 2, 1));
        table.addCell(createCell("二级部门", textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 2, 1));
        table.addCell(createCell("三级部门", textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 2, 1));
        table.addCell(createCell("奖品", textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 2, 1));

        for (int i = 0, j = list.size(); i < j; i++) {
            table.addCell(
                    createCell(String.valueOf(i + 1), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
            table.addCell(createCell(String.valueOf(list.get(i).get("emp_no")), textfont_H, Element.ALIGN_MIDDLE,
                    Element.ALIGN_CENTER, 1, 1));
            table.addCell(createCell(String.valueOf(list.get(i).get("emp_name")), textfont_H, Element.ALIGN_MIDDLE,
                    Element.ALIGN_CENTER, 1, 1));
            table.addCell(createCell(String.valueOf(list.get(i).get("emp_first_department")), textfont_H,
                    Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 2, 1));
            table.addCell(createCell(String.valueOf(list.get(i).get("emp_secondary_department")), textfont_H,
                    Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 2, 1));
            table.addCell(createCell(String.valueOf(list.get(i).get("emp_three_department")), textfont_H,
                    Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 2, 1));
            table.addCell(createCell(String.valueOf(list.get(i).get("prize_name")), textfont_H, Element.ALIGN_MIDDLE,
                    Element.ALIGN_CENTER, 2, 1));
        }

        document.add(table);
        document.close();
    }

}