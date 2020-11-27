package com.ckb.ssm.service;

import com.ckb.ssm.entity.User;
import com.ckb.ssm.exception.LoginException;

import java.util.List;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;

    List<User> getUserList();
}
