package cn.shiva.datasources.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author shiva   2020/2/6 22:24
 */
@Slf4j
@Aspect
@Component
public class DataSourceAspect {
    @Before("@annotation(ds)")
    public void beforeDataSource(DataSource ds) {
        DataSourceType value = ds.value();
        DataSourceContextHolder.setDataSource(value);
        log.info("当前使用的数据源为：{}", value);
    }
    @After("@annotation(ds)")
    public void afterDataSource(DataSource ds){
        DataSourceContextHolder.clearDataSource();
    }
}
