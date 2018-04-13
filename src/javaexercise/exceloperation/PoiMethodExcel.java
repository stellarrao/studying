package javaexercise.exceloperation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.monitorjbl.xlsx.StreamingReader;

public class PoiMethodExcel
{
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        // new PoiMethodExcel().readExcel();
//        new PoiMethodExcel().readExcelOfxlsx();
        new PoiMethodExcel().readExcelOfxlsxNew();
        // new PoiMethodExcel().writeExcel();

        // new PoiMethodExcel().excel2007AboveOperateOld();
        // new PoiMethodExcel().excel2007AboveOperate();
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间: " + (endTime - startTime) + "ms");
    }

    public void readExcel()
    {
        File file = new File("D:\\City.xls");

        if (!file.exists())
        {
            System.out.println("文件不存在");
        }

        try
        {
            // 1读取Excel的对象
            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new FileInputStream(file));
            // 2Excel工作薄对象
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
            // 3Excel工作表对象
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
            // 总行数
            int rowLength = hssfSheet.getLastRowNum() + 1;
            // 得到Excel工作表的行
            HSSFRow hssfRow = hssfSheet.getRow(0);
            // 总列数
            int colLength = hssfRow.getLastCellNum();
            // 得到Excel指定单元格中的内容
            HSSFCell hssfCell = hssfRow.getCell(0);
            // 得到单元格样式
            CellStyle cellStyle = hssfCell.getCellStyle();

            for (int i = 0; i < rowLength; i++)
            {
                hssfRow = hssfSheet.getRow(i);
                for (int j = 0; j < colLength; j++)
                {
                    hssfCell = hssfRow.getCell(j);
                    if (hssfCell != null)
                    {
                        hssfCell.setCellType(CellType.STRING);
                        System.out.print(hssfCell.getStringCellValue() + "\t");
                    }
                }

                System.out.println();
            }

        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void readExcelOfxlsx()
    {
        File file = new File("D:\\BJS0017.xlsx");

        if (!file.exists())
        {
            System.out.println("文件不存在");
        }

        try
        {
            // 1读取Excel的对象
            InputStream inputStream = new FileInputStream(file);
            // 2Excel工作薄对象
            Workbook workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            // 3Excel工作表对象
            Sheet sheet = workbook.getSheetAt(0);
            // 总行数
            int rowLength = sheet.getLastRowNum() + 1;
            // 得到Excel工作表的行
            Row row = sheet.getRow(0);
            // 总列数
            int colLength = row.getLastCellNum();
            // 得到Excel指定单元格中的内容
            Cell cell = row.getCell(0);
            // 得到单元格样式
            CellStyle cellStyle = cell.getCellStyle();

            for (int i = 0; i < rowLength; i++)
            {
                row = sheet.getRow(i);
                for (int j = 0; j < colLength; j++)
                {
                    cell = row.getCell(j);
                    if (cell != null)
                    {
                        cell.setCellType(CellType.STRING);
                        System.out.print(cell.getStringCellValue() + "\t");
                    }
                }

                System.out.println();
            }

        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void readExcelOfxlsxNew()
    {
        try (InputStream in = new FileInputStream("D:\\BJS0017.xlsx");
                Workbook workbook = StreamingReader.builder().rowCacheSize(1000).bufferSize(4096).open(in))
        {
            for (Sheet sheet : workbook)
            {
                System.out.println(sheet.getSheetName());
                for (Row r : sheet)
                {
                    for (Cell c : r)
                    {
                        System.out.print(c.getStringCellValue() + "\t");
                    }

                    System.out.println();
                }
            }
        }
        catch(Exception ex) {
            
        }
    }

    public void writeExcel()
    {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("0");
        Row row = sheet.createRow(0);
        CellStyle cellStyle = workbook.createCellStyle();

        // 设置样式
        cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // cellStyle.setBorderBottom(arg0);
        row.createCell(0).setCellStyle(cellStyle);
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellStyle(cellStyle);
        row.createCell(1).setCellValue("年龄");

        workbook.setSheetName(0, "信息");

        try
        {
            File file = new File("D:\\City1111.xlsx");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void excel2007AboveOperateOld()
    {
        try
        {
            // XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(filePath)));
            XSSFWorkbook workbook = new XSSFWorkbook();
            // 获取第一个表单
            // Sheet first = workbook.getSheetAt(0);
            Sheet first = workbook.createSheet("0");
            for (int i = 0; i < 100000; i++)
            {
                Row row = first.createRow(i);
                for (int j = 0; j < 11; j++)
                {
                    if (i == 0)
                    {
                        // 首行
                        row.createCell(j).setCellValue("column" + j);
                    } else
                    {
                        // 数据
                        if (j == 0)
                        {
                            // row.createCell(j).setCellValue(String.valueOf(i));
                            CellUtil.createCell(row, j, String.valueOf(i));
                        } else
                        {
                            // row.createCell(j).setCellValue(String.valueOf(Math.random()));
                            CellUtil.createCell(row, j, String.valueOf(Math.random()));
                        }

                    }
                }
            }
            // 写入文件
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            FileOutputStream out = new FileOutputStream("D:\\workbook" + df.format(new Date()) + ".xlsx");

            workbook.write(out);
            out.close();
            workbook.close();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void excel2007AboveOperate()
    {

        try
        {
            // XSSFWorkbook workbook1 = new XSSFWorkbook(new FileInputStream(new File(filePath)));
            XSSFWorkbook workbook = new XSSFWorkbook();
            SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(workbook, 100);
            // Workbook workbook = WorkbookFactory.create(new FileInputStream(new File(filePath)));
            // Sheet first = sxssfWorkbook.getSheetAt(0);
            Sheet first = sxssfWorkbook.createSheet("0");
            for (int i = 0; i < 100000; i++)
            {
                Row row = first.createRow(i);
                for (int j = 0; j < 11; j++)
                {
                    if (i == 0)
                    {
                        // 首行
                        row.createCell(j).setCellValue("column" + j);
                    } else
                    {
                        // 数据
                        if (j == 0)
                        {
                            CellUtil.createCell(row, j, String.valueOf(i));
                        } else
                            CellUtil.createCell(row, j, String.valueOf(Math.random()));
                    }
                }
            }

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            FileOutputStream out = new FileOutputStream("D:\\workbook" + df.format(new Date()) + ".xlsx");
            sxssfWorkbook.write(out);
            out.close();
            sxssfWorkbook.close();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
