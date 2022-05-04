package com.cloud.meiju;

/**
 * @author zl
 * @date 2022/5/4 14:19
 */
public class SeasonTest1 {
    public static void main(String[] args) {
        Season1 summer = Season1.SUMMER;
        // toString()
        System.out.println(summer.toString());

//        System.out.println(Season1.class.getSuperclass());
        System.out.println("-----------------------");

        // values()
        Season1[] values = Season1.values();

        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
            values[i].show();
        }
        System.out.println("-----------------------");

        Thread.State[] values1 = Thread.State.values();
        for (int i = 0; i < values1.length; i++) {
            System.out.println(values1[i]);
        }
        System.out.println("-----------------------");

        // valueOf(String objName): 返回枚举类中对象名是objName的对象
        Season1 winter = Season1.valueOf("WINTER");
        // 如果没有objName的枚举类对象，则抛异常：IllegalArgumentException
//        Season1 winter = Season1.valueOf("WINTER1");
        System.out.println(winter);
        winter.show();

    }
}

interface Info{
    void show();
}

/**
 * 使用enum关键字定义枚举类
 */
enum Season1 implements Info {
    /**
     * 1.提供当前枚举类的对象，多个对象之间用","隔开，末尾对象";"结束
     */
    SPRING("春天", "春暖花开"){
        @Override
        public void show() {
            System.out.println("春天在哪里？");
        }
    },
    SUMMER("夏天", "夏日炎炎"){
        @Override
        public void show() {
            System.out.println("宁夏");
        }
    },
    AUTUMN("秋天", "秋高气爽"){
        @Override
        public void show() {
            System.out.println("秋天不回来");
        }
    },
    WINTER("冬天", "冬去春来"){
        @Override
        public void show() {
            System.out.println("冬天的一把火");
        }
    };


    /**
     * 声明Season对象属性
     */
    private final String seasonName;
    private final String seasonDesc;

    /**
     * 2、私有化类的构造器，并给对象属性赋值
     */
    private Season1(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }


    /**
     * 4、其他诉求：获取枚举类对象的属性
     */
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    /**
     * 4、其他诉求1：提供toString()
     */
//    @Override
//    public String toString() {
//        return "Season1{" +
//                "seasonName='" + seasonName + '\'' +
//                ", seasonDesc='" + seasonDesc + '\'' +
//                '}';
//    }


//    @Override
//    public void show() {
//        System.out.println("这是一个季节");
//    }


}
