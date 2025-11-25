package autoService.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import autoService.exception.CsvOperationException;
import autoService.exception.ParsingException;


public class CsvService {
	
	public static void writeIntoCSV(String filename, String[] header, List<String[]> data) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
			 writer.write(String.join(",", header));
			 writer.newLine();
			 for(String[] row : data) {
				 writer.write(String.join(",", row));
				 writer.newLine();
			 }
			
		} catch (IOException e) {
			throw new CsvOperationException("Ошибка при экспорте в CSV: " + e.getMessage(), e);
		}
	}
	
	public static List<Map<String, String>> readFromCSV(String filename) {
		try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
			String headerLine = reader.readLine();
            if (headerLine == null) {
                throw new CsvOperationException("Файл пуст или не содержит заголовка");
            }
            
            String[] headers = headerLine.split(",");
            List<Map<String, String>> data = new ArrayList<Map<String,String>>();
            String line = null;
            while((line = reader.readLine())!=null) {
            	data.add(parseCSVLineToMap(headers,line));
            }
			
            return data;
            
		} catch (IOException  e) {
			throw new CsvOperationException("Ошибка при импорте заказов из CSV: " + e.getMessage(), e);
		} catch ( ParsingException e) {
			throw new CsvOperationException("Ошибка при импорте заказов из CSV, файл признан невалидным для импорта: " + e.getMessage(), e);
		}
	}
    
	private static Map<String,String> parseCSVLineToMap(String[] headers, String line){
		String[] values = line.split(",");
		Map<String, String> row = new HashMap<>();
		
		if(values.length!=headers.length) {
			throw new ParsingException("Строка не может быть преобразована, т.к. число значений не совпадаёт с числом заголовков");
		}
		
		for(int i = 0; i < headers.length ; i++) {
			row.put(headers[i], values[i]);
		}
		
		return row;
	}
}
