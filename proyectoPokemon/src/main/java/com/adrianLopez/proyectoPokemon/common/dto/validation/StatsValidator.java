package com.adrianLopez.proyectoPokemon.common.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StatsValidator implements ConstraintValidator<ValidStat, Integer> {

    private String message;

    @Override
    public void initialize(ValidStat constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Integer stat, ConstraintValidatorContext constraintValidatorContext) {
        return (stat == null || (stat >= 1 && stat <= 255));
    }
}
