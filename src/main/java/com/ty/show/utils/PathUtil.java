package com.ty.show.utils;


import lombok.Data;

import javax.servlet.http.HttpServletRequest;

@Data
public class PathUtil {



    public static String getSystemRealPath(HttpServletRequest request){
        String filePath;
        String active = SpringUtil.getActiveProfile();
        if("dev".equals(active)){
            filePath = request.getContextPath()+"/";
        }else if("prod".equals(active)){
            filePath = System.getProperty("java.class.path");
            filePath = filePath.substring(0,filePath.lastIndexOf("\\")+1)+"\\";
        }else{
            filePath = request.getContextPath();
        }
        return filePath;
    }
}
