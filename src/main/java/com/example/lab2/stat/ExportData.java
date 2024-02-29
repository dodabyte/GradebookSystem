package com.example.lab2.stat;

import java.io.File;
import java.util.Date;
import java.util.List;
import com.aspose.cells.*;

public class ExportData<T> {
    private List<T> list;
    private final Workbook book;
    private final String bookName;
    private final String fileName;

    private final String[] studentColumn = new String[]{"LastName", "FirstName", "Patronymic", "Group",
            "DateOfBirth", "DateAdmission", "FormOfEducation", "BasisOfEducation", "Address"};
    private final String[] studentHeader = new String[]{"Фамилия", "Имя", "Отчество", "Группа",
            "Дата рождения", "Дата поступления",  "Форма обучения", "Основа обучения", "Адрес"};

    private final String[] addressColumn = new String[]{"City", "Street", "HouseNumber", "ApartmentNumber"};
    private final String[] addressHeader = new String[]{"Город", "Улица", "Номер дома", "Номер квартиры"};

    private final String[] disciplinesColumn = new String[]{"Name", "TypeOfMark"};
    private final String[] disciplinesHeader = new String[]{"Название", "Тип оценки"};

    private final String[] groupColumn = new String[]{"Name", "Course", "Semester", "Specialization", "DateFormation", "DateGraduation"};
    private final String[] groupHeader = new String[]{"Название", "Курс", "Семестр", "Специализация", "Дата формирования", "Дата выпуска"};

    private final String[] parentColumn = new String[]{"LastName", "FirstName", "Patronymic", "Student", "Address"};
    private final String[] parentHeader = new String[]{"Фамилия", "Имя", "Отчество", "Студент", "Адрес"};

    private final String[] semesterPerformanceColumn = new String[]{"Student", "Course", "Semester", "Discipline",
            "Mark", "EctsMark", "TraditionalMark", "TraditionalWordMark"};
    private final String[] semesterPerformanceHeader = new String[]{"Студент", "Курс", "Семестр", "Дисциплина",
            "Оценка",  "ECTS оценка", "Трад. оценка", "Трад. текст. оценка"};

    private final String[] specializationColumn = new String[]{"Number", "Name", "StudyDuration"};
    private final String[] specializationHeader = new String[]{"Номер", "Название", "Продолжительность обучения (лет)"};

    private final String[] teacherColumn = new String[]{"LastName", "FirstName", "Patronymic", "DateOfBirth", "Post", "Department", "Address"};
    private final String[] teacherHeader = new String[]{"Фамилия", "Имя", "Отчество", "Дата рождения", "Должность",  "Кафедра", "Адрес"};

    private final String[] serviceTableColumn = new String[]{"Name"};
    private final String[] serviceTableHeader = new String[]{"Название"};

    public ExportData(String bookName) {
        book = new Workbook();
        this.bookName = bookName;
        long timestamp = new Date().getTime() % 1000;
        fileName = bookName + "_" + timestamp;
    }

    public ExportData(String bookName, List<T> list) {
        book = new Workbook();
        this.list = list;
        this.bookName = bookName;
        long timestamp = new Date().getTime() % 1000;
        fileName = bookName + "_" + timestamp;
    }

    public void exportList(int sheetIdx, String sheetName) throws Exception {
        if (!list.isEmpty()) {
            WorksheetCollection sheets = book.getWorksheets();

            while (sheets.getCount() <= sheetIdx) {
                sheets.add();
            }
            Worksheet sheet = book.getWorksheets().get(sheetIdx);
            sheet.setName((sheetIdx + 1) + ". " + sheetName);

            switch (list.get(0).getClass().getSimpleName()) {
                case "Student" -> {
                    sheet.getCells().importArray(studentHeader, 0, 0, false);
                    sheet.getCells().importCustomObjects(list, studentColumn, false, 1, 0, list.size(), true, null, false);
                }
                case "Teacher" -> {
                    sheet.getCells().importArray(teacherHeader, 0, 0, false);
                    sheet.getCells().importCustomObjects(list, teacherColumn, false, 1, 0, list.size(), true, null, false);
                }
                case "Address" -> {
                    sheet.getCells().importArray(addressHeader, 0, 0, false);
                    sheet.getCells().importCustomObjects(list, addressColumn, false, 1, 0, list.size(), true, null, false);
                }
                case "Discipline" -> {
                    sheet.getCells().importArray(disciplinesHeader, 0, 0, false);
                    sheet.getCells().importCustomObjects(list, disciplinesColumn, false, 1, 0, list.size(), true, null, false);
                }
                case "Group" -> {
                    sheet.getCells().importArray(groupHeader, 0, 0, false);
                    sheet.getCells().importCustomObjects(list, groupColumn, false, 1, 0, list.size(), true, null, false);
                }
                case "Specialization" -> {
                    sheet.getCells().importArray(specializationHeader, 0, 0, false);
                    sheet.getCells().importCustomObjects(list, specializationColumn, false, 1, 0, list.size(), true, null, false);
                }
                case "SemesterPerformance" -> {
                    sheet.getCells().importArray(semesterPerformanceHeader, 0, 0, false);
                    sheet.getCells().importCustomObjects(list, semesterPerformanceColumn, false, 1, 0, list.size(), true, null, false);
                }
                case "Parent" -> {
                    sheet.getCells().importArray(parentHeader, 0, 0, false);
                    sheet.getCells().importCustomObjects(list, parentColumn, false, 1, 0, list.size(), true, null, false);
                }
                case "BasisOfEducation", "FormOfEducation", "TypeOfMark", "Post", "Department" -> {
                    sheet.getCells().importArray(serviceTableHeader, 0, 0, false);
                    sheet.getCells().importCustomObjects(list, serviceTableColumn, false, 1, 0, list.size(), true, null, false);
                }
            }

            sheet.autoFitColumns();

            if (!checkDirExistence()) {
                throw new Exception("Error: Can't create stat directory!");
            }

            book.save("stat\\" + fileName + ".xlsx", SaveFormat.XLSX);
        }
    }

    private boolean checkDirExistence() {
        File file = new File("stat");

        if (!file.exists())
            if (!file.mkdirs())
                return false;

        return true;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
