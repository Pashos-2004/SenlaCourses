package autoService.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

import autoService.exception.ConfigException;


public class ConfigManager {
	private static final String CONFIG_FILE = "autoservice.conf";
	
	private static final String CAN_ADD_REMOVE_GARAGE_SPOTS_PROPERTY_NAME = "garage.spots.modification.enabled";
	private static final String CAN_SHIFT_ORDER_TIME_PROPERTY_NAME = "order.time.shift.enabled";
	private static final String CAN_DELETE_ORDER_PROPERTY_NAME = "order.deletion.enabled";
	
	private static ConfigManager instance;
	

	@ConfigAnnotation(configFileName = CONFIG_FILE,propertyName = CAN_ADD_REMOVE_GARAGE_SPOTS_PROPERTY_NAME, type = Boolean.class)
	private boolean garageSpotsModificationEnabled;
	    
	@ConfigAnnotation(propertyName = CAN_SHIFT_ORDER_TIME_PROPERTY_NAME, type = Boolean.class)
	private boolean orderTimeShiftEnabled;
	    
	@ConfigAnnotation(propertyName = CAN_DELETE_ORDER_PROPERTY_NAME, type = Boolean.class)
	private boolean orderDeletionEnabled ;

	private static final Properties DEFAULT_PROPERTIES = new Properties();
	static {
		DEFAULT_PROPERTIES.setProperty(CAN_ADD_REMOVE_GARAGE_SPOTS_PROPERTY_NAME, "false");
		DEFAULT_PROPERTIES.setProperty(CAN_SHIFT_ORDER_TIME_PROPERTY_NAME, "false");
		DEFAULT_PROPERTIES.setProperty(CAN_DELETE_ORDER_PROPERTY_NAME, "false");
	}

	private void setDefaultProperties() {
		garageSpotsModificationEnabled = false;
		orderTimeShiftEnabled = false;
		orderDeletionEnabled = false;
		
	}
	
	private ConfigManager() {
		//properties = new Properties(DEFAULT_PROPERTIES);
		
		loadConfigurationFromAnnotations();
		/*
		System.out.println(isGarageSpotsModificationEnabled());
		System.out.println(isOrderDeletionEnabled());
		System.out.println(isOrderTimeShiftEnabled());*/
	}

	public static ConfigManager getInstance() {
		if (instance == null) {
			instance = new ConfigManager();
		}
		return instance;
	}
	
	private void loadConfigurationFromAnnotations() {
		
		 try {
			 
	            Class<?> configClass = this.getClass();
	            for (Field field : configClass.getDeclaredFields()) {
	                if (field.isAnnotationPresent(ConfigAnnotation.class)) {
	                	ConfigAnnotation annotation = field.getAnnotation(ConfigAnnotation.class);
	                    
	                    String configFileName = annotation.configFileName();
	                    String propertyName = annotation.propertyName();
	                    Class<?> type = annotation.type();
	                    
	                    if (propertyName == null || propertyName.isEmpty()) {
	                    	throw new ConfigException("The configuration name is empty, the data cannot be loaded");
	                    }
	                    
	                    File configFile = new File(configFileName);
	                    if (configFile.exists()) {
	                    	try (FileInputStream fis = new FileInputStream(configFile)) {
	            				Properties properties = new Properties();
	            				properties.load(fis);
	             				field.set(this, convertStringToType(properties.getProperty(propertyName), annotation.type()));
	            			} catch (IOException e) {
	            				System.out.println("Ошибка загрузки конфигурации для поля: "+propertyName+" \n" + e.getMessage());
	            				System.out.println("Используются настройки по умолчанию");
	            				
	            				field.set(configClass, convertStringToType(DEFAULT_PROPERTIES.getProperty(propertyName), annotation.type()));
	            			}
	                    }else {
	                    	System.out.println("Нет файла конфигурации: "+configFileName+"\nдля поля :"+propertyName);
	                    	System.out.println("Используется значение по умолчанию ");
	                    	field.set(configClass, convertStringToType(DEFAULT_PROPERTIES.getProperty(propertyName), annotation.type()));
	                    	
	                    }
	                   
	                }
	            }
	            
	            System.out.println("Конфигурация загружена с использованием аннотаций");
	            
	        } catch (Exception e) {
	            System.out.println("Ошибка загрузки конфигурации: " + e.getMessage());
	            System.out.println("Используются значения по умолчанию");
	            setDefaultProperties();
	        }
		
	}
	
	
	 private Object convertStringToType(String value, Class<?> targetType) {

	        
	        try {
	            if (targetType == boolean.class || targetType == Boolean.class) {
	                return Boolean.parseBoolean(value);
	            } else if (targetType == int.class || targetType == Integer.class) {
	                return Integer.parseInt(value);
	            } else if (targetType == long.class || targetType == Long.class) {
	                return Long.parseLong(value);
	            } else if (targetType == double.class || targetType == Double.class) {
	                return Double.parseDouble(value);
	            } else if (targetType == float.class || targetType == Float.class) {
	                return Float.parseFloat(value);
	            } else if (targetType == String.class) {
	                return value;
	            } else {
	                
	                return null;
	            }
	        } catch (Exception e) {
	            System.out.println("Ошибка преобразования значения '" + value + 
	                             "' к типу " + targetType.getName());
	           
	        }
			return null;
	    }
	
	
	private void createDefaultConfigFile() {
		try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
			DEFAULT_PROPERTIES.store(fos, "Автосервис - конфигурационные параметры");
			System.out.println("Создан файл конфигурации по умолчанию: " + CONFIG_FILE);
		} catch (IOException e) {
			System.out.println("Ошибка создания файла конфигурации: " + e.getMessage());
		}
	}

	public boolean isGarageSpotsModificationEnabled() {
		return garageSpotsModificationEnabled;
	}

	public boolean isOrderTimeShiftEnabled() {
		return orderTimeShiftEnabled;
	}

	public boolean isOrderDeletionEnabled() {
		return orderDeletionEnabled;
	}

}
