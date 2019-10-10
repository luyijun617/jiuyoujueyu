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


public class ExcelPort {

    private static String excelDownLoadPath = "C:\\Users\\lyj\\Desktop\\miko";
    private static String imgPathName = "C:\\Users\\lyj\\Desktop\\miko\\img.jpg";

    public static void main(String[] args) {
        //数据
        Object[][] datas =
                {
                        {"测试标题", null, null, null, null, null}, //大标题
                        {"照片信息", "店铺信息", "租凭信息", "负责人", "租凭状态", "最后修改信息"},//小标题
                        {"没有图片", "铺位号事实上测试", "租打算的撒凭\n信息\n算的\n长沙市\n的撒打算", "负大声地责人", "大声地", "打算的撒"},
                        {"没有图片", "铺位号事实上测试", "租打算的撒凭\n信息", "负大声地责人", "大声地", "打算的撒"},
                        {"没有图片", "铺位号事实上测试", "租打算的撒凭\n信息\n22d", "负大声地责人", "大声地", "打算的撒"}
                };
        //合并单元格// 起始行号，终止行号， 起始列号，终止列号
        int[][] mergeCell = {
                {0, 0, 0, 5} //合并标题
        };
        try {
            int export = excelExport(datas, mergeCell, excelDownLoadPath + "/abc.xls");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 导出-根据需要自己设置
     * @param datas 数据
     * @param mergeCell 合并列
     * @param path 导出路径
     */
    public static int excelExport(Object[][] datas, int[][] mergeCell, String path) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("table");  //创建table工作薄
        HSSFRow row;
        HSSFCell cell;
        short colorIndex = 10;
        HSSFPalette palette = wb.getCustomPalette();
        Color rgb = Color.yellow; //背景颜色
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
                    cellStyle.setFillForegroundColor(bgIndex); //bgIndex 背景颜色下标值(只设置标题的-可调整)
                    cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
                }
                if (i == 0 || i == 1) {
                    HSSFFont font = wb.createFont();
                    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                    if (i == 0) {
                        font.setFontHeightInPoints((short) 15);
                    } else if (i == 1) {
                        font.setFontHeightInPoints((short) 12);
                    }
                    cellStyle.setFont(font);  //字体加粗
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
                cell.setCellStyle(cellStyle);
            }
        }
        // 合并单元格
        for (int i = 0; i < mergeCell.length; i++) {
            //合并单元格// 起始行号，终止行号， 起始列号，终止列号
            CellRangeAddress region = new CellRangeAddress(mergeCell[i][0], mergeCell[i][1], mergeCell[i][2], mergeCell[i][3]);
            sheet.addMergedRegion(region);
        }
        //创建表格之后设置行高与列宽
        for (int i = 0; i < datas.length; i++) {
            int heigtht = 1;
            for (int j = 0; j < datas[i].length; j++) {
                if (datas[i] != null && datas[i][j] != null) {
                    int len = datas[i][j].toString().split("\n").length;
                    if (len > heigtht) {
                        heigtht = len - 1;
                    }
                }
            }
            row = sheet.getRow(i);
            row.setHeightInPoints(27 * heigtht);
        }
        addPicture(wb, sheet, 0, 1, 2, 3, imgPathName);
        for (int j = 0; j < datas[0].length; j++) {
            sheet.setColumnWidth(j, MSExcelUtil.pixel2WidthUnits(150));
        }
        wb.write(new FileOutputStream(path)); //存储文件路径
        System.out.println("Excel>>> 导出成功!!!");
        return 1;
    }

    /*
     * 插入图片-暂时不使用
     */
    public static void addPicture(HSSFWorkbook wb, HSSFSheet sheet, int beginColl, int endColl, int beginRow, int endRow, String imgUrl) {
        //加入图片
        byte[] bt = new byte[0];
        try {
            bt = FileUtils.readFileToByteArray(new File(imgUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int pictureIdx = wb.addPicture(bt, Workbook.PICTURE_TYPE_JPEG);
        CreationHelper helper = wb.getCreationHelper();
        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = helper.createClientAnchor();
//        anchor.setDx1(MSExcelUtil.pixel2WidthUnits(60));
//        anchor.setDy1(MSExcelUtil.pixel2WidthUnits(60));
//        anchor.setDx1(0);
//        anchor.setDy1(1);
        anchor.setCol1(beginColl); //图片开始列数
        anchor.setCol2(endColl); //图片结束列数
        anchor.setRow1(beginRow); //图片开始行数
        anchor.setRow2(endRow);//图片结束行数
        drawing.createPicture(anchor, pictureIdx);
    }
}
