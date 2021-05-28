package com.woowahan.woowahanadminservice.domain.user.util;

import com.woowahan.woowahanadminservice.domain.user.entity.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {

    public static ByteArrayInputStream exportUserToExcel(List<User> users) throws IOException {
        String[] COLUMN = {"Email Id", "Name", "Rank", "Score"};

        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {
            Sheet sheet = workbook.createSheet("Users");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMN.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMN[col]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowIdx = 1;
            for (User user : users) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(user.getEmailId());
                row.createCell(1).setCellValue(user.getName());
                row.createCell(2).setCellValue(user.getRank());
                row.createCell(3).setCellValue(user.getScore());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
