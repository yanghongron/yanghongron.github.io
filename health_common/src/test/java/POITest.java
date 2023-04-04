import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class POITest {

    /**
     * 使用PoI读取Excel文件中的数据
     */
    @Test
    public void test1() throws Exception {
        //加载指定文件，创建Excel对象(工作簿)
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(new File("C:\\Users\\28972\\Desktop\\poi.xlsx")));
        XSSFSheet sheetAt = excel.getSheetAt(0);

        int lastRowNum = sheetAt.getLastRowNum();
        for (int i = 0 ; i<=lastRowNum;i++){
            XSSFRow row = sheetAt.getRow(i);
            //获取当前行在最后一个单元格索引
            short lastCellNum = row.getLastCellNum();
            for ( int j = 0;j<lastCellNum;j++){
                XSSFCell cell = row.getCell(j);
                System.out.println(cell.getStringCellValue());
            }
        }

        excel.close();
    }

    @Test
    public void test2() throws Exception {
        XSSFWorkbook excel = new XSSFWorkbook();
        XSSFSheet sheet = excel.createSheet("传智博客");
        XSSFRow title = sheet.createRow(0);
        title.createCell(0).setCellValue("姓名");
        title.createCell(1).setCellValue("地址");
        title.createCell(2).setCellValue("年龄");

        XSSFRow dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue("北京");
        dataRow.createCell(1).setCellValue("小i");
        dataRow.createCell(2).setCellValue("90");

        //将数据输出
        FileOutputStream out = new FileOutputStream(new File("F:\\hello.xlsx"));
        excel.write(out);
        out.flush();
        excel.close();
    }
}
