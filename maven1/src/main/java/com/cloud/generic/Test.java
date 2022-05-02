package com.cloud.generic;

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

//    class Generic<T extend Fruit>{
//        T data;
//        public void getFruitName(T t){
//            t.getName();
//        }
//        public void setData(T t){
//            data = t;
//        }
//        public T getData(){
//            return data;
//        }
//    }
//    public void setGeneric(Generic<? extends Fruit> generic){
//
//    }

    public  void main(String[] args) {

//        Plate<Fruit> p=new Plate<Apple>(new Apple());

        Plate<? extends Fruit> p1 = new Plate<>(new Apple());
    }
}
