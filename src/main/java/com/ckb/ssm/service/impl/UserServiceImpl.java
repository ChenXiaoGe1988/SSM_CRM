package com.ckb.ssm.service.impl;

import com.ckb.ssm.entity.User;
import com.ckb.ssm.entity.UserExample;
import com.ckb.ssm.exception.LoginException;
import com.ckb.ssm.mapper.UserMapper;
import com.ckb.ssm.service.UserService;
import com.ckb.ssm.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录验证
     * @param loginAct
     * @param loginPwd
     * @param ip
     * @return
     * @throws LoginException
     */
    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {
        Map<String, String> map = new HashMap<>();
        map.put("loginAct", loginAct);
        map.put("loginPwd", loginPwd);

        User user = userMapper.login(map);
        //账号密码验证
        if (user == null) {
            throw new LoginException("账号密码错误");
        }
        //如果执行到这里,说明账号密码正确
        //验证实效时间
        String expireTime = user.getExpireTime();
        String currentTime = DateTimeUtil.getSysTime();
        if (expireTime.compareTo(currentTime) < 0) {
            throw new LoginException("账号已失效");
        }

        //验证锁定状态
        String lockState = user.getLockState();
        if ("0".equals(lockState)) {
            throw new LoginException("账号已锁定");
        }

        //判断ip地址
        String allowIps = user.getAllowIps();

        if(!allowIps.contains(ip)){
            throw new LoginException("ip地址受限");
        }
        return user;
    }

    @Override
    public List<User> getUserList() {
        //return userMapper.selectByExample(new UserExample());
        List<User> userList = userMapper.getUserList();
        return userList;
    }
}
