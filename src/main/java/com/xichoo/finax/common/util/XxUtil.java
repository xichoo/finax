package com.xichoo.finax.common.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author xichoo@live.cn
 */
@Slf4j
public class XxUtil {

    public static String getIpAddr(HttpServletRequest request) {
        String ip = "-";

        ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if ("127.0.0.1".equals(ip)) {
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    log.error("获取登陆用户IP地址出错：{}", e.getMessage());
                }
                ip = inet.getHostAddress();
            }
        }
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    public static String getOSInfo(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent" );
        String os = "";
        if (userAgent.toLowerCase().contains("windows" )) {
            os = "Windows";
        } else if (userAgent.toLowerCase().contains("mac" )) {
            os = "Mac";
        } else if (userAgent.toLowerCase().contains("x11" )) {
            os = "Unix";
        } else if (userAgent.toLowerCase().contains("android" )) {
            os = "Android";
        } else if (userAgent.toLowerCase().contains("iphone" )) {
            os = "IPhone";
        } else {
            os = "UnKnown, More-Info: " + userAgent;
        }

        return os;
    }

    public static String getBroswerInfo(HttpServletRequest request){
        String userAgent = request.getHeader("User-Agent" );
        String user = userAgent.toLowerCase();
        String browser = "";
        if (user.contains("edge" )) {
            browser = (userAgent.substring(userAgent.indexOf("Edge" )).split(" " )[0]).replace("/" , "-" );
        } else if (user.contains("msie" )) {
            String substring = userAgent.substring(userAgent.indexOf("MSIE" )).split(";" )[0];
            browser = substring.split(" " )[0].replace("MSIE" , "IE" ) + "-" + substring.split(" " )[1];
        } else if (user.contains("safari" ) && user.contains("version" )) {
            browser = (userAgent.substring(userAgent.indexOf("Safari" )).split(" " )[0]).split("/" )[0]
                    + "-" + (userAgent.substring(userAgent.indexOf("Version" )).split(" " )[0]).split("/" )[1];
        } else if (user.contains("opr" ) || user.contains("opera" )) {
            if (user.contains("opera" )) {
                browser = (userAgent.substring(userAgent.indexOf("Opera" )).split(" " )[0]).split("/" )[0]
                        + "-" + (userAgent.substring(userAgent.indexOf("Version" )).split(" " )[0]).split("/" )[1];
            } else if (user.contains("opr" )) {
                browser = ((userAgent.substring(userAgent.indexOf("OPR" )).split(" " )[0]).replace("/" , "-" ))
                        .replace("OPR" , "Opera" );
            }
        } else if (user.contains("chrome" )) {
            browser = (userAgent.substring(userAgent.indexOf("Chrome" )).split(" " )[0]).replace("/" , "-" );
        } else if ((user.contains("mozilla/7.0" )) || (user.contains("netscape6" )) ||
                (user.contains("mozilla/4.7" )) || (user.contains("mozilla/4.78" )) ||
                (user.contains("mozilla/4.08" )) || (user.contains("mozilla/3" ))) {
            browser = "Netscape-?";
        } else if (user.contains("firefox" )) {
            browser = (userAgent.substring(userAgent.indexOf("Firefox" )).split(" " )[0]).replace("/" , "-" );
        } else if (user.contains("rv" )) {
            String IEVersion = (userAgent.substring(userAgent.indexOf("rv" )).split(" " )[0]).replace("rv:" , "-" );
            browser = "IE" + IEVersion.substring(0, IEVersion.length() - 1);
        } else {
            browser = "UnKnown, More-Info: " + userAgent;
        }
        return browser;
    }

}

