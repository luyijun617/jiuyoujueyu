package cn.luyijun.fitness.utils;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

/*
 * Excel导出
 */
public class ExportExcel {

    public static void main(String[] args) throws Exception {

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("table");  //创建table工作薄

        Object[][] datas =
                {
                        {"测试标题", null, null, null, null, null}, //标题
                        {"照片信息", "店铺信息", "租凭信息", "负责人", "租凭状态", "最后修改信息"},
                        {"没有图片", "铺位号事实上测试", "租打算的撒凭\n信息", "负大声地责人", "大声地", "打算的撒"},
                        {"没有图片", "铺位号事实上测试", "租打算的撒凭\n信息", "负大声地责人", "大声地", "打算的撒"}
                };
        HSSFRow row;
        HSSFCell cell;

        short colorIndex = 10;
        HSSFPalette palette = wb.getCustomPalette();
        Color rgb = Color.yellow;
        short bgIndex = colorIndex++;
        palette.setColorAtIndex(bgIndex, (byte) rgb.getRed(), (byte) rgb.getGreen(), (byte) rgb.getBlue());
        short bdIndex = colorIndex++;
        rgb = Color.BLACK;
        palette.setColorAtIndex(bdIndex, (byte) rgb.getRed(), (byte) rgb.getGreen(), (byte) rgb.getBlue());

        for (int i = 0; i < datas.length; i++) {
            row = sheet.createRow(i);//创建表格行
            for (int j = 0; j < datas[i].length; j++) {
                cell = row.createCell(j);//根据表格行创建单元格
                cell.setCellValue(String.valueOf(datas[i][j]));

                HSSFCellStyle cellStyle = wb.createCellStyle();
                if (i == 0 || i == 1) {
                    cellStyle.setFillForegroundColor(bgIndex); //bgIndex 背景颜色下标值
                    cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
                }

                cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
                cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
                //bdIndex 边框颜色下标值
                cellStyle.setBottomBorderColor(bdIndex);
                cellStyle.setLeftBorderColor(bdIndex);
                cellStyle.setRightBorderColor(bdIndex);
                cellStyle.setTopBorderColor(bdIndex);
                cellStyle.setWrapText(true);//换行
                cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

                if (i == datas.length - 1 && j == datas[0].length - 1) {
                    HSSFFont font = wb.createFont();
                    font.setItalic(true);
                    font.setUnderline(HSSFFont.U_SINGLE);
                    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                    font.setFontHeightInPoints((short) 14);
                    //cellStyle.setFont(font);  字体加粗和加下划线
                }
                cell.setCellStyle(cellStyle);
            }
        }
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 5);
        sheet.addMergedRegion(region);
        // 合并单元格
        for (int i = 0; i < datas.length; i++) {
            //合并单元格// 起始行号，终止行号， 起始列号，终止列号
            //CellRangeAddress region2 = new CellRangeAddress(2, 3, 0, 0);
            //sheet.addMergedRegion(region2);
        }
        // for (int i = 0; i < datas.length; i++) {
        //加入图片
        // addPicture(wb, sheet, 0, 3, 3, 10, "C:/Users/shuai.luo/Desktop/photo/1.jpg");
        //}
        //创建表格之后设置行高与列宽
        for (int i = 0; i < datas.length; i++) {
            row = sheet.getRow(i);
            row.setHeightInPoints(30);
        }
        for (int j = 0; j < datas[0].length; j++) {
            sheet.setColumnWidth(j, MSExcelUtil.pixel2WidthUnits(160));
        }
        wb.write(new FileOutputStream("C:/Users/shuai.luo/Desktop/table6.xls"));
    }

    public static void addPicture(HSSFWorkbook wb, HSSFSheet sheet, int beginColl, int endColl, int beginRow, int endRow, String imgUrl) {
        //加入图片
        byte[] bt = new byte[0];
        try {
            bt = FileUtils.readFileToByteArray(new File(imgUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int pictureIdx = wb.addPicture(bt, Workbook.PICTURE_TYPE_PNG);
        CreationHelper helper = wb.getCreationHelper();
        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setDx1(MSExcelUtil.pixel2WidthUnits(60));
        anchor.setDy1(MSExcelUtil.pixel2WidthUnits(60));
        anchor.setCol1(beginColl); //图片开始列数
        anchor.setRow1(beginRow); //图片开始行数
        anchor.setCol2(endColl); //图片结束列数
        anchor.setRow2(endRow);//图片结束行数
        drawing.createPicture(anchor, pictureIdx);
    }
}
