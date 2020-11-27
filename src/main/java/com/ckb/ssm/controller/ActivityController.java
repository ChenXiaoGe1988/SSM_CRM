package com.ckb.ssm.controller;

import com.ckb.ssm.common.api.CommonResult;
import com.ckb.ssm.entity.Activity;
import com.ckb.ssm.entity.User;
import com.ckb.ssm.service.ActivityService;
import com.ckb.ssm.service.UserService;
import com.ckb.ssm.util.DateTimeUtil;
import com.ckb.ssm.util.PrintJson;
import com.ckb.ssm.util.UUIDUtil;
import com.ckb.ssm.vo.PaginationVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ychen
 * 市场活动控制器
 */
@Controller
@RequestMapping(value = "/workbench/activity")
public class ActivityController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    //private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);

    /**
     * 获取用户列表
     * @param response
     */
    @RequestMapping("/getUserList")
    public void getUserList(HttpServletResponse response) {
        System.out.println("取得用户信息列表");
        List<User> userList = userService.getUserList();
        PrintJson.printJsonObj(response, userList);
    }


    /**
     * 创建市场活动
     * @return
     */
    @RequestMapping("/save")
    public void save(@ModelAttribute("Activity") Activity activity,//使用@ModelAttribute这个方法可以直接将参数映射成pojo对象
                     HttpServletRequest request,
                     HttpServletResponse response) {
        //生成唯一主键id
        activity.setId(UUIDUtil.getUUID());
        activity.setCreateTime(DateTimeUtil.getSysTime());
        //注意session里的内容与登录时设置的currentUser一致
        activity.setCreateBy(((User) request.getSession().getAttribute("user")).getName());
        boolean flag = activityService.save(activity);
        PrintJson.printJsonFlag(response, flag);
    }

    /**
     * 查询市场活动列表(条件查询+分页查询)
     * @param name
     * @param owner
     * @param startDate
     * @param endDate
     * @param pageNo
     * @param pageSize
     * @param response
     */
    @RequestMapping("/pageList")
    public void pageList(@RequestParam(value = "name") String name,
                         @RequestParam(value = "owner") String owner,
                         @RequestParam(value = "startDate") String startDate,
                         @RequestParam(value = "endDate") String endDate,
                         @RequestParam(value = "pageNo") String pageNo,
                         @RequestParam(value = "pageSize") String pageSize,
                         HttpServletResponse response) {
        int pageNo1 = Integer.valueOf(pageNo);
        int pageSize1 = Integer.valueOf(pageSize);
        int skipCount = (pageNo1 - 1) * pageSize1;
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("owner", owner);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("skipCount", skipCount);
        map.put("pageSize", pageSize1);
        //使用VO
        PaginationVO<Activity> vo = activityService.pageList(map);
        PrintJson.printJsonObj(response, vo);
    }


    /**
     * 删除市场活动
     */
    @RequestMapping("/delete")
    public void delete(@RequestParam(value = "id") String[] ids,
                       HttpServletResponse response) {
        boolean flag = activityService.delete(ids);
        PrintJson.printJsonFlag(response, flag);
    }

    /**
     * 查询用户信息列表和根据市场活动id查询单条记录操作
     * @param id
     * @param response
     */
    @RequestMapping("/getUserListAndActivity")
    public void getUserListAndActivity(@RequestParam(value = "id")String id,
                                       HttpServletResponse response) {
        /*
            controller调用service的方法,返回值是什么
            你的想一想前端要什么 , 就要从service层要什么
            前端需要: uList + a
            以上两项复用率不高 , 所以用map打包这两项信息即可
         */
        Map<String, Object> map = activityService.getUserListAndActivity(id);
        PrintJson.printJsonObj(response, map);
    }

    /**
     * 更新市场活动信息
     * @param activity
     * @param request
     * @param response
     */
    @RequestMapping("/update")
    public void update(@ModelAttribute("Activity") Activity activity,
                       HttpServletRequest request,
                       HttpServletResponse response) {
        activity.setEditTime(DateTimeUtil.getSysTime());
        activity.setEditBy(((User)request.getSession().getAttribute("user")).getName());
        boolean flag = activityService.update(activity);
        PrintJson.printJsonFlag(response, flag);
    }

}
