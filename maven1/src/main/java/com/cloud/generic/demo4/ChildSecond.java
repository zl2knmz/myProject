package com.cloud.generic.demo4;

/**
 * 泛型类派生子类，子类不是泛型类，父类要明确泛型的数据类型。
 *
 * @author zl
 * @date 2022/5/3 0:51
 */
public class ChildSecond extends Parent<Integer> {
    @Override
    public Integer getValue() {
        return super.getValue();
    }

    @Override
    public void setValue(Integer value) {
        super.setValue(value);
    }
}
