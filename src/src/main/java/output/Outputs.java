package output;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;

/**
 * Class for outpur result
 * @author Dima Korenko
 */
public class Outputs {

    private final List<ResultTable> data;

    public Outputs(List<ResultTable> data) {
        this.data = data;
    }

    public void createExcel(String fileName) {
        Excel excel = new Excel();
        for (ResultTable table : data) {
            XSSFSheet sheet = excel.createSheet(table.getName());
            TableBounds bounds = excel.createTable(sheet, table, 2, 2);
            excel.createGraph(sheet, bounds, 10, 0, 35, 20);
        }
        excel.save(fileName);
    }

    public void print() {
        for (ResultTable table : data) {
            System.out.println("Table");
            for (int i = 0; i < table.size(); i++) {
                System.out.println(table.get(i));
            }
        }
    }
}