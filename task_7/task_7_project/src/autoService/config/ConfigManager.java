package autoService.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
	private static final String CONFIG_FILE = "autoservice.conf";
	private static ConfigManager instance;
	private Properties properties;

	public static final String CAN_ADD_REMOVE_GARAGE_SPOTS = "garage.spots.modification.enabled";
	public static final String CAN_SHIFT_ORDER_TIME = "order.time.shift.enabled";
	public static final String CAN_DELETE_ORDER = "order.deletion.enabled";

	private static final Properties DEFAULT_PROPERTIES = new Properties();
	static {
		DEFAULT_PROPERTIES.setProperty(CAN_ADD_REMOVE_GARAGE_SPOTS, "false");
		DEFAULT_PROPERTIES.setProperty(CAN_SHIFT_ORDER_TIME, "false");
		DEFAULT_PROPERTIES.setProperty(CAN_DELETE_ORDER, "false");
	}

	private ConfigManager() {
		properties = new Properties(DEFAULT_PROPERTIES);
		loadConfiguration();
		/*System.out.println(isGarageSpotsModificationEnabled());
		System.out.println(isOrderDeletionEnabled());
		System.out.println(isOrderTimeShiftEnabled());*/
	}

	public static ConfigManager getInstance() {
		if (instance == null) {
			instance = new ConfigManager();
		}
		return instance;
	}

	private void loadConfiguration() {
		File configFile = new File(CONFIG_FILE);
		if (configFile.exists()) {
			try (FileInputStream fis = new FileInputStream(configFile)) {
				properties.load(fis);
				System.out.println("Конфигурация загружена из файла: " + CONFIG_FILE);
			} catch (IOException e) {
				System.out.println("Ошибка загрузки конфигурации: " + e.getMessage());
				System.out.println("Используются настройки по умолчанию");
			}
		} else {
			createDefaultConfigFile();
		}
	}

	private void createDefaultConfigFile() {
		try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
			properties.store(fos, "Автосервис - конфигурационные параметры");
			System.out.println("Создан файл конфигурации по умолчанию: " + CONFIG_FILE);
		} catch (IOException e) {
			System.out.println("Ошибка создания файла конфигурации: " + e.getMessage());
		}
	}

	public boolean isGarageSpotsModificationEnabled() {
		return Boolean.parseBoolean(properties.getProperty(CAN_ADD_REMOVE_GARAGE_SPOTS));
	}

	public boolean isOrderTimeShiftEnabled() {
		return Boolean.parseBoolean(properties.getProperty(CAN_SHIFT_ORDER_TIME));
	}

	public boolean isOrderDeletionEnabled() {
		return Boolean.parseBoolean(properties.getProperty(CAN_DELETE_ORDER));
	}

	public void setGarageSpotsModificationEnabled(boolean enabled) {
		properties.setProperty(CAN_ADD_REMOVE_GARAGE_SPOTS, String.valueOf(enabled));
	}

	public void setOrderTimeShiftEnabled(boolean enabled) {
		properties.setProperty(CAN_SHIFT_ORDER_TIME, String.valueOf(enabled));
	}

	public void setOrderDeletionEnabled(boolean enabled) {
		properties.setProperty(CAN_DELETE_ORDER, String.valueOf(enabled));
	}

	public Properties getAllProperties() {
		return new Properties(properties);
	}

	public void updateProperty(String key, String value) {
		properties.setProperty(key, value);
	}
}
