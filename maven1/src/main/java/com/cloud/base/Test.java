package com.cloud.base;

/**
 * @author zl
 * @date 2022/4/25 23:30
 */
public class Test {

    class Plate<T>{
        private T item;
        public Plate(T t){item=t;}
        public void set(T t){item=t;}
        public T get(){return item;}
    }

    public  void main(String[] args) {

//        Plate<Fruit> p=new Plate<Apple>(new Apple());

        Plate<? extends Fruit> p1=new Plate<Apple>(new Apple());
    }
}
