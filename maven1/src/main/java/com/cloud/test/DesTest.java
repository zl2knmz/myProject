package com.cloud.test;

import com.cloud.util.DesUtils;
import org.junit.Test;

/**
 * @author zl
 * @date 2020/4/1 15:37
 */
public class DesTest {
    /**
     * 加密解密的密钥
     */
    private final String password = "Hdx.20201024";

    /**
     * DES加密
     */
    @Test
    public void testDesEncode() {
        String phoneNum = "18770913915";
        String enPhoneNum = DesUtils.encrypt(password, phoneNum);
        System.out.println(enPhoneNum);

        String email = "837870929@qq.com";
        String enEmail = DesUtils.encrypt(password, email);
        System.out.println(enEmail);
    }

    /**
     * DES解密
     */
    @Test
    public void testDesDecode() {
        String enPhoneNum = "1LNelk/5o/Kkg3s559F2ig==";
        String dePhoneNum = DesUtils.decrypt(password, enPhoneNum);
        System.out.println(dePhoneNum);

        String enEmail = "zD+C4Ycgcpxlo+KShDVwAj0PUNMG4oJa";
        String deEmail = DesUtils.decrypt(password, enEmail);
        System.out.println(deEmail);
    }
}
