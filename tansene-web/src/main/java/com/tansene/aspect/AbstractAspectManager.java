package com.tansene.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @author tansene
 * @since on 2020/11/20.
 */
public abstract class AbstractAspectManager implements AspectApi{

    private AspectApi aspectApi;

    public AbstractAspectManager(AspectApi aspectApi){
        this.aspectApi=aspectApi;
    }

    public  Object doHandlerAspect(ProceedingJoinPoint pjp, Method method)throws Throwable{
        return this.aspectApi.doHandlerAspect(pjp,method);
    }

    protected abstract Object execute(ProceedingJoinPoint pjp, Method method)throws Throwable;

}
