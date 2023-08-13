package com.chuka.springaop.aop;

import com.chuka.springaop.entity.Book;
import com.chuka.springaop.exceptions.AlreadyExistsException;
import com.chuka.springaop.util.CustomResponse;
import com.chuka.springaop.util.CustomStatus;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@Aspect
@Slf4j
public class MyAspect {


    @Around("Pointcuts.allAddMethods()")
    public Object aroundAddingAdvice(ProceedingJoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Book book = null;
        if(methodSignature.getName().equals("addBook")){
            Object [] arguments = joinPoint.getArgs();
            for (Object arg : arguments){
                if(arg instanceof Book){
                    book = (Book) arg;
                    log.info("Поптыка добавления книги с названием {}", book.getTitle());
                }
            }
        }
        Object result = null;
        try {
            result = joinPoint.proceed();
        }catch (AlreadyExistsException e){
            log.error(e.getMessage(), e);
            return new CustomResponse<>(CustomStatus.ALREADY_EXIST);
        }catch (Throwable e){
            log.error(e.getMessage(), e);
            return new CustomResponse<>(null, CustomStatus.EXCEPTION);
        }
        log.info("Книга с названием {} добавлена", book.getTitle());
        return result;
    }

    @Around("Pointcuts.allGetMethods()")
    public Object aroundGettingAdvice(ProceedingJoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String title = null;
        if(methodSignature.getName().equals("getAll")){
            log.info("Попытка получить все книги");
        } else if (methodSignature.getName().equals("getBookByTitle")) {
            Object [] arguments = joinPoint.getArgs();
            for (Object arg : arguments){
                if(arg instanceof String){
                    title = (String) arg;
                    log.info("Попытка получить книгу с названием {}", title);
                }
            }
        }
        Object result = null;
        try{
            result = joinPoint.proceed();
        }catch (NoSuchElementException e) {
            log.error(e.getMessage(), e);
            return new CustomResponse<>(null, CustomStatus.NOT_FOUND);
        }catch (Throwable e) {
            log.error(e.getMessage(), e);
            return new CustomResponse<>(null, CustomStatus.EXCEPTION);
        }
        return result;
    }

    @Around("Pointcuts.allDeleteMethods()")
    public Object aroundAllDeleteMethods(ProceedingJoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String title = null;
        if(methodSignature.getName().equals("deleteBookByTitle")){
            Object [] arguments = joinPoint.getArgs();
            for (Object arg : arguments)
                if (arg instanceof String) {
                    title = (String) arg;
                    log.info("Попытка удаления киги с наименованием {}", title);
                }
        }
        Object result = null;
        try {
            result = joinPoint.proceed();
        }catch (NoSuchElementException e) {
            log.error(e.getMessage(), e);
            return new CustomResponse<>(null, CustomStatus.NOT_FOUND);
        }catch (Throwable e) {
            log.error(e.getMessage(), e);
            return new CustomResponse<>(null, CustomStatus.EXCEPTION);
        }
        log.info("Книга с названием {} удалена", title);
        return result;
    }

}
