package com.andikscript.simpleusermongodb.constrainst.phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<PhoneValue, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Double phone = Double.valueOf(s.substring(1));
            if (s.contains("+") && s.length() == 14) {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
