package com.knmz.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


/**
 * @author zl
 * @date 2019/9/24 16:56
 * 汉字转化为拼音的工具类
 */
public class PinyinTool {
    private static HanyuPinyinOutputFormat format = null;

    static {
        format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    /**
     * 汉字转换成拼音的类型
     */
    public enum Type {
        // 全部大写
        UPPERCASE,
        // 全部小写
        LOWERCASE,
        // 首字母大写
        FIRSTUPPER
    }

    /**
     * 将str转换成拼音，如果不是汉字或者没有对应的拼音，则不作转换
     * 如： 明天 转换成 MINGTIAN
     *
     * @param str：要转化的汉字
     * @param separator：转化结果的分割符，如：分隔符- 转换结果 MING-TIAN
     * @param type 转换成拼音的格式（全部大写、全部小写、首字母大写）
     */
    public static String toPinYin(String str, String separator, Type type) {
        if (str == null || str.trim().length() == 0) {
            return "";
        }
        if (type == Type.UPPERCASE) {
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        } else {
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        }

        StringBuilder pinYin = new StringBuilder();
        String temp = "";
        String[] t = null;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((int) c <= 128) {
                pinYin.append(c);
            } else {
                try {
                    t = PinyinHelper.toHanyuPinyinStringArray(c, format);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }

                if (t == null) {
                    pinYin.append(c);
                } else {
                    temp = t[0];
                    if (type == Type.FIRSTUPPER) {
                        temp = t[0].toUpperCase().charAt(0) + temp.substring(1);
                    }
                    String tempStr = temp + (i == str.length() - 1 ? "" : separator);
                    pinYin.append(tempStr);
                }
            }
        }
        return pinYin.toString().trim();
    }
}