package com.andikscript.simpleusermongodb.constrainst.phone;
import com.andikscript.simpleusermongodb.constrainst.email.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PhoneValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PhoneValue {

    String message() default "{com.andikscript.simpleusermongodb.constrainst.phone.PhoneValue.message}";

    Class<?> [] groups() default{};

    Class<? extends Payload> [] payload() default {};
}
