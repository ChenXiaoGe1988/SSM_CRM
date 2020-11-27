package com.ckb.ssm.service;

import com.ckb.ssm.entity.Activity;
import com.ckb.ssm.vo.PaginationVO;

import java.util.Map;

public interface ActivityService {
    boolean save(Activity activity);

    PaginationVO<Activity> pageList(Map<String, Object> map);

    boolean delete(String[] ids);

    Map<String, Object> getUserListAndActivity(String id);

    boolean update(Activity activity);
}
