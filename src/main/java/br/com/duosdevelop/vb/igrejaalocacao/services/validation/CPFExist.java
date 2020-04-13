package br.com.duosdevelop.vb.igrejaalocacao.services.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {CPFExistValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CPFExist {

    String message() default "{br.com.duosdevelop.vb.igrejaalocacao.services.validation.CPFExist.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
