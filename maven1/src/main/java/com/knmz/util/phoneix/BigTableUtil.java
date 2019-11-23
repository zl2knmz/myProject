package com.knmz.util.phoneix;

/**
 * @author zl
 * @date 2019/6/24 11:00
 * 宽表工具类
 */
public class BigTableUtil {

    /**
     * 报名来源id -> 报名来源名
     */
    public static String regSrcToName(int regSrc) {
        String name = null;
        switch (regSrc) {
            case 0:
                name = "WEB";
                break;
            case 1:
                name = "手机报名";
                break;
            case 2:
                name = "嵌入报名";
                break;
            case 3:
                name = "微信服务号报名";
                break;
            case 4:
                name = "API导入票";
                break;
            case 5:
                name = "APP报名";
                break;
            case 6:
                name = "微信浏览器报名";
                break;
            case 7:
                name = "微信小程序报名";
                break;
            default:
                break;
        }
        return name;
    }

    /**
     * 性别id -> 性别名
     */
    public static String genderToName(int gender) {
        String name = null;
        switch (gender) {
            case 0:
                name = "保密";
                break;
            case 1:
                name = "男";
                break;
            case 2:
                name = "女";
                break;
            default:
                break;
        }
        return name;
    }
}
