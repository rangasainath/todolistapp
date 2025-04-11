package com.Application.todolistapp.Aspect;

import jakarta.persistence.SequenceGenerator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TaskAspect {

    @Pointcut("execute( com.Application.todolistapp.Service.createTaskService()")
    public void  pointcutDefinitionPackage(){
    }

    @Before("pointcutDefinitionPackage()")
    public void beforecreatetaskMethod(JoinPoint joinpoint){
        System.out.println("Before Method:"+joinpoint.getSignature().getName());
        System.out.println("Task will be created in this function.");
    }

    @After("pointcutDefinitionPackage()")
            public void aftercreatetaskMethod(JoinPoint joinpoint)
    {
        System.out.println("Before method:"+joinpoint.getSignature().getName());
        System.out.println("Task has been created.");
    }


}
