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
			 writer.write(String.join(",", escapeData(header)));
			 writer.newLine();
			 for(String[] row : data) {
				 
				 writer.write(String.join(",", escapeData(row)));
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
            
            //String[] headers = headerLine.split(",");
            String[] headers = parseCSVLine(headerLine);
            headers = unescapeData(headers);
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
		//String[] values = line.split(",");
		String [] values = unescapeData(parseCSVLine(line));
		Map<String, String> row = new HashMap<>();
		
		if(values.length!=headers.length) {
			throw new ParsingException("Строка не может быть преобразована, т.к. число значений не совпадаёт с числом заголовков");
		}
		
		for(int i = 0; i < headers.length ; i++) {
			row.put(headers[i], values[i]);
		}
		
		return row;
	}
	
	private static String[] escapeData(String[] data) {
		String[] escaped = new String[data.length];
		for(int i = 0; i < data.length; i++) {
			String value = data[i];
			value = value.replace("\\", "\\\\");
			value = value.replace(",", "\\,");
			value = value.replace("\n", "\\n");
			escaped[i] = value;
		}
		return escaped;
	}
	
	private static String[] unescapeData(String[] data) {
		String[] unescaped = new String[data.length];  
		for(int i = 0; i < data.length; i++) {
			String value = data[i];
			value = value.replace("\\n", "\n");
			value = value.replace("\\,", ",");
			value = value.replace("\\\\", "\\");
			unescaped[i] = value;
		}
		
		return unescaped;
	}
	
	private static String[] parseCSVLine(String line) {
		
		List<String> result = new ArrayList<>();
		StringBuilder current = new StringBuilder();
		boolean isEscapeStep = false;
		for(int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if(isEscapeStep) {
				current.append(c);
				isEscapeStep = false;
			} else if(c == '\\') {
				isEscapeStep = true;
				current.append(c);
			} else if(c == ',') {
				result.add(current.toString());
				current = new StringBuilder();
			} else {
				current.append(c);
			}
		}
		result.add(current.toString());
		return ((String[]) result.toArray(new String[0]));
	}
	
}
