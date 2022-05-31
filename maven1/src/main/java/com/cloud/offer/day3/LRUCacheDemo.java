package com.cloud.offer.day3;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zl
 * @date 2022/5/31 23:12
 */
public class LRUCacheDemo {
    /**
     * map 负责查找，构建一个虚拟的双向链表，它里面安装的就是一个个Node节点，作为数据载体。
     */
    class Node<K,V>{
        K key;
        V value;
        Node<K,V> prev;
        Node<K,V> next;

        public Node() {
            this.prev = this.next = null;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.prev = this.next = null;
        }
    }

    /**
     * 2 构造一个双向队列，里面安放Node
     */
    class DoubleLinkedList<K,V>{
        Node<K,V> head;
        Node<K,V> tail;

        // 2.1
        public DoubleLinkedList() {
            this.head = new Node<>();
            this.tail = new Node<>();
            head.next = tail;
            tail.prev = head;
        }

        // 2.2 添加到头
        public void addHead(Node<K,V> node){
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        // 2.3 删除节点
        public void removeNode(Node<K,V> node){
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.prev = null;
            node.next = null;
        }

        // 2.4 获取最后一个节点
        public Node getLast(){
            return tail.prev;
        }
    }


    private int cacheSize;
    Map<Integer, Node<Integer, Integer>> map;
    DoubleLinkedList<Integer, Integer> doubleLinkedList;

    public LRUCacheDemo(int cacheSize){
        // 坑位
        this.cacheSize = cacheSize;
        // 查找
        map = new HashMap<>();
        //
        doubleLinkedList = new DoubleLinkedList<>();

    }

    public int get(int key){
        if (!map.containsKey(key)){
            return -1;
        }
        Node<Integer, Integer> node = map.get(key);
        doubleLinkedList.removeNode(node);
        doubleLinkedList.addHead(node);
        return node.value;
    }

    // saveOrUpdate method
    public void put(int key, int value){
        // update
        if (map.containsKey(key)){
            Node<Integer, Integer> node = map.get(key);
            node.value = value;
            map.put(key, node);

            doubleLinkedList.removeNode(node);
            doubleLinkedList.addHead(node);
        } else {
            if (map.size() == cacheSize){
                Node lastNode = doubleLinkedList.getLast();
                map.remove(lastNode.key);
                doubleLinkedList.removeNode(lastNode);
            }
            // 才是新增
            Node<Integer, Integer> newNode = new Node<>(key, value);
            map.put(key, newNode);
            doubleLinkedList.addHead(newNode);
        }

    }
    public static void main(String[] args) {
        LRUCacheDemo lruCacheDemo = new LRUCacheDemo(3);
        lruCacheDemo.put(1,1);
        lruCacheDemo.put(2,2);
        lruCacheDemo.put(3,3);
        System.out.println(lruCacheDemo.map.keySet());

        lruCacheDemo.put(4,1);
        System.out.println(lruCacheDemo.map.keySet());

    }
}
