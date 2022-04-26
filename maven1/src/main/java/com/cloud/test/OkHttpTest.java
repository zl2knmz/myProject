package com.cloud.test;

import com.alibaba.fastjson.JSONObject;
import com.cloud.util.OKHttpAdapter;
import org.junit.Test;

/**
 * @author zl
 * @date 2020/9/4 10:58
 */
public class OkHttpTest {
    /**
     * 测试post
     *
     * @author zl
     */
    @Test
    public void doPostJsonTest() {
        String accessToken = "565467546747";
        String accessTokenUrl = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=ACCESS_TOKEN";
        accessTokenUrl = accessTokenUrl.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("scene","a=1");
        String result = new OKHttpAdapter().doPostJson(accessTokenUrl, jsonObject.toJSONString());
        System.out.println("result：" + result);
    }
}
