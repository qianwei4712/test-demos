package cn.shiva.datasources.service;

import cn.shiva.datasources.config.DataSource;
import cn.shiva.datasources.config.DataSourceType;
import cn.shiva.datasources.mapper.GroupMapper;
import cn.shiva.datasources.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author shiva   2020/2/7 16:52
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GroupMapper groupMapper;

    public String getUser() {
        return userMapper.selectUser();
    }

    @DataSource(value = DataSourceType.db2)
    public String getUser2() {
        return groupMapper.selectGroup();
    }
}
