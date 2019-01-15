package output;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sorters.ArraySorter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * Class for outpur result
 * @author Dima Korenko
 */
public class Outputs {

    private final List<ResultHolder> result;
    public Outputs(List<ResultHolder> data) {
        this.result = data;
    }

    public void createExcel(String fileName) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("results");
            for (int i = 0; i < result.size(); i++) {
                XSSFRow row = spreadsheet.createRow(i);
                String[] resultArray = result.get(i).toArray();
                for (int j = 0; j < resultArray.length; j++) {
                    row.createCell(j).setCellValue(resultArray[j]);
                }
            }
            FileOutputStream out = new FileOutputStream(new File(fileName));
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print() {
        for (ResultHolder result : result) {
            System.out.println(result);
        }
    }

    public void sort(Comparator<ResultHolder> sortBy) {
        result.sort(sortBy);
    }
}