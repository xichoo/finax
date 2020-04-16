package com.xichoo.finax.modules.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器基础方法
 * @author xichoo@live.cn
 */
public class BaseController {

    /**
     * 获取request
     */
    public HttpServletRequest getRequest(){
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     */
    public HttpServletResponse getResponse(){
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    public HttpSession getSession(){
        return getRequest().getSession();
    }

    /**
     * 获取requestAttributes
     */
    public ServletRequestAttributes getRequestAttributes(){
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    /**
     * PageHelper分页处理
     */
    public void startPage(){
        String pageNum = getRequest().getParameter("pageNum");
        String pageSize = getRequest().getParameter("pageSize");
        if(Strings.isNotBlank(pageNum) && Strings.isNotBlank(pageSize)){
            PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        }
    }

    /**
     * 返回Table列表数据
     * @param list
     */
    public Map pageData(List<?> list){
        Map result = new HashMap();
        result.put("rows", list);
        result.put("total", new PageInfo(list).getTotal());
        return result;
    }

    /**
     * 返回json数组
     * @param list
     */
    public JSONArray jsonArray(List<?> list) {
        if(list == null || list.isEmpty()){
            return new JSONArray();
        }
        return JSONArray.parseArray(JSON.toJSONString(list));
    }


}
