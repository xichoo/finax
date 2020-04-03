package com.xichoo.finax.modules.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.util.Strings;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器基础方法
 * @author xichoo@live.cn
 */
public class BaseController {

    /**
     * PageHelper分页处理
     */
    public void startPage(HttpServletRequest request){
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
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


}
