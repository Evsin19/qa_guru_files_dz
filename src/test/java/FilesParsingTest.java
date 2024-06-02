import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import models.Education;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class FilesParsingTest {

    private ClassLoader cl = FilesParsingTest.class.getClassLoader();

    @DisplayName("Проверка файла pdf из архива zip")
    @Test
    void pdfFileParsingTest() throws Exception {
        try(ZipInputStream zip = new ZipInputStream(
                cl.getResourceAsStream("hello.zip")
        )){
            ZipEntry entry;
            while((entry = zip.getNextEntry()) != null){
                if (entry.getName().equals("Hello.pdf")){
                    PDF pdf = new PDF(zip);
                    Assertions.assertEquals(1, pdf.numberOfPages);
                    Assertions.assertEquals("Привет, Мир! \n", pdf.text);
                }
            }
        }
    }

    @DisplayName("Проверка файла csv из архива zip")
    @Test
    void csvFileParsingTest() throws Exception {
        try (ZipInputStream zip = new ZipInputStream(
                cl.getResourceAsStream("hello.zip")
        )) {
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().equals("example.csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zip));
                    List<String[]> data = csvReader.readAll();
                    Assertions.assertEquals(2, data.size());
                    Assertions.assertArrayEquals(
                            new String[]{"Русский", " Привет Мир!"},
                            data.get(0)
                    );
                    Assertions.assertArrayEquals(
                            new String[]{"English", " Hello World!"},
                            data.get(1)
                    );
                }
            }
        }
    }

    @DisplayName("Проверка файла xlsx из архива zip")
    @Test
    void xlsxFileParsingTest() throws Exception {
        try (ZipInputStream zip = new ZipInputStream(
                cl.getResourceAsStream("hello.zip")
        )) {
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().equals("hello.xlsx")) {
                    XLS xls = new XLS(zip);;
                    Assertions.assertEquals(
                            "Привет, Мир!",
                            xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue());
                    Assertions.assertEquals(
                            "Hello, World!",
                            xls.excel.getSheetAt(0).getRow(1).getCell(0).getStringCellValue());
                }
            }
        }
    }

    @DisplayName("Проверка файла json")
    @Test
    void jsonFileParsingImprovedTest() throws Exception {

        try(InputStream js = cl.getResourceAsStream("education.json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            Education education = objectMapper.readValue(js, Education.class);

            Assertions.assertEquals("QA_Guru", education.getCourse());
            Assertions.assertEquals(234234, education.getId());
            Assertions.assertEquals("Дмитрий Тучс", education.getDetails().getTeacher());
            Assertions.assertEquals("AQA", education.getDetails().getSpeciality());;
            Assertions.assertArrayEquals(new String[]{"Junit 5", "Java", "Files"}, education.getDetails().getLectures());
        }
    }





}
