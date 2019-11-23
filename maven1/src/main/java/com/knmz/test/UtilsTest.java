package com.knmz.test;

import com.knmz.util.PinyinTool;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * @author zl
 * @date 2019/9/24 17:16
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

    /**
     * 测试sha256加密，SHA256加密后是256位，也就是64个字符。
     * @author zl
     *
     */
    @Test
    public void shaTest() {
        String str = "this is a test string";
        String sha = DigestUtils.sha256Hex(str).toUpperCase();
        System.out.println(sha);
    }

}
