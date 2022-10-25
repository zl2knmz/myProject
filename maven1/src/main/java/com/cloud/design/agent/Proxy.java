package com.cloud.design.agent;

/**
 * 静态代理
 *
 * @author zl
 * @date 2022/10/23 16:20
 */
public class Proxy implements Service {

    private Service service;
    private Aop aop;

    public Proxy(Service service, Aop aop){
        this.service = service;
        this.aop = aop;
    }

    @Override
    public void buy() {
        try {
            aop.before();
            service.buy();
            aop.after();
        } catch (Exception e) {
            aop.exception();
        }
    }
}
