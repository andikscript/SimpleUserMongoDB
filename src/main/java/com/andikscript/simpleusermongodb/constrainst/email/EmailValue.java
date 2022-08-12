package com.andikscript.simpleusermongodb.constrainst.email;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EmailValue {

    String message() default "{com.andikscript.simpleusermongodb.constrainst.email.EmailValue.message}";

    Class<?> [] groups() default{};

    Class<? extends Payload> [] payload() default {};
}
