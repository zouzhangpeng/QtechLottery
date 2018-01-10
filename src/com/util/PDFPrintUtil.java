package com.util;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PDFPrintUtil {

	public static void printPDF(String filePath) {
		FileInputStream fiStream = null;
		try {
			fiStream = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		DocFlavor fileFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
		Doc myDoc = new SimpleDoc(fiStream, fileFormat, null);
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		PrintService[] services = PrintServiceLookup.lookupPrintServices(fileFormat, aset);
		DocPrintJob job = services[7].createPrintJob();
		try {
			job.print(myDoc, aset);// 成功后电脑会提示已有文档添加到打印队列
		} catch (PrintException pe) {
		}
	}

	public static void main(String[] args) {
		printPDF("C://2017年晚会二等奖中奖名单.pdf");
	}
}
