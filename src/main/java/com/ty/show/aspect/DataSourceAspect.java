package com.ty.show.aspect;
import com.ty.show.annotation.TargetDataSource;
import com.ty.show.datasource.DynamicDataSourceHolder;
import com.ty.show.annotation.TargetDataSource;
import com.ty.show.datasource.DynamicDataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 数据源AOP切面定义
 */
@Aspect
@Component
@Slf4j
public class DataSourceAspect {

    @Before("@annotation(targetDataSource)")
    public void switchDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        if (!DynamicDataSourceHolder.containDataSourceKey(targetDataSource.value())) {
            System.out.println("DataSource [{}] doesn't exist, use default DataSource [{}] " + targetDataSource.value());
        } else {
            // 切换数据源
            DynamicDataSourceHolder.setDataSourceKey(targetDataSource.value());
            System.out.println("Switch DataSource to [{}] in Method [{}] " +
            DynamicDataSourceHolder.getDataSourceKey() + joinPoint.getSignature());
        }

    }

    //执行完切面后，将线程共享中的数据源名称清空
    @After("@annotation(targetDataSource)")
    public void restoreDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource){
        // 将数据源置为默认数据源
        DynamicDataSourceHolder.clearDataSourceKey();
        System.out.println("Restore DataSource to [{}] in Method [{}] " +
                DynamicDataSourceHolder.getDataSourceKey() + joinPoint.getSignature());
    }
}
