package com.cloud.design.agent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 *
 * @author zl
 * @date 2022/10/23 16:42
 */
public class ProxyFactory {

    public static Object getAgent(Service target, Aop aop) {
        // 返回生成的动态代理对象
        return Proxy.newProxyInstance(
                // 类加载器
                target.getClass().getClassLoader(),
                // 目标对象实现的所有接口
                target.getClass().getInterfaces(),
                // 代理功能实现
                new InvocationHandler() {
                    /**
                     * @param proxy 生成的代理对象
                     * @param method 正在被调用的目标方法buy(),show()
                     * @param args 目标方法的参数
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object obj = null;
                        try {
                            // 切面
                            aop.before();
                            // 业务
                            obj = method.invoke(target, args);
                            // 切面
                            aop.after();
                        } catch (Exception e) {
                            // 切面
                            aop.exception();
                        }
                        return obj;
                    }
                }
        );
    }

}
