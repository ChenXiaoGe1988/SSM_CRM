package com.ckb.ssm.controller;

import com.ckb.ssm.entity.User;
import com.ckb.ssm.service.UserService;
import com.ckb.ssm.util.MD5Util;
import com.ckb.ssm.util.PrintJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ychen
 * 用户控制器
 */
@Controller
@RequestMapping(value = "/settings/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param user
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/login")
    public void Login(@RequestParam(value = "loginAct") String loginAct,
                      @RequestParam(value = "loginPwd") String loginPwd,
                        HttpServletRequest request, HttpServletResponse response) {
        //登录密码md5加密
        loginPwd = MD5Util.getMD5(loginPwd);
        //接收浏览器ip地址
        String ip = request.getRemoteAddr();
        try {
            User user = userService.login(loginAct, loginPwd, ip);
            request.getSession().setAttribute("user", user);
            PrintJson.printJsonFlag(response, true);
        } catch (Exception e) {
            e.printStackTrace();
            String msg = e.getMessage();
            //(1)打包成map,将map解析为json串
            //(2)创建一个Vo(大量使用信息的情况下)
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", false);
            map.put("msg", msg);
            PrintJson.printJsonObj(response, map);
        }
    }


    /**
     * 修改密码
     * @param user
     * @param response
     * @return
     * @throws Exception
     *//*
    @RequestMapping("/modifyPassword")
    public String modifyPassword(User user, HttpServletResponse response) throws Exception {
        String MD5pwd = MD5Util.MD5Encode(user.getPassword(), "UTF-8");
        user.setPassword(MD5pwd);
        int resultTotal = userService.updateUser(user);
        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        LOGGER.info("request: user/modifyPassword , user: " + user.toString());
        ResponseUtil.write(response, result);
        return null;
    }

    *//**
     * 退出系统
     * @param session
     * @return
     * @throws Exception
     *//*
    @RequestMapping("/logout")
    public String logout(HttpSession session) throws Exception {
        session.invalidate();
        LOGGER.info("request: user/logout");
        return "redirect:/login.jsp";
    }

    *//**
     * 获取用户列表
     * @param page
     * @param rows
     * @param s_user
     * @param response
     * @return
     * @throws Exception
     *//*
    @RequestMapping("/list")
    public String list(@RequestParam(value = "page", required = false) String page,
                       @RequestParam(value = "rows", required = false) String rows,
                       User s_user,
                       HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (page != null && rows != null) {
            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
            map.put("start", pageBean.getStart());
            map.put("size", pageBean.getPageSize());
        }
        map.put("userName", StringUtil.formatLike(s_user.getUserName()));
        List<User> userList = userService.findUser(map);
        Long total = userService.getTotalUser(map);
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(userList);
        result.put("rows", jsonArray);
        result.put("total", total);
        LOGGER.info("request: user/list , map: " + map.toString());
        ResponseUtil.write(response, result);
        return null;
    }


    *//**
     * 添加或者修改管理员
     * @param user
     * @param response
     * @return
     *//*
    @RequestMapping("/save")
    public String save(User user, HttpServletResponse response) throws Exception {
        int resultTotal = 0;
        String MD5pwd = MD5Util.MD5Encode(user.getPassword(), "UTF-8");
        user.setPassword(MD5pwd);
        if (user.getId() == null) {
            resultTotal = userService.addUser(user);
        } else {
            resultTotal = userService.updateUser(user);
        }
        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        LOGGER.info("request: user/save , user: " + user.toString());
        ResponseUtil.write(response, result);
        return null;
    }

    *//**
     * 删除管理员
     * @param ids
     * @param response
     * @return
     *//*
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "ids") String ids, HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            userService.deleteUser(Integer.parseInt(idsStr[i]));
        }
        result.put("success", true);
        LOGGER.info("request: user/delete , ids: " + ids);
        ResponseUtil.write(response, result);
        return null;
    }*/
}
