package com.cloud.design.createtype;

/**
 * 1. 单例模式 (Singleton Pattern)
 * 使用场景: 当你需要确保一个类只有一个实例，并提供一个全局访问点时使用。例如，配置文件读取器、数据库连接池等。
 *
 * @author zl
 * @date 2025/1/15 18:27
 */
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
