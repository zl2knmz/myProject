package com.cloud.meiju.demo1;

/**
 * 枚举类 不能创建对象 不能继承 不能实现
 * @author zl
 * @date 2022/5/3 23:25
 */
public enum EnumTest {
    // 放在第一行
    ONE("1") {
        @Override
        public String getSay() {
            super.name = "change-one";
            return name;
        }
    },
    TWO("2") {
        @Override
        public String getSay() {
//            super.name = "change-two";
            return name;
        }
    };

    private EnumTest(String s){
        name = s;
    }

    public String name = "hello";

    public String say(){
        return name;
    }

    public abstract String getSay();

}
