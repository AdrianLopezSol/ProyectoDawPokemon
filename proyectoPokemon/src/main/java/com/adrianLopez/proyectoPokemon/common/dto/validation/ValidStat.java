package com.adrianLopez.proyectoPokemon.common.dto.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StatsValidator.class)
public @interface ValidStat {
    String message() default "Una estadistica debe ser positiva y no superior a 256";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
