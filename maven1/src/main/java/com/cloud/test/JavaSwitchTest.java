package com.cloud.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author zl
 * @date 2020/5/15 13:52
 */
public class JavaSwitchTest {
    @Test
    public void switchTest() {
        String pathType = "10";
        String typePath = null;
        if (StringUtils.isBlank(pathType)) {
            typePath = "common/";
        } else {
            switch (pathType) {
                case "1":
                    typePath = "blog/";
                    break;
                case "2":
                    typePath = "brand/";
                    break;
                case "3":
                    typePath = "product/";
                    break;
                case "4":
                    typePath = "subject/";
                    break;
                case "5":
                    typePath = "cate/";
                    break;
                case "6":
                    typePath = "topic/";
                    break;
                case "7":
                    typePath = "rec/";
                    break;
                case "8":
                    typePath = "user/";
                    break;
                default:
                    typePath = "other/";
                    break;
            }
        }
        System.out.println("pathType=" + pathType + ", typePath=" + typePath);
    }
}
