package com.ckb.ssm.mapper;

import com.ckb.ssm.entity.User;
import com.ckb.ssm.entity.UserExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * 获取用户数量
     * @param example
     * @return
     */
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 增加用户
     * @param record
     * @return
     */
    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User login(Map<String, String> map);

    List<User> getUserList();
}