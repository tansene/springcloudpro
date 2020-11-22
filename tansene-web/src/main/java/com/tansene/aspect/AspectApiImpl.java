package com.tansene.aspect;

import com.tansene.base.Constant;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * 基本被装饰类,做一些公共处理
 * Created by tansene on 2020/11/20.
 */
public class AspectApiImpl implements AspectApi {

    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {
        Constant.isPass=false;
        return null;
    }
}
