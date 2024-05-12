package Spring.AsetProject.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import Spring.AsetProject.Entities.Employers;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static java.lang.String.format;


public class ExcelGenerator {
    public static ByteArrayInputStream studentToExcel(List<Employers> students) throws IOException {
        String[] columns = {"ID", "Личный номер", "Фамилия", "Имя", "Отчество", "Дата рождения", "Начало контракта", "Срок контракта", "Конец контракта", "Должность", "Звание", "Департамент"};
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

            //создаем лист
            Sheet sheet = workbook.createSheet("Employers Report");


            //задаем шрифт и цвет загаловка
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLACK.getIndex());



            //Границы заголовка
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setBorderBottom(BorderStyle.MEDIUM);
            headerCellStyle.setBorderTop(BorderStyle.MEDIUM);
            headerCellStyle.setBorderRight(BorderStyle.MEDIUM);
            headerCellStyle.setBorderLeft(BorderStyle.MEDIUM);



            //Row for Header-->
            Row headerRow = sheet.createRow(0);



            //Header
            for (int col = 0; col < columns.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columns[col]);
                cell.setCellStyle(headerCellStyle);
            }



            CellStyle rowStyle = workbook.createCellStyle();
            rowStyle.setBorderBottom(BorderStyle.MEDIUM);
            rowStyle.setBorderTop(BorderStyle.MEDIUM);
            rowStyle.setBorderRight(BorderStyle.MEDIUM);
            rowStyle.setBorderLeft(BorderStyle.MEDIUM);


            int rowIdx = 1;
            for (Employers stud : students) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(stud.getId());
                row.createCell(1).setCellValue(stud.getPersonalNumber());
                row.createCell(2).setCellValue(stud.getSurname());
                row.createCell(3).setCellValue(stud.getName());
                row.createCell(4).setCellValue(stud.getSecondName());
                row.createCell(5).setCellValue(format(stud.getBornDate().toString()));
                row.createCell(6).setCellValue(format(stud.getContractStart().toString()));
                row.createCell(7).setCellValue(stud.getContractTime());
                row.createCell(8).setCellValue(format(stud.getContractEnd().toString()));
                row.createCell(9).setCellValue(stud.getPosition().getPositionName());
                row.createCell(10).setCellValue(stud.getRanks().getRanksName());
                row.createCell(11).setCellValue(stud.getDepartment().getDepartmentName());
            }


            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}