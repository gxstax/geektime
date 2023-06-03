package com.ant.service;

import com.ant.TestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 测试用例
 * </p>
 *
 * @author Ant
 * @since 2020/3/10 9:01 上午
 */
public class AntServiceTest extends IServiceTest{

    @Autowired
    private TestService testService;

    @Test
    public void test() {
        testService.test();
    }
}
