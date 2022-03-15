package com.mengby.io;

import org.junit.jupiter.api.Test;

import java.io.InputStream;

/**
 * @author bingye
 * @Date 2022/3/15
 */
public class ResourcesTest {

    @Test
    public void main() {
        final InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
    }
}
