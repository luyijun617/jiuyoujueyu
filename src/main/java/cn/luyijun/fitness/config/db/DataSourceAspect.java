package cn.luyijun.fitness.config.db;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by Yao on 2016/9/28.
 */
@Component
@Aspect
public class DataSourceAspect {
//    @Before("( execution(* com.putiandi.passservice.api.*.service..*.*(..)) || execution(* com.putiandi.passservice.api.*.service..*.*(..))) && !@annotation(org.springframework.transaction.annotation.Transactional) && !@annotation(com.putiandi.passservice.bootstrap.data.WritingDataSource)")
    @Before("( @annotation(org.springframework.transaction.annotation.Transactional) && @annotation(cn.luyijun.fitness.config.db.WritingDataSource) )")
    public void doBefore(JoinPoint joinPoint) {
        DataSourceHolder.setDataSource(DataSourceType.READ);
    }

//    @After("(execution( !@annotation(org.springframework.transaction.annotation.Transactional) && !@annotation(cn.luyijun.fitness.bootstrap.data.WritingDataSource)))")
//    public void after(JoinPoint joinPoint) {
//        DataSourceHolder.setDataSource(DataSourceType.WRITE);
//    }

}
