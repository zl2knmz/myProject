package com.cloud.regex;

import red.zyc.desensitization.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zl
 * @date 2023/8/30 17:21
 */
public class Child {

    @ChineseNameSensitive
    private String name = "李富贵";

    @IdCardNumberSensitive
    private String idCardNumber = "321181199301096000";

    @UsccSensitive
    private String unifiedSocialCreditCode = "91310106575855456U";

    @CharSequenceSensitive
    private String string = "123456";

    @EmailSensitive
    private String email = "123456@qq.com";

    @PasswordSensitive
    private String password = "123456";

    @CascadeSensitive
    private Mother mother = new Mother();

    @CascadeSensitive
    private Father father = new Father();

    private @PasswordSensitive
    String[] passwords = {"123456", "1234567", "12345678"};

    private List<@CascadeSensitive Parent> parents1 = Stream.of(new Father(), new Mother()).collect(Collectors.toList());

    private List<@EmailSensitive String> emails1 = Stream.of("123456@qq.com", "1234567@qq.com", "1234568@qq.com").collect(Collectors.toList());

    private Map<@ChineseNameSensitive String, @EmailSensitive String> emails2 = Stream.of("张三", "李四", "小明").collect(Collectors.toMap(s -> s, s -> "123456@qq.com"));

}
