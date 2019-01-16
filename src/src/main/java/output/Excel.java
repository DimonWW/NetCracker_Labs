package output;

import org.apache.poi.xssf.usermodel.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Class for creating and working with tables
 * @author Dima Korenko
 */
public class Excel {
    private final XSSFWorkbook workbook;

    public Excel() {
        this.workbook = new XSSFWorkbook();
    }

    public XSSFSheet createSheet(String name) {
        return workbook.createSheet(name);
    }

    public void save(String fileName) {
        try (FileOutputStream out = new FileOutputStream(new File(fileName))) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TableBounds createTable(XSSFSheet sheet, ResultTable table) {
        HashMap sorterNameToColumn = new HashMap<String, Integer>();
        HashMap elmCountToRow = new HashMap<Integer, XSSFRow>();

        table.sort(Comparator.comparingInt(ResultRecord::getNumberOfElements));
        XSSFRow headerRow = sheet.createRow(0);
        Cell cell;
        XSSFRow row;

        for (int recIndex = 0, colIndex = 1, rowIndex = 1; recIndex < table.size(); recIndex++) {
            ResultRecord record = table.get(recIndex);
            String sorterName = record.getSorterName();
            int elementCount = record.getNumberOfElements();

            if (sorterNameToColumn.get(sorterName) == null) {
                cell = headerRow.createCell(colIndex);
                cell.setCellValue(sorterName);
                sorterNameToColumn.put(sorterName, colIndex++);
            }

            if (elmCountToRow.get(elementCount) == null) {
                row = sheet.createRow(rowIndex++);
                cell = row.createCell(0);
                cell.setCellValue(elementCount);
                elmCountToRow.put(elementCount, row);

                cell = row.createCell((Integer) sorterNameToColumn.get(sorterName));
                cell.setCellValue(record.getTime());
                continue;
            }

            row = (XSSFRow) elmCountToRow.get(elementCount);
            cell = row.createCell((Integer) sorterNameToColumn.get(sorterName));
            cell.setCellValue(record.getTime());
        }
        return new TableBounds(1, 1, 7, 8)
                .setHeader(0, 1, 0, 7)
                .setSideHeader(1, 0, 7, 0);
    }

    public void createGraph(XSSFSheet sheet, TableBounds bounds) {
        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 10, 20, 35);

        XSSFChart chart = drawing.createChart(anchor);
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.RIGHT);

        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        bottomAxis.setTitle("elements");
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        leftAxis.setTitle("ticks");

        XDDFLineChartData data = (XDDFLineChartData) chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);

        XDDFNumericalDataSource<Double> x = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(bounds.getSideHeaderRow1(), bounds.getSideHeaderRow2(), bounds.getSideHeaderCol1(), bounds.getSideHeaderCol2()));

        XSSFRow headRow = sheet.getRow(bounds.getHeaderRow1());
        for (int col = bounds.getBodyCol1(); col <= bounds.getBodyCol2(); col++) {
            XDDFNumericalDataSource<Double> y = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(bounds.getBodyRow1(), bounds.getBodyRow2(), col, col));
            XDDFLineChartData.Series series1 = (XDDFLineChartData.Series) data.addSeries(x, y);

            series1.setTitle(headRow.getCell(col).getStringCellValue(), null);
            series1.setMarkerStyle(MarkerStyle.NONE);
        }

        chart.plot(data);

    }
}