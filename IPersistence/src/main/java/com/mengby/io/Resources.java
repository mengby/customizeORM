package com.mengby.io;

import java.io.InputStream;

/**
 * @author bingye
 * @Date 2022/3/15
 */
public class Resources {

    /**
     * 根据配置文件的路径，将文件加载成字节流存储在内存中
     * @param path 配置文件的路径
     * @return 返回输入流
     */
    public static InputStream getResourceAsSteam(String path) {
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }
}
