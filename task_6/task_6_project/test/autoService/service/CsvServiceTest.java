package autoService.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CsvServiceTest {

    public static final String DATA_2 = "data2";
    public static final String HEADER_1 = "header1";
    public static final String HEADER_2 = "header2";

    @ParameterizedTest
    @CsvSource({
        "data1",
        "'data1,data1\ndata1'",
        "data1\\",
    })
    public void test1(String data1) throws IOException {
        File tempFile = File.createTempFile("prefix-", ".csv");
        try {
            System.out.println("temp file name: " + tempFile.getAbsolutePath());
            System.out.println("data1: " + data1);

            List<String[]> data = new ArrayList<>();
            data.add(new String[]{data1, DATA_2});
            CsvService.writeIntoCSV(tempFile.getAbsolutePath(), new String[]{HEADER_1, HEADER_2}, data);

            final List<Map<String, String>> rows = CsvService.readFromCSV(tempFile.getAbsolutePath());
            assertEquals(1, rows.size());
            final Map<String, String> row = rows.get(0);
            assertEquals(data1, row.get(HEADER_1));
            assertEquals(DATA_2, row.get(HEADER_2));
        } finally {
            tempFile.delete();
        }
    }

}