package com.xichoo.finax.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Xss过滤包装类
 */
@Slf4j
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper{

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        String strHeader = super.getHeader(name);
        if(Strings.isEmpty(strHeader)){
            return strHeader;

        }
        return Jsoup.clean(super.getHeader(name), Whitelist.relaxed());
    }

    @Override
    public String getParameter(String name) {
        String strParameter = super.getParameter(name);
        if(Strings.isEmpty(strParameter)){
            return strParameter;
        }
        return Jsoup.clean(super.getParameter(name),Whitelist.relaxed());
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if(values==null){
            return values;
        }
        int length = values.length;
        String[] escapseValues = new String[length];
        for(int i = 0;i<length;i++){
            escapseValues[i] = Jsoup.clean(values[i], Whitelist.relaxed());
        }
        return escapseValues;
    }

}
