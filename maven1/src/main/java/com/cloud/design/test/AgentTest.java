package com.cloud.design.test;

import com.cloud.design.agent.Proxy;
import com.cloud.design.agent.ProxyFactory;
import com.cloud.design.agent.Service;
import com.cloud.design.agent.impl.BookServiceImpl;
import com.cloud.design.agent.impl.LogAop;
import com.cloud.design.agent.impl.ProductServiceImpl;
import com.cloud.design.agent.impl.TransAop;
import org.junit.Test;

/**
 * 测试类
 *
 * @author zl
 * @date 2022/10/23 16:53
 */
public class AgentTest {
    /**
     * 静态代理
     */
    @Test
    public void test01() {
        BookServiceImpl book = new BookServiceImpl();
        LogAop logAop = new LogAop();
        TransAop transAop = new TransAop();
        Proxy proxy = new Proxy(book, transAop);
        // 静态代理，show不能被调用
        proxy.show();

        // proxy 当成service再传入，aop嵌套
        Proxy proxy1 = new Proxy(proxy, logAop);
        proxy1.buy();
    }

    /**
     * 动态代理
     */
    @Test
    public void test02() {
        BookServiceImpl book = new BookServiceImpl();
        ProductServiceImpl product = new ProductServiceImpl();

        LogAop logAop = new LogAop();
        TransAop transAop = new TransAop();

        Service proxyFactory = (Service) ProxyFactory.getAgent(book, logAop);
        // com.sun.proxy.$Proxy4
        System.out.println("proxyFactory type=" + proxyFactory.getClass().getTypeName());
        proxyFactory.buy();
        System.out.println("-------------");
        // 动态代理show方法被调用
        proxyFactory.show();
        System.out.println("-------------");

        Service proxyFactory1 = (Service) ProxyFactory.getAgent(product, transAop);
        proxyFactory1.buy();
        System.out.println("-------------");
        proxyFactory1.show();
    }
}
