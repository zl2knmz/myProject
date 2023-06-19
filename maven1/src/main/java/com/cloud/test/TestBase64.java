package com.cloud.test;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author zl
 * @date 2023/6/13 16:47
 */
public class TestBase64 {

    private static final String ALGORITHM = "AES";
    private static final String KEY = "mysecretkey12345";

    public static String encrypt(String value) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptedBytes = cipher.doFinal(value.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedValue) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedValue);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }


    public static String encrypt1(String data) {
        // 加密
        StringBuilder replacedStr = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            switch (c) {
                case '0':
                    replacedStr.append('a');
                    break;
                case '1':
                    replacedStr.append('b');
                    break;
                case '2':
                    replacedStr.append('c');
                    break;
                case '3':
                    replacedStr.append('d');
                    break;
                case '4':
                    replacedStr.append('e');
                    break;
                case '5':
                    replacedStr.append('f');
                    break;
                case '6':
                    replacedStr.append('g');
                    break;
                case '7':
                    replacedStr.append('h');
                    break;
                case '8':
                    replacedStr.append('i');
                    break;
                case '9':
                    replacedStr.append('j');
                    break;
                default:
                    break;
            }
        }
        return replacedStr.toString();
    }

    public static String decrypt1(String data) {
        // 解密
        StringBuilder replacedStr = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            switch (c) {
                case 'a':
                    replacedStr.append('0');
                    break;
                case 'b':
                    replacedStr.append('1');
                    break;
                case 'c':
                    replacedStr.append('2');
                    break;
                case 'd':
                    replacedStr.append('3');
                    break;
                case 'e':
                    replacedStr.append('4');
                    break;
                case 'f':
                    replacedStr.append('5');
                    break;
                case 'g':
                    replacedStr.append('6');
                    break;
                case 'h':
                    replacedStr.append('7');
                    break;
                case 'i':
                    replacedStr.append('8');
                    break;
                case 'j':
                    replacedStr.append('9');
                    break;
                default:
                    break;
            }
        }
        return replacedStr.toString();
    }

    public static void main1(String[] args) throws UnsupportedEncodingException {
        // 原始数据
//        String str = "720307050929797";
        String str = "700263849718256";
        System.out.println(str);
        String encrypt = encrypt1(str);
        System.out.println(encrypt);

        String decrypt = decrypt1(encrypt);
        System.out.println(decrypt);


        System.out.println(decrypt1("hcadahafcjhjh"));


    }

    public static void main(String[] args) throws Exception {
        String originalValue = "7002638497182";
        String encryptedValue = encrypt(originalValue);
        System.out.println("Encrypted value: " + encryptedValue);
        String decryptedValue = decrypt(encryptedValue);
        System.out.println("Decrypted value: " + decryptedValue);
    }
}
