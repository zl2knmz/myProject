package com.knmz.util.bloom;

import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;
import redis.clients.jedis.Jedis;

import java.nio.charset.Charset;

/**
 * redis实现布隆过滤器
 *
 * @author zl
 * @date 2019/10/29 14:06
 */
public class RedisBloomFilter {
    /**
     * 要插入多少数据
     */
    private static final int EXPECTED_INSERTIONS = 100;

    /**
     * 期望的误判率
     */
    private static final double FPP = 0.01;

    /**
     * bit数组长度
     */
    private static long numBits;

    /**
     * hash函数数量
     */
    private static int numHashFunctions;

    static {
        numBits = optimalNumOfBits(EXPECTED_INSERTIONS, FPP);
        numHashFunctions = optimalNumOfHashFunctions(EXPECTED_INSERTIONS, numBits);
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.1.95", 6379);
        for (int i = 0; i < 100; i++) {
            long[] indexs = getIndexs(String.valueOf(i));
            for (long index : indexs) {
                jedis.setbit("codebear:bloom", index, true);
            }
        }
        for (int i = 0; i < 100; i++) {
            long[] indexs = getIndexs(String.valueOf(i));
            for (long index : indexs) {
                Boolean isContain = jedis.getbit("codebear:bloom", index);
                if (!isContain) {
                    System.out.println(i + "肯定没有重复");
                }
            }
            System.out.println(i + "可能重复");
        }
        jedis.close();
    }

    /**
     * 根据key获取bitmap下标
     */
    private static long[] getIndexs(String key) {
        long hash1 = hash(key);
        long hash2 = hash1 >>> 16;
        long[] result = new long[numHashFunctions];
        for (int i = 0; i < numHashFunctions; i++) {
            long combinedHash = hash1 + i * hash2;
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            result[i] = combinedHash % numBits;
        }
        return result;
    }

    private static long hash(String key) {
        Charset charset = Charset.forName("UTF-8");
        return Hashing.murmur3_128().hashObject(key, Funnels.stringFunnel(charset)).asLong();
    }

    /**
     * 计算hash函数个数
     */
    private static int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }

    /**
     * 计算bit数组长度
     */
    private static long optimalNumOfBits(long n, double p) {
        if (p == 0) {
            p = Double.MIN_VALUE;
        }
        return (long) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }
}
