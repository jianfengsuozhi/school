package common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.Region;

import entity.Mark;


/**
 * 导出excel
 * @author Edward
 *
 */
public class ImportExcel {
	public static Integer startRow = 1;
	public static String path = "F:\\mark.xls";
	
	public  static void importE(List<Mark> markList){
		//excel文件(sheet(row(cell)，列),单元格样式) 
		//row,column 从0开始
		HSSFWorkbook workbook = new HSSFWorkbook();
		String courseName = markList.get(0).getCourseName();
		HSSFSheet sheet = workbook.createSheet(courseName+"成绩sheet1");
		//设置列宽
		sheet.setColumnWidth(0, 20*256);
		//设置单元格样式
		HSSFCellStyle cellStyle= workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//合并单元格
		HSSFRow row0 = sheet.createRow(0);
		sheet.addMergedRegion(new Region(0, (short)0, 0, (short)2));
		HSSFCell cell00 = row0.createCell(0);
		cell00.setCellStyle(cellStyle);
		cell00.setCellValue(courseName+"成绩统计");
		
		HSSFRow row	 = sheet.createRow(startRow);
		HSSFCell cell0 = row.createCell(0);
		cell0.setCellStyle(cellStyle);
		cell0.setCellValue("课程");
		HSSFCell cell1 = row.createCell(1);
		cell1.setCellStyle(cellStyle);
		cell1.setCellValue("班级");
		HSSFCell cell2 = row.createCell(2);
		cell2.setCellStyle(cellStyle);
		cell2.setCellValue("学号");
		HSSFCell cell3 = row.createCell(3);
		cell3.setCellStyle(cellStyle);
		cell3.setCellValue("学生");
		HSSFCell cell4 = row.createCell(4);
		cell4.setCellStyle(cellStyle);
		cell4.setCellValue("分数");
		
		for (int i = 0; i < markList.size(); i++) {
			Mark mark = markList.get(i);
			
			HSSFRow createRow = sheet.createRow(i+(startRow+1));
			cell0 = createRow.createCell(0);
			cell0.setCellStyle(cellStyle);
			cell0.setCellValue(courseName);
		    cell1 = createRow.createCell(1);
			cell1.setCellStyle(cellStyle);
			cell1.setCellValue(mark.getTeamName());
			cell2 = createRow.createCell(2);
			cell2.setCellStyle(cellStyle);
			cell2.setCellValue(mark.getCode());
			cell3 = createRow.createCell(3);
			cell3.setCellStyle(cellStyle);
			cell3.setCellValue(mark.getStudentName());
			cell4 = createRow.createCell(4);
			cell4.setCellStyle(cellStyle);
			cell4.setCellValue(mark.getScore());
		}
		
		//统计
		HSSFRow rowt = sheet.createRow(startRow+markList.size()+1);
		cell1 = rowt.createCell(3);
		cell1.setCellStyle(cellStyle);
		cell1.setCellValue("总分数");
		cell2 = rowt.createCell(4);
		cell2.setCellStyle(cellStyle);
		cell2.setCellValue(ImportExcel.sumScore(markList));
		//平均分
		HSSFRow rowp = sheet.createRow(startRow+markList.size()+2);
		cell1 = rowp.createCell(3);
		cell1.setCellStyle(cellStyle);
		cell1.setCellValue("平均分");
		cell2 = rowp.createCell(4);
		cell2.setCellStyle(cellStyle);
		cell2.setCellValue(ImportExcel.sumScore(markList)*1.0/markList.size());
		
		//最高分
		HSSFRow rowg = sheet.createRow(startRow+markList.size()+3);
		cell1 = rowg.createCell(3);
		cell1.setCellStyle(cellStyle);
		cell1.setCellValue("最高分");
		cell2 = rowg.createCell(4);
		cell2.setCellStyle(cellStyle);
		cell2.setCellValue(ImportExcel.gaoScore(markList));
		
		//最低分
		HSSFRow rowd = sheet.createRow(startRow+markList.size()+4);
		cell1 = rowd.createCell(3);
		cell1.setCellStyle(cellStyle);
		cell1.setCellValue("最低分");
		cell2 = rowd.createCell(4);
		cell2.setCellStyle(cellStyle);
		cell2.setCellValue(ImportExcel.diScore(markList));
		
		//不及格
		HSSFRow rowb1 = sheet.createRow(startRow+markList.size()+5);
		//合并单元格
		sheet.addMergedRegion(new Region(startRow+markList.size()+5, (short)0, startRow+markList.size()+5, (short)1));
		cell0 = rowb1.createCell(0);
		cell0.setCellStyle(cellStyle);
		cell0.setCellValue("不及格");
		int j=0;
		for (int i = 0; i < markList.size(); i++) {
			Mark mark = markList.get(i);
			double parseDouble = Double.parseDouble(mark.getScore().trim());
			if(parseDouble<60){
				HSSFRow createRow = sheet.createRow(j+(startRow+markList.size()+6));
				cell0 = createRow.createCell(0);
				cell0.setCellStyle(cellStyle);
				cell0.setCellValue(courseName);
			    cell1 = createRow.createCell(1);
				cell1.setCellStyle(cellStyle);
				cell1.setCellValue(mark.getTeamName());
				cell2 = createRow.createCell(2);
				cell2.setCellStyle(cellStyle);
				cell2.setCellValue(mark.getCode());
				cell3 = createRow.createCell(3);
				cell3.setCellStyle(cellStyle);
				cell3.setCellValue(mark.getStudentName());
				cell4 = createRow.createCell(4);
				cell4.setCellStyle(cellStyle);
				cell4.setCellValue(mark.getScore());
				j++;
			}
		}
		try {
			FileOutputStream fout = new FileOutputStream(path);
			workbook.write(fout);
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 总分
	 * @param list
	 * @return
	 */
	private static Double sumScore(List<Mark> list){
		Double sum = 0.0;
		for (Mark mark : list) {
			sum += Double.parseDouble(mark.getScore().trim());
		}
		return sum;
	}
	
	/**
	 * 最高分
	 * @param list
	 * @return
	 */
	private static Double gaoScore(List<Mark> list){
		Double gaoScore = 0.0;
		for (Mark mark : list) {
			Double score = Double.parseDouble(mark.getScore().trim());
			if(gaoScore<score){
				gaoScore = score;
			}
		}
		return gaoScore;
	}
	/**
	 * 最低分
	 * @param list
	 * @return
	 */
	private static Double diScore(List<Mark> list){
		Double diScore = Double.parseDouble(list.get(0).getScore());
		for (int i = 1; i <list.size(); i++) {
			Mark mark = list.get(i);
			Double score = Double.parseDouble(mark.getScore().trim());
			if(score<diScore){
				diScore = score;
			}
		}
		return diScore;
	}
	
	public static void download(String path,HttpServletResponse response) throws IOException{
		//文件到流(读 input) 流到文件(写 output) (相对于流)
		File file = new File(path);
		String filename = file.getName();
		InputStream inStream = new BufferedInputStream(new FileInputStream(file));
		byte[] buffer = new byte[inStream.available()];
		inStream.read(buffer);
		inStream.close();
		
		response.reset();
		response.addHeader("Content-Disposition", "attachment;filename="  
                + new String(filename.getBytes()));
		response.addHeader("Content-Length", "" + file.length());
		
		OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		toClient.write(buffer);
		toClient.flush();
		toClient.close();
		System.out.println("下载成功");
	}
}
