package com.ckb.ssm.service.impl;

import com.ckb.ssm.entity.Activity;
import com.ckb.ssm.entity.User;
import com.ckb.ssm.mapper.ActivityMapper;
import com.ckb.ssm.mapper.ActivityremarkMapper;
import com.ckb.ssm.mapper.UserMapper;
import com.ckb.ssm.service.ActivityService;
import com.ckb.ssm.vo.PaginationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ActivityremarkMapper activityremarkMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean save(Activity activity) {
        boolean flag = true;
        int count = activityMapper.save(activity);
        if (count != 1) {
            flag = false;
        }
        return flag;
    }

    @Override
    public PaginationVO<Activity> pageList(Map<String, Object> map) {
        int total = activityMapper.getTotalByCondition(map);
        //取得市场活动信息列表 dataList
        List<Activity> dataList =  activityMapper.getActivityListByCondition(map);
        //创建一个vo对象将total和dataList封装到vo中
        PaginationVO<Activity> vo = new PaginationVO<Activity>();
        vo.setTotal(total);
        vo.setDataList(dataList);
        return vo;
    }

    @Override
    public boolean delete(String[] ids) {
        boolean flag = true;
        //查询出需要删除的备注的数量
        int count1 = activityremarkMapper.getCountByAids(ids);
        //删除备注,返回受到影响的条数
        int count2 = activityremarkMapper.deleteByAids(ids);
        if (count1 != count2) {
            flag = false;
        }

        //删除市场活动
        int count3 = activityMapper.delete(ids);
        if (count3 != ids.length) {
            flag = false;
        }
        return flag;
    }

    @Override
    public Map<String, Object> getUserListAndActivity(String id) {
        //取得用户列表
        List<User> userList = userMapper.getUserList();
        //根据id查询市场活动
        Activity activity = activityMapper.selectByPrimaryKey(id);
        //将userList和activity打包成map
        Map<String, Object> map = new HashMap<>();
        map.put("userList", userList);
        map.put("activity", activity);
        //返回map
        return map;
    }

    @Override
    public boolean update(Activity activity) {
        boolean flag = true;
        int count = activityMapper.update(activity);
        if (count != 1) {
            flag = false;
        }
        return flag;
    }
}
