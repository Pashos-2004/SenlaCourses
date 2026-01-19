package autoService.DI;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import autoService.DI.annotations.*;

public class DIContainer {
    private static DIContainer instance;
    private final Map<String, Object> singletonBeans = new ConcurrentHashMap<>();
    private final Map<String, Class<?>> beanDefinitions = new ConcurrentHashMap<>();
    private final Map<String, Object> configBeans = new ConcurrentHashMap<>();
    private final Set<Class<?>> scannedClasses = new HashSet<>();
    
    private DIContainer() {
    }
    
    public static synchronized DIContainer getInstance() {
        if (instance == null) {
            instance = new DIContainer();
        }
        return instance;
    }
    
    public void initialize(String... basePackages) {
        System.out.println("Инициализация DI контейнера...");
        
        try {
           
            for (String basePackage : basePackages) {
            	registerClasses();
            }
            
            initializeSingletonBeans();
            
            System.out.println("DI контейнер успешно инициализирован");
            System.out.println("Зарегистрировано бинов: " + singletonBeans.size());
            
        } catch (Exception e) {
            throw new RuntimeException("Ошибка инициализации DI контейнера", e);
        }
    }

    
    private void registerClasses() {
       
        registerComponent(autoService.config.ConfigManager.class);
        registerComponent(autoService.config.StateManager.class);
        registerComponent(autoService.config.AppDIConfig.class);
        
        registerComponent(autoService.controller.AutoServiceController.class);
        
        registerComponent(autoService.service.CsvService.class);
        
        registerComponent(autoService.view.MenuNavigator.class);
        registerComponent(autoService.view.AutoServiceConsoleUI.class);
        
        registerComponent(autoService.view.menuFactory.MainMenuFactory.class);
        registerComponent(autoService.view.menuFactory.MastersMenuFactory.class);
        registerComponent(autoService.view.menuFactory.GarageSpotsMenuFactory.class);
        registerComponent(autoService.view.menuFactory.OrdersMenuFactory.class);
        registerComponent(autoService.view.menuFactory.ReportsMenuFactory.class);
    }
    
   
    
    private void registerComponent(Class<?> class_) {
        if (class_.isAnnotationPresent(Component.class) ||
        		class_.isAnnotationPresent(Configuration.class)) {
            
            String beanName = getBeanName(class_);
            beanDefinitions.put(beanName, class_);
            scannedClasses.add(class_);
            
            System.out.println("Зарегистрирован компонент: " + class_.getName() + 
                             " как " + beanName);
        }
    }
    
    private void initializeSingletonBeans() {
        for (Map.Entry<String, Class<?>> entry : beanDefinitions.entrySet()) {
            String beanName = entry.getKey();
            Class<?> beanClass = entry.getValue();
            
            if (isSingleton(beanClass)) {
                getBean(beanName);
            }
        }
    }
    
    
    public <T> T getBean(String name) {
    	
        if (singletonBeans.containsKey(name)) {
            return (T) singletonBeans.get(name);
        }
        
        
        Class<?> beanClass = beanDefinitions.get(name);
        if (beanClass == null) {
            throw new RuntimeException("Bean не найден: " + name);
        }
        
        
        T bean = (T) createBeanInstance(beanClass);
        
        
        if (isSingleton(beanClass)) {
            singletonBeans.put(name, bean);
        }
        
        return bean;
    }
    
 
    public <T> T getBean(Class<T> type) {
    	
        for (Map.Entry<String, Class<?>> entry : beanDefinitions.entrySet()) {
            if (type.isAssignableFrom(entry.getValue())) {
                return (T) getBean(entry.getKey());
            }
        }
        
        throw new RuntimeException("Bean типа " + type.getName() + " не найден");
    }
    
    private <T> T createBeanInstance(Class<T> beanClass) {
        try {
           
            Constructor<T> injectConstructor = findInjectConstructor(beanClass);
            
            T instance;
            if (injectConstructor != null) {
                instance = createInstanceViaConstructor(injectConstructor);
            } else {
                instance = beanClass.getDeclaredConstructor().newInstance();
            }
            
            injectFieldDependencies(instance);
            
            invokeInitMethods(instance);
            
            return instance;
            
        } catch (Exception e) {
            throw new RuntimeException("Не удалось создать экземпляр " + 
                                     beanClass.getName(), e);
        }
    }
    
    private <T> Constructor<T> findInjectConstructor(Class<T> beanClass) {
        for (Constructor<?> constructor : beanClass.getDeclaredConstructors()) {
            if (constructor.isAnnotationPresent(Inject.class)) {
                return (Constructor<T>) constructor;
            }
        }
        return null;
    }
    
    private <T> T createInstanceViaConstructor(Constructor<T> constructor) 
            throws Exception {
        
        constructor.setAccessible(true);
        Parameter[] parameters = constructor.getParameters();
        Object[] args = new Object[parameters.length];
        
        for (int i = 0; i < parameters.length; i++) {
            Parameter param = parameters[i];
            args[i] = resolveDependency(param);
        }
        
        return constructor.newInstance(args);
    }
    

    private void injectFieldDependencies(Object instance) throws Exception {
        Class<?> clazz = instance.getClass();
        
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                Object dependency = resolveDependency(field);
                field.set(instance, dependency);
            }
        }
    }
    

    private Object resolveDependency(AnnotatedElement element) {
    	if (element instanceof Field) {
            Field field = (Field) element;
            Class<?> fieldType = field.getType();
            return getBean(fieldType);
            
            
        } else if (element instanceof Parameter) {
            Parameter param = (Parameter) element;
            Class<?> paramType = param.getType();
            return getBean(paramType);
            
        }
        
        throw new RuntimeException("Не удалось разрешить зависимость");
    }
    
    private void invokeInitMethods(Object instance) throws Exception {
        Class<?> clazz = instance.getClass();
        
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getName().startsWith("init") || 
                method.getName().equals("initialize")) {
                
                method.setAccessible(true);
                method.invoke(instance);
            }
        }
    }
    

    private boolean isSingleton(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Component.class)) {
            Component component = clazz.getAnnotation(Component.class);
            return component.scope() == ScopeType.SINGLETON;
        }
        
        if (clazz.isAnnotationPresent(Configuration.class)) {
            return true; 
        }
        
        return true;
    }
    
    private String getBeanName(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Component.class)) {
            Component component = clazz.getAnnotation(Component.class);
            if (!component.name().isEmpty()) {
                return component.name();
            }
        }
        
        String className = clazz.getSimpleName();
        return Character.toLowerCase(className.charAt(0)) + className.substring(1);
    }
    
    public void shutdown() {
        singletonBeans.clear();
        beanDefinitions.clear();
        configBeans.clear();
        scannedClasses.clear();
        System.out.println("DI контейнер остановлен");
    }
    
    public void printBeansInfo() {
        System.out.println("\n=== Информация о бинах DI контейнера ===");
        System.out.println("Всего бинов: " + beanDefinitions.size());
        
        for (Map.Entry<String, Class<?>> entry : beanDefinitions.entrySet()) {
            String beanName = entry.getKey();
            Class<?> beanClass = entry.getValue();
            String scope = isSingleton(beanClass) ? "SINGLETON" : "PROTOTYPE";
            
            System.out.println(String.format("%-30s -> %-40s [%s]", 
                beanName, beanClass.getName(), scope));
        }
        System.out.println("========================================\n");
    }
}