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

    /**
     * String 的 replace 方法测试
     *
     * @author zl
     */
    @Test
    public void replaceTest() {
        String text = "报个名啦！链接1：\n" +
                "https://www.huodongxing.com/event/4538977446421?qd=525746874888486912&utm_source=525746874888486912&utm_medium=2&utm_campaign=HDXPUSH\n" +
                "\n" +
                "报个名啦！链接2：\n" +
                "https://www.huodongxing.com/news/750898249196?qd=525746874888486912&utm_source=525746874888486912&utm_medium=2&utm_campaign=HDXPUSH\n";
        String oldStr = "https://www.huodongxing.com/event/4538977446421?qd=525746874888486912&utm_source=525746874888486912&utm_medium=2&utm_campaign=HDXPUSH";
        String newStr = "http://hdxu.cn/8bvA3";
        if (text.contains(oldStr)) {
            text = text.replace(oldStr, newStr);
            System.out.println("start- " + text);
        }
        System.out.println("end- " + text);
    }

}
