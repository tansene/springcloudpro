package com.tansene.annotation;


import java.lang.annotation.*;

/**
 * 在Controller方法上加入该注解不会验证身份
 * @author : tansene
 * @date : 2020/11/20
 */
@Target( { ElementType.METHOD } )
@Retention( RetentionPolicy.RUNTIME )
@Documented
public @interface Pass {

}
