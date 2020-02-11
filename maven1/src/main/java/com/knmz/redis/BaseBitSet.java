package com.knmz.redis;

import java.io.Serializable;

/**
 * 基本的位集合接口，如果你想使用自己的数据结构可以实现这个接口
 *
 * @author zl
 * @date 2019/8/23 16:04
 */
public interface BaseBitSet extends Cloneable, Serializable {

    /**
     * 在布隆过滤器中设置一个单独的位,默认值是true。
     *
     * @param bitIndex bit index.
     */
    public void set(int bitIndex);

    /**
     * 在布隆过滤器中设置一个单独的位,默认值是true/false。
     *
     * @param bitIndex bit index.
     * @param value    value true or false.
     */
    public void set(int bitIndex, boolean value);

    /**
     * 返回用于存储在布隆过滤器中的位集合。
     *
     * @param bitIndex bit index.
     * @return the bit set used to store the Bloom filter.
     */
    public boolean get(int bitIndex);

    /**
     * 清除当前索引的位集合，同样在这个索引的位集合值为false
     *
     * @param bitIndex bit index.
     */
    public void clear(int bitIndex);

    /**
     * 清除位集合，同样位集合所有值为false
     */
    public void clear();

    /**
     * @return the number of bits in the Bloom filter.
     */
    public long size();

    /**
     * @return is the bit set empty.
     */
    public boolean isEmpty();
}
