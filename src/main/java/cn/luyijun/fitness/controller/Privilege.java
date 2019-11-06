package cn.luyijun.fitness.controller;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface Privilege {
    String[] value() default {};
}
