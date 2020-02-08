package cn.shiva.datasources.mapper;

import cn.shiva.datasources.config.DataSource;
import cn.shiva.datasources.config.DataSourceType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author shiva   2020/2/7 17:31
 */

@Mapper
@Repository
@Component
public interface UserMapper {

    @DataSource(value = DataSourceType.db1)
    String selectUser();
}
