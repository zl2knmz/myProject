package com.knmz.test;

import com.knmz.util.PinyinTool;
import org.junit.Test;

/**
 * @Author: zl
 * @Date: 2019/9/24 17:16
 */
public class UtilsTest {

    /**
     * 测试拼音转化结果
     * @author zl
     *
     */
    @Test
    public void pingYinTest() {
        String pingYin = PinyinTool.toPinYin("李白", "-", PinyinTool.Type.UPPERCASE);
        System.out.println("测试拼音转化结果为：" + pingYin);
    }

}
