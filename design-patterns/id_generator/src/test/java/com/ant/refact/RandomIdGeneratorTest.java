package com.ant.refact;

import com.ant.refact.RandomIdGenerator;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/3/14 5:40 下午
 */
public class RandomIdGeneratorTest {
    @Test
    public void testGetLastSubstrSplittedByDot() {
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String actualSubstr = idGenerator.getLastSubstrSplittedByDot("field1.field2.field3");
        Assert.assertEquals("field3", actualSubstr);
        actualSubstr = idGenerator.getLastSubstrSplittedByDot("field1");
        Assert.assertEquals("field1", actualSubstr);
        actualSubstr = idGenerator.getLastSubstrSplittedByDot("field1#field2$field3");
        Assert.assertEquals("field1#field2#field3", actualSubstr);
    }
}
