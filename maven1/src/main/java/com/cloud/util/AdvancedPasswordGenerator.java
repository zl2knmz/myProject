package com.cloud.util;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 生成12位复杂密码实现方案
 *
 * @author zl
 * @date 2025/7/15 14:54
 */
public class AdvancedPasswordGenerator {
    public enum CharType {
        LOWERCASE("abcdefghijklmnopqrstuvwxyz"),
        UPPERCASE("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
        DIGITS("0123456789"),
        SPECIALS("@#*");

        private final String chars;

        CharType(String chars) {
            this.chars = chars;
        }

        public String getChars() {
            return chars;
        }
    }

    public static String generatePassword(int length, CharType... types) {
        if (length < types.length) {
            throw new IllegalArgumentException("密码长度不能小于字符类型数量");
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        // 确保每种类型至少一个字符
        for (CharType type : types) {
            String chars = type.getChars();
            password.append(chars.charAt(random.nextInt(chars.length())));
        }

        // 合并所有可用字符
        String allChars = Arrays.stream(types)
                .map(CharType::getChars)
                .collect(Collectors.joining());

        // 填充剩余字符
        while (password.length() < length) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        // 打乱顺序
        char[] array = password.toString().toCharArray();
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        return new String(array);
    }

    public static boolean isStrong(String password, int length) {
        if (password == null || password.length() != length) {
            return false;
        }

        boolean hasLower = false, hasUpper = false,
                hasDigit = false, hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if ("@#*".indexOf(c) >= 0) hasSpecial = true;
        }

        return hasLower && hasUpper && hasDigit && hasSpecial;
    }

    /**
     * 使用示例
     */
    public static void main(String[] args) {
        int length = 16;
        String password = generatePassword(length,
                CharType.LOWERCASE,
                CharType.UPPERCASE,
                CharType.DIGITS,
                CharType.SPECIALS);
        System.out.println("生成密码: " + password);
        System.out.println("密码强度校验: " + isStrong(password,  length));
    }

}
