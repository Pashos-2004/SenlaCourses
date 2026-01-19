package autoService.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import autoService.DI.annotations.Component;
import autoService.DI.annotations.Inject;
import autoService.model.AutoService;

@Component(name = "stateManager")
public class StateManager {
	private static final String STATE_FILE = "autoservice_state.save";
	private static StateManager instance;
	
	@Inject
	private StateManager() {
	}

	
	public static StateManager getInstance() {
		if (instance == null) {
			instance = new StateManager();
		}
		return instance;
	}

	public void saveState(AutoService autoService) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STATE_FILE))) {
			oos.writeObject(autoService);
			System.out.println("Состояние программы сохранено в файл: " + STATE_FILE);
		} catch (IOException e) {
			System.out.println("Ошибка сохранения состояния: " + e.getMessage());
		}
	}

	public AutoService loadState() {
		File stateFile = new File(STATE_FILE);

		if (!stateFile.exists()) {
			System.out.println("Файл состояния не найден. Создана новая система.");
			return new AutoService();
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STATE_FILE))) {
			AutoService autoService = (AutoService) ois.readObject();
			System.out.println("Состояние программы загружено из файла: " + STATE_FILE);
			return autoService;
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Ошибка загрузки состояния: " + e.getMessage());
			System.out.println("Создана новая система.");
			return new AutoService();
		}
	}

	public boolean stateFileExists() {
		return new File(STATE_FILE).exists();
	}
}