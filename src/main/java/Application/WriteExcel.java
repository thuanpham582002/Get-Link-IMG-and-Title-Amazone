package Application;

import java.awt.print.Book;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import Model.ListProduct;
import Model.Product;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {

    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_TITLE = 1;
    public static final int COLUMN_INDEX_IMAGE1 = 2;
    public static final int COLUMN_INDEX_IMAGE2 = 3;
    public static final int COLUMN_INDEX_IMAGE3 = 4;
    public static final int COLUMN_INDEX_IMAGE4 = 5;
    public static final int COLUMN_INDEX_IMAGE5 = 6;
    public static final int COLUMN_INDEX_IMAGE6 = 7;
    public static final int COLUMN_INDEX_IMAGE7 = 8;
    public static final int COLUMN_INDEX_IMAGE8 = 9;
    public static final int COLUMN_INDEX_IMAGE9 = 10;
    public static final int COLUMN_INDEX_IMAGE10 = 11;
    private static CellStyle cellStyleFormatNumber = null;

    public static void writeExcel(List<ListProduct> listProducts, String nameExcel, String excelFilePath) throws IOException {
        // Create Workproduct
        Workbook workbook = getWorkbook(excelFilePath);

        // Create sheet
        Sheet sheet = workbook.createSheet(nameExcel); // Create sheet with sheet name

        int rowIndex = 0;

        // Write header
        writeHeader(sheet, rowIndex);

        // Write data
        rowIndex++;
        for (ListProduct listProduct : listProducts) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeproduct(listProduct.getProduct(), row);
            rowIndex++;

            for (Product product : listProduct.getListProduct()) {
                Row row1 = sheet.createRow(rowIndex);
                // Write data on row
                writeproduct(product, row1);
                rowIndex++;
            }
        }

        // Auto resize column witdth
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        // Create file excel
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }

    // Create workproduct
    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workproduct = null;

        if (excelFilePath.endsWith("xlsx")) {
            workproduct = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workproduct = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
        return workproduct;
    }

    // Write header with format
    private static void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);

        // Create row
        Row row = sheet.createRow(rowIndex);

        // Create cells
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Id");

        cell = row.createCell(COLUMN_INDEX_TITLE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tiêu đề");

        cell = row.createCell(COLUMN_INDEX_IMAGE1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Hình 1");

        cell = row.createCell(COLUMN_INDEX_IMAGE2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Hình 2");

        cell = row.createCell(COLUMN_INDEX_IMAGE3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Hình 3");

        cell = row.createCell(COLUMN_INDEX_IMAGE4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Hình 4");

        cell = row.createCell(COLUMN_INDEX_IMAGE5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Hình 5");

        cell = row.createCell(COLUMN_INDEX_IMAGE6);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Hình 6");

        cell = row.createCell(COLUMN_INDEX_IMAGE7);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Hình 7");

        cell = row.createCell(COLUMN_INDEX_IMAGE8);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Hình 8");

        cell = row.createCell(COLUMN_INDEX_IMAGE9);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Hình 9");

        cell = row.createCell(COLUMN_INDEX_IMAGE10);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Hình 10");

    }

    // Write data
    private static void writeproduct(Product product, Row row) {

        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellValue(product.getId());
        System.out.println(product.getId());

        cell = row.createCell(COLUMN_INDEX_TITLE);
        cell.setCellValue(product.getTitle());

        cell = row.createCell(COLUMN_INDEX_IMAGE1);
        if (product.getImages().size() > 0)
            cell.setCellValue(product.getImages().get(0));

        cell = row.createCell(COLUMN_INDEX_IMAGE2);
        if (product.getImages().size() > 1)
            cell.setCellValue(product.getImages().get(1));
        else
            cell.setCellValue("");

        cell = row.createCell(COLUMN_INDEX_IMAGE3);
        if (product.getImages().size() > 2)
            cell.setCellValue(product.getImages().get(2));
        else
            cell.setCellValue("");

        cell = row.createCell(COLUMN_INDEX_IMAGE4);
        if (product.getImages().size() > 3)
            cell.setCellValue(product.getImages().get(3));
        else
            cell.setCellValue("");
        cell = row.createCell(COLUMN_INDEX_IMAGE5);
        if (product.getImages().size() > 4)
            cell.setCellValue(product.getImages().get(4));
        else
            cell.setCellValue("");
        cell = row.createCell(COLUMN_INDEX_IMAGE6);
        if (product.getImages().size() > 5)
            cell.setCellValue(product.getImages().get(5));
        else
            cell.setCellValue("");
        cell = row.createCell(COLUMN_INDEX_IMAGE7);
        if (product.getImages().size() > 6)
            cell.setCellValue(product.getImages().get(6));
        else
            cell.setCellValue("");
        cell = row.createCell(COLUMN_INDEX_IMAGE8);
        if (product.getImages().size() > 7)
            cell.setCellValue(product.getImages().get(7));
        else
            cell.setCellValue("");
        cell = row.createCell(COLUMN_INDEX_IMAGE9);
        if (product.getImages().size() > 8)
            cell.setCellValue(product.getImages().get(8));
        else
            cell.setCellValue("");
        cell = row.createCell(COLUMN_INDEX_IMAGE10);
        if (product.getImages().size() > 9)
            cell.setCellValue(product.getImages().get(9));
        else
            cell.setCellValue("");

    }

    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    // Auto resize column width
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    // Create output file
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
}
