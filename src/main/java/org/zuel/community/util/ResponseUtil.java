package org.zuel.community.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseUtil {
    /**
     * 返回Map，
     * errno：返回码
     * errmsg：返回信息
     * @return
     */
    public static Object ok(){
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", 0);
        obj.put("errmsg", "成功");
        return obj;
    }
    /**
     * 返回Map
     * errno:返回码
     * errmsg:返回信息
     * data:返回data
     * @param data
     * @return
     */
    public static Object ok(Object data){
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", 0);
        obj.put("errmsg", "成功");
        obj.put("data", data);
        return obj;
    }
    /**
     * data里面有total和list属性两个
     * @param total
     * @param list
     * @return
     */
    public static Object ok(long total, List list){
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("items", list);
        return ok(data);
    }
    /**
     *
     * @param errmsg
     * @param data
     * @return
     */
    public static Object ok(String errmsg, Object data){
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", 0);
        obj.put("errmsg", errmsg);
        obj.put("data", data);
        return obj;
    }
    /**
     * errno:返回码，-1
     * errmsg:返回信息，错误
     * @return
     */
    public static Object fail(){
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", -1);
        obj.put("errmsg", "错误");
        return obj;
    }
    /**
     * errno:返回码，errno
     * errmsg:返回信息，errmsg
     * @param errno
     * @param errmsg
     * @return
     */
    public static Object fail(int errno, String errmsg){
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", errno);
        obj.put("errmsg", errmsg);
        return obj;
    }
    /**
     * errno:返回码
     * erromsg:返回信息
     * @param errno
     * @param errmsg
     * @return
     */
    public static Object fail(String errno, String errmsg){
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", errno);
        obj.put("errmsg", errmsg);
        return obj;
    }

    public static Object unlogin(){ return fail(501,"请先登陆");}

    public static Object userNameOrPasswordIsWrong(){return fail(502, "用户名或密码错误");}

    public static Object userNameIsExist(){return fail(503, "用户名已存在");}

    public static Object badArgument(){return fail(502,"系统内部错误");}


}