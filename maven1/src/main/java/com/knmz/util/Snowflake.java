package com.knmz.util;

/**
 * @Author: zl
 * @Date: 2019/3/17 21:49
 */
public class Snowflake {
    private final static long unusedBits = 1L;
    private final static long timestampBits = 41L;
    private final static long datacenterIdBits = 5L;
    private final static long workerIdBits = 5L;
    private final static long sequenceBits = 12L;

    private final static long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private final static long maxSequence = -1L ^ (-1L << sequenceBits);

    private final static long timestampShift = sequenceBits + datacenterIdBits + workerIdBits;
    private final static long datacenterIdShift = sequenceBits + workerIdBits;
    private final static long workerIdShift = sequenceBits;


    /**
     * reference material of timestamp
     */
    private final static long epoch = 1460390400000L;

    /**
     * data center number the process running on, its value can't be modified
     * after initialization.
     */
    private static long datacenterId;

    /**
     * machine or process number, its value can't be modified after
     * initialization.
     */
    private static long workerId;

    /**
     * the unique and incrementing sequence number scoped in only one
     * period/unit (here is ONE millisecond). its value will be increased by 1
     * in the same specified period and then reset to 0 for next period.
     */
    private static long sequence = 0L;

    /**
     * the time stamp last snowflake ID generated
     */
    private static long lastTimestamp = -1L;

    /**
     * snowflake is initialized or not
     */
    private static boolean initialized = false;

    /**
     * generate an unique and incrementing id
     *
     * @return id
     */
    public static synchronized long nextId() {
        long currTimestamp = timestampGen();

        if (currTimestamp < lastTimestamp) {
            throw new IllegalStateException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - currTimestamp));
        }

        if (currTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & maxSequence;
            if (sequence == 0) {
                currTimestamp = waitNextMillis(currTimestamp);
            }

        } else {
            sequence = 0L;
        }

        lastTimestamp = currTimestamp;

        return ((currTimestamp - epoch) << timestampShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    /**
     * @param dataCenterId data center number the process running on, value range: [0,31]
     * @param serverId     machine or process number, value range: [0,31]
     */
    public static void init(long dataCenterId, long serverId) {
        if (!initialized) {
            if (dataCenterId > maxDatacenterId || dataCenterId < 0) {
                throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
            }
            if (serverId > maxWorkerId || serverId < 0) {
                throw new IllegalArgumentException(String.format("server Id can't be greater than %d or less than 0", maxWorkerId));
            }

            datacenterId = dataCenterId;
            workerId = serverId;
            initialized = true;
        }
    }


    /**
     * running loop blocking until next millisecond
     *
     * @param currTimestamp current time stamp
     * @return current time stamp in millisecond
     */
    protected static long waitNextMillis(long currTimestamp) {
        while (currTimestamp <= lastTimestamp) {
            currTimestamp = timestampGen();
        }
        return currTimestamp;
    }

    /**
     * get current time stamp
     *
     * @return current time stamp in millisecond
     */
    protected static long timestampGen() {
        return System.currentTimeMillis();
    }

    public static long getEpoch() {
        return epoch;
    }

}