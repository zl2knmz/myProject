package com.cloud.meiju.enumcache;

/**
 * @author zl
 * @date 2023/6/19 16:06
 */
public class Test2 {
    enum OrderType {
        _00("00", "00"),
        _01("01", "01"),
        _02("02", "02"),
        _03("03", "03"),
        _04("04", "04"),
        _05("05", "05"),
        _06("06", "06"),
        _07("07", "07"),
        _08("08", "08"),
        _09("09", "09"),
        _10("10", "10");
        private String code;
        private String desc;

        OrderType(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        static {
            EnumCache.registerByValue(OrderType.class, OrderType.values(), OrderType::getCode);
        }

        public static OrderType getEnumByCode(String code, OrderType def) {
            OrderType[] values = OrderType.values();
            for (OrderType value : values) {
                if (value.getCode().equals(code)) {
                    return value;
                }
            }
            return def;
        }
    }

    private static final OrderType DEF = OrderType._00;
    private static final int TIMES = 10000000;

    static void compare(String code) {
        long s = System.currentTimeMillis();
        for (int idx = 0; idx < TIMES; idx++) {
            OrderType.getEnumByCode(code, DEF);
        }
        long t = System.currentTimeMillis() - s;
        System.out.println(String.format("枚举->%s : %s", code, t));

        s = System.currentTimeMillis();
        for (int idx = 0; idx < TIMES; idx++) {
            EnumCache.findByValue(OrderType.class, code, DEF);
        }
        t = System.currentTimeMillis() - s;
        System.out.println(String.format("缓存->%s : %s", code, t));
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        for (int idx = 0; idx < 2; idx++) {
            compare("NotExist");
            for (OrderType value : OrderType.values()) {
                compare(value.getCode());
            }
            System.out.println("=================");
        }
    }

}
