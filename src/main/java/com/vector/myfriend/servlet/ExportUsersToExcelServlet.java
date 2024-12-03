package com.vector.myfriend.servlet;

import com.vector.myfriend.model.User;
import com.vector.myfriend.service.UserService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/exportUsersToExcel")
public class ExportUsersToExcelServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("user");
        List<User> usersExceptCurrent = userService.getUsersExceptCurrent(currentUser.getId());

        Workbook workbook = new XSSFWorkbook();
        Sheet usersSheeet = workbook.createSheet("Users");
        Row headerRow = usersSheeet.createRow(0);

        Cell nameCell = headerRow.createCell(0);
        nameCell.setCellValue("Name");
        usersSheeet.autoSizeColumn(0);
        Cell surnameCell = headerRow.createCell(1);
        surnameCell.setCellValue("Surname");
        usersSheeet.autoSizeColumn(1);
        Cell emailCell = headerRow.createCell(2);
        emailCell.setCellValue("Email");
        usersSheeet.autoSizeColumn(2);


        CellStyle headerStyle = workbook.createCellStyle();
//        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
//        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);


        nameCell.setCellStyle(headerStyle);
        surnameCell.setCellStyle(headerStyle);
        emailCell.setCellStyle(headerStyle);

        Font font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 17);
        font.setBold(true);
        headerStyle.setFont(font);
        int rowIndex = 1;
        for (User user : usersExceptCurrent) {
            Row row = usersSheeet.createRow(rowIndex++);
            nameCell = row.createCell(0);
            nameCell.setCellValue(user.getName());
            usersSheeet.autoSizeColumn(0);
            surnameCell = row.createCell(1);
            surnameCell.setCellValue(user.getSurname());
            emailCell = row.createCell(2);
            usersSheeet.autoSizeColumn(1);
            emailCell.setCellValue(user.getEmail());
            usersSheeet.autoSizeColumn(2);
        }
        resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        resp.setHeader("Content-Disposition", "attachment; filename=users.xlsx");
        workbook.write(resp.getOutputStream());
    }
}
