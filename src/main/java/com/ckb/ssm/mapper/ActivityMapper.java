package com.ckb.ssm.mapper;

import com.ckb.ssm.entity.Activity;
import com.ckb.ssm.entity.ActivityExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ActivityMapper {
    long countByExample(ActivityExample example);

    int deleteByExample(ActivityExample example);

    int deleteByPrimaryKey(String id);

    int insert(Activity record);

    int insertSelective(Activity record);

    List<Activity> selectByExample(ActivityExample example);

    Activity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Activity record, @Param("example") ActivityExample example);

    int updateByExample(@Param("record") Activity record, @Param("example") ActivityExample example);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    int save(Activity activity);

    int getTotalByCondition(Map<String, Object> map);

    List<Activity> getActivityListByCondition(Map<String, Object> map);

    int delete(String[] ids);

    int update(Activity activity);
}