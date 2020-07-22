package com.knmz.util;

public class SecurityHelper {

    public static String doDecrypt(String key, String salt, String encryptText) {
        Encryption encryption = Encryption.getDefault("kuaidi", "Z5gYs3", new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        return encryption.decryptOrNull(encryptText);
    }


}
