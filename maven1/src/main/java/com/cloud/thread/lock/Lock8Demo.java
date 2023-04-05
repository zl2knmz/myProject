package com.cloud.thread.lock;

import java.util.concurrent.TimeUnit;


/**
 * 8锁案例说明：
 * 1、标准访问有ab两个线程，请问先打印邮件还是短信
 * 2、sendEmail方法中加入暂停3秒钟，请问先打印邮件还是短信
 * 3、添加一个普通的hello方法，请问先打印邮件还是hello
 * 4、有两部手机，请问先打印邮件还是短信
 * 5、有两个静态同步方法，有1部手机，请问先打印邮件还是短信
 * 6、有两个静态同步方法，有2部手机，请问先打印邮件还是短信
 * 7、有1个静态同步方法，有1个普通同步方法，有1部手机，请问先打印邮件还是短信
 * 8、有1个静态同步方法，有1个普通同步方法，有2部手机，请问先打印邮件还是短信
 *
 * @author zl
 * @date 2023/4/5 18:40
 */
public class Lock8Demo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2= new Phone();
        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();

        // 暂停毫秒，保证a线程先启动
        try {
            TimeUnit.MILLISECONDS.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
//            phone.sendSms();
//            phone.hello();
            phone2.sendSms();
        }, "b").start();
    }
}

/**
 * 资源类
 */
class Phone {
    public static synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-------sendEmail");
    }

    public synchronized void sendSms() {
        System.out.println("-------sendSms");
    }

    public void hello() {
        System.out.println("-------hello");
    }
}