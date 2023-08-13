package com.chuka.springaop.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* com.chuka.springaop.service.BookService.get*(..))")
    public void allGetMethods(){}

    @Pointcut("execution(* com.chuka.springaop.service.BookService.add*(..))")
    public void allAddMethods(){}

    @Pointcut("execution(* com.chuka.springaop.service.BookService.deleteBookByTitle(..))")
    public void allDeleteMethods(){}

}
