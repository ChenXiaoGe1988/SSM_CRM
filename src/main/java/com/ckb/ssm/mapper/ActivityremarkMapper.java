package com.ckb.ssm.mapper;

import com.ckb.ssm.entity.Activityremark;
import com.ckb.ssm.entity.ActivityremarkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityremarkMapper {
    long countByExample(ActivityremarkExample example);

    int deleteByExample(ActivityremarkExample example);

    int deleteByPrimaryKey(String id);

    int insert(Activityremark record);

    int insertSelective(Activityremark record);

    List<Activityremark> selectByExample(ActivityremarkExample example);

    Activityremark selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Activityremark record, @Param("example") ActivityremarkExample example);

    int updateByExample(@Param("record") Activityremark record, @Param("example") ActivityremarkExample example);

    int updateByPrimaryKeySelective(Activityremark record);

    int updateByPrimaryKey(Activityremark record);


    int getCountByAids(String[] ids);

    int deleteByAids(String[] ids);
}