package com.knmz.util;

import com.google.common.base.Throwables;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

/**
 * Created by reese on 2020/7/23.
 */
public class AESUtil {
    private static final String CHARSET_NAME = "UTF-8";
    private static final String AES_NAME = "AES";
    public static final String ALGORITHM = "AES/CBC/PKCS7Padding";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 加密
     *
     * @param content
     * @param key
     * @return
     */
    public static String encrypt(String content, String key) {
        byte[] result = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);

            int base = 16;
            byte[] keyBytes=key.getBytes(CHARSET_NAME);
           /* if (keyBytes.length % base != 0) {
                int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
                keyBytes = temp;
            }*/

            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, AES_NAME);
//            AlgorithmParameterSpec paramSpec = new IvParameterSpec(new byte[16]);
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(new byte[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);
            result = cipher.doFinal(content.getBytes(CHARSET_NAME));
        } catch (Exception ex) {
            Throwables.propagate(ex);
        }
        return Base64.encodeBase64String(result);
    }

    /**
     * 解密
     *
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(String content, String key) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(CHARSET_NAME), AES_NAME);
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(new byte[16]);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);
            return new String(cipher.doFinal(Base64.decodeBase64(content)), CHARSET_NAME);
        } catch (Exception ex) {
            Throwables.propagate(ex);
        }
        return StringUtils.EMPTY;
    }

    public static void main(String[] args) throws Exception {
        String testStr = "{\"allow_activities\":[\"y7dnx05o\",\"von11j17\",\"lmz6bp27\",\"vmjvnz2m\"],\"avatar\":\"https://glevent.oss-cn-shenzhen.aliyuncs.com/prdres/guest/2020/11265871966568448/touxiang20.jpg\",\"expire\":null,\"name\":\"系统账号\",\"third_party_id\":\"100000000\",\"user_id\":null}";
        System.out.println(AESUtil.encrypt(testStr,"KaHRBlWhbh%brhT$"));
    }
}
