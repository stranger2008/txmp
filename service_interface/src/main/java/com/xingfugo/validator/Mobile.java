package com.xingfugo.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Pattern(regexp="^((13[0-9])|(147)|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$")
@Constraint(validatedBy={})
@ReportAsSingleViolation
public @interface Mobile {
	String message() default "{com.xingfugo.validator.Mobile.message}";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}
