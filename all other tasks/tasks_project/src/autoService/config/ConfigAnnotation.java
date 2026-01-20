package autoService.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigAnnotation {
    String configFileName() default "autoservice.conf";
    String propertyName() default "";
    Class<?> type() default String.class;
}
