package com.plc.hmi.annotation;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.METHOD})
//@Constraint(checkWith=ValidRequiredHandler.class);
public @interface ValidRequired {
    String name()   default "";
    //验证失败返回错误信息
    String message()   default "[{%s}]必填";
    //适用场景
    String[] profiles()   default {};
    //条件必填关联字段
    String conField()   default "";
    //关联字段参考值
    String conValue()   default "";
    //关联字段参考值， 可以配多个
    String[] conValueRange()   default {};
    //关联字段不等于值
    String conNotValue()   default "";
    //关联字段不等于值范围
    String[] conNotValueRange()   default {};

}
