package com.cloud.thread.lock;

import java.util.concurrent.TimeUnit;


/**
 * 8锁案例说明：
 * 1、标准访问有a、b两个线程，请问先打印邮件还是短信 （sendEmail  sendSms）
 * 2、sendEmail方法中加入暂停3秒钟，请问先打印邮件还是短信 （sendEmail  sendSms）
 *
 * 3、添加一个普通的hello方法，请问先打印邮件还是hello （hello sendEmail）
 * 4、有两部手机，请问先打印邮件还是短信 （sendSms sendEmail）
 *
 * 5、有两个静态同步方法，有1部手机，请问先打印邮件还是短信 （sendEmail  sendSms）
 * 6、有两个静态同步方法，有2部手机，请问先打印邮件还是短信 （sendEmail  sendSms）
 *
 * 7、有1个静态同步方法，有1个普通同步方法，有1部手机，请问先打印邮件还是短信 （sendSms sendEmail）
 * 8、有1个静态同步方法，有1个普通同步方法，有2部手机，请问先打印邮件还是短信 （sendSms sendEmail）
 * <p>
 *
 * 笔记总结：
 * 1-2
 *   一个对象里面如果有多个 synchronized方法，某一个时刻内，只要一个线程去调用其中的一个 synchronized方法了
 *   其他的线程都只能等待，换句话说，某一个时刻内，只能有唯一的一个线程去访问这些 synchronized方法
 *   锁的是当前对象this，被锁定后，其他的线程都不能进入到当前对象的其他的 synchronized方法
 *
 * 3-4
 *   加个普通方法后发现和同步锁无关
 *   换成两个对象后，不是同一把锁了，情况立刻变换。
 *
 * 5-6 都换成静态同步方法后，情况又变化
 *  三种 synchronized 锁的内容有一些差别：
 *     对于普通同步方法，锁的是当前实例对象，通常指this，具体的一部手机，所有的普通同步方法用的都是同一把锁 ——> 实例对象本身
 *     对于静态同步方法，锁的是当前类的Class对象，如 Phone.class唯一的一个模板
 *     对于同步方法块，锁的是 synchronized 括号内的对象
 *
 * 7-8
 *   当一个线程试图访问同步代码块时它首先必须得到锁，正常退出或抛出异常时必须释放锁。
 *
 *   所有的普通同步方法用的都是同一把锁——实例对象本身，就是new出来的具体实例对象本身，本类this
 *   也就是说如果一个实例对象的普通同步方法获取锁后，该实例对象的其他普通同步方法必须等待获取锁的方法释放后才能获取锁。
 *
 *   所有的静态同步方法用的也是同一把锁——类对象本身，就是我们说过的唯一模板Class
 *   具体实例对象this和唯一模板Class，这两把锁是两个不同的对象，所以静态同步方法与普通同步方法之间是不会有竞态条件的
 *   但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁。
 *
 * @author zl
 * @date 2023/4/5 18:40
 */
public class Lock8Demo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

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
 * synchronized方法 1、对象锁: 作用于实例方法，当前实例加锁，进入同步代码前要获得当前对象实例的锁
 * public synchronized void sendEmail() {}
 *
 * synchronized代码块：2、对括号里配置的对象加锁
 * synchronized (this) {}
 *
 * static synchronized方法 3、类锁：作用于静态方法，当前类加锁，进去同步代码前要获得当前类对象的锁
 * public static synchronized void sendEmail() {}
 */
class Phone {
    public static synchronized void sendEmail() {
        // 锁括号里的对象
//        synchronized (Phone.class) {}
        try { TimeUnit.SECONDS.sleep(3L); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("-------sendEmail");
    }

    public synchronized void sendSms() {
//        synchronized (this) {}
        System.out.println("-------sendSms");
    }

    public void hello() {
        System.out.println("-------hello");
    }
}