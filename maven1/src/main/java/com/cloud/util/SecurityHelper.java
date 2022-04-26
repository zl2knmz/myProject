package com.cloud.util;

public class SecurityHelper {
    public static String doEncrypt(String key, String salt, String encryptText) {
        Encryption encryption = Encryption.getDefault(key, salt, new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        return encryption.encryptOrNull(encryptText);
    }
}
