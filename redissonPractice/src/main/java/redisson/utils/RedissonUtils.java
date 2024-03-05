package redisson.utils;

import org.redisson.api.*;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedissonUtils<T> {

    private static RedissonClient redissonClient = null;
    private static final long DEFAULT_EXPIRED = 60L;

    static {
        redissonClient = (RedissonClient)ApplicationContextUtil.getBean("redissonClient");
    }


    /**
     * 读取缓存中的字符串，永久有效
     *
     * @param key 缓存key
     * @return 字符串
     */
    public static String getStr(String key) {
        RBucket<String> bucket = redissonClient.getBucket(key);
        return bucket.get();
    }

    /**
     * 缓存字符串
     *
     * @param key
     * @param value
     */
    public static void setStr(String key, String value) {
        RBucket<String> bucket = redissonClient.getBucket(key);
        bucket.set(value);
    }

    /**
     * 缓存带过期时间的字符串
     *
     * @param key     缓存key
     * @param value   缓存值
     * @param expired 缓存过期时间，long类型，必须传值
     */
    public static void setStr(String key, String value, long expired) {
        RBucket<String> bucket = redissonClient.getBucket(key);
        bucket.set(value, expired <= 0L ? DEFAULT_EXPIRED : expired, TimeUnit.SECONDS);
    }

    /**
     * 缓存带过期时间和单位的字符串
     *
     * @param key       缓存key
     * @param value     缓存值
     * @param expired   缓存过期时间，long类型，必须传值
     * @param timeUnit  缓存过期时间单位
     */
    public static void setStr(String key, String value, long expired, TimeUnit timeUnit) {
        RBucket<String> bucket = redissonClient.getBucket(key);
        bucket.set(value, expired <= 0L ? DEFAULT_EXPIRED : expired, timeUnit);
    }

    /**
     * 判断缓存是否存在
     *
     * @param key
     * @return true 存在
     */
    public static Boolean isExists(String key) {
        return redissonClient.getBucket(key).isExists();
    }

    /**
     * 删除key
     *
     * @param key
     * @return true 存在
     */
    public static Boolean delete(String key) {
        RBucket<String> bucket = redissonClient.getBucket(key);
        return bucket.delete();
    }

    /**
     * 读取缓存中的字符串，永久有效
     *
     * @param key 缓存key
     * @return 字符串
     */
    public static Object get(String key) {
        RBucket<String> bucket = redissonClient.getBucket(key);
        return bucket.get();
    }

    /**
     * 缓存字符串
     *
     * @param key
     * @param value
     */
    public static void set(String key, Object value) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        bucket.set(value);
    }

    /**
     * 缓存带过期时间的字符串
     *
     * @param key     缓存key
     * @param value   缓存值
     * @param expired 缓存过期时间，long类型，必须传值
     */
    public static void set(String key, Object value, long expired) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        bucket.set(value, expired <= 0L ? DEFAULT_EXPIRED : expired, TimeUnit.SECONDS);
    }

    /**
     * 缓存带过期时间和单位的字符串
     *
     * @param key       缓存key
     * @param value     缓存值
     * @param expired   缓存过期时间，long类型，必须传值
     * @param timeUnit  缓存过期时间单位
     */
    public static void set(String key, Object value, long expired, TimeUnit timeUnit) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        bucket.set(value, expired <= 0L ? DEFAULT_EXPIRED : expired, timeUnit);
    }

    /**
     * 获取位
     *
     * @param key       缓存key
     * @param bit       位;序号0(低位)起始
     * @return
     */
    public static boolean getBit(String key, int bit) {
        RBitSet bitSet = redissonClient.getBitSet(key);
        return bitSet.get(bit);
    }

    /**
     * 设置位
     *
     * @param key       缓存key
     * @param bit       位;序号0(低位)起始
     * @param bool      1或0
     * @return
     */
    public static Boolean setBit(String key, int bit, boolean bool) {
        RBitSet bitSet = redissonClient.getBitSet(key);
        return bitSet.set(bit, bool);
    }

    /**
     * 原子自增
     *
     * @param key       缓存key
     * @return
     */
    public static long incrementAndGet(String key) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
        return atomicLong.incrementAndGet();
    }

    /**
     * 原子自增
     *
     * @param key       缓存key
     * @param initValue      初始化的值
     * @return
     */
    public static long incrementAndGet(String key, long initValue) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
        atomicLong.set(initValue);
        return atomicLong.incrementAndGet();
    }

    /**
     * 原子浮点型
     *
     * @param key       缓存key
     * @param addValue  增加值
     * @return
     */
    public static double atomicDoubleAdd(String key, double addValue) {
        RAtomicDouble atomicDouble = redissonClient.getAtomicDouble(key);
        return atomicDouble.addAndGet(addValue);
    }

    /**
     * 原子浮点型
     *
     * @param key        缓存key
     * @param addValue   增加值
     * @param initValue  初始值
     * @return
     */
    public static double atomicDoubleAdd(String key, double addValue, double initValue) {
        RAtomicDouble atomicDouble = redissonClient.getAtomicDouble(key);
        atomicDouble.set(initValue);
        return atomicDouble.addAndGet(addValue);
    }

    /**
     * 获取map 整个
     *
     * @param key       缓存key
     * @return
     */
    public static Map<String, Object> getMap(String key) {
        return redissonClient.getMap(key);
    }

    /**
     * 获取map  某个key
     *
     * @param key       缓存key
     * @param mapKey    某个key
     * @return
     */
    public static Object getMap(String key, String mapKey) {
        RMap<String, Object> map = redissonClient.getMap(key);
        return map.get(mapKey);
    }

    /**
     * 移除map  某个key
     *
     * @param key       缓存key
     * @param mapKey    某个key
     * @return
     */
    public static Object removeMap(String key, String mapKey) {
        RMap<String, Object> map = redissonClient.getMap(key);
        return map.remove(mapKey);
    }

    /**
     * 上传map  某个key
     *
     * @param key       缓存key
     * @param mapKey    key
     * @param mapValue  value
     * @return
     */
    public static Object putMap(String key, String mapKey, String mapValue) {
        RMap<String, Object> map = redissonClient.getMap(key);
        return map.put(mapKey,mapValue);
    }

    /**
     * 获取set 整个
     *
     * @param key       缓存key
     * @return
     */
    public static Set<Object> getSet(String key) {
        return redissonClient.getSet(key);
    }

    /**
     * 移除set  某个key
     *
     * @param key       缓存key
     * @param setKey    某个key
     * @return
     */
    public static boolean removeSet(String key, String setKey) {
        RSet<Object> set = redissonClient.getSet(key);
        return set.remove(setKey);
    }

    /**
     * 上传set  某个key
     *
     * @param key       缓存key
     * @param setKey    key
     * @return
     */
    public static boolean addSet(String key, String setKey) {
        RSet<Object> set = redissonClient.getSet(key);
        return set.add(setKey);
    }

    /**
     * 获取set 整个
     *
     * @param key       缓存key
     * @return
     */
    public static Set<Object> getSortedSet(String key) {
        return redissonClient.getSortedSet(key);
    }

    /**
     * 移除set  某个key
     *
     * @param key       缓存key
     * @param setKey    某个key
     * @return
     */
    public static boolean removeSortedSet(String key, String setKey) {
        RSortedSet<Object> set = redissonClient.getSortedSet(key);
        return set.remove(setKey);
    }

    /**
     * 上传set  某个key
     *
     * @param key       缓存key
     * @param setKey    key
     * @return
     */
    public static boolean addSortedSet(String key, Object setKey) {
        RSortedSet<Object> set = redissonClient.getSortedSet(key);
        return set.add(setKey);
    }


    /**
     * 移除set  某个key
     *
     * @param key       缓存key
     * @param setKey    某个key
     * @return
     */
    public static boolean removeScoredSortedSet(String key, String setKey) {
        RScoredSortedSet<Object> set = redissonClient.getScoredSortedSet(key);
        return set.remove(setKey);
    }

    /**
     * 上传set  某个key
     *
     * @param key       缓存key
     * @param setKey    key
     * @param score     分数
     * @return
     */
    public static boolean addScoredSortedSet(String key, Object setKey, double score) {
        RScoredSortedSet<Object> set = redissonClient.getScoredSortedSet(key);
        return set.add(score,setKey);
    }

    /**
     * 返回计分排序set的index 整个
     *
     * @param key       缓存key
     * @param setKey    key
     * @return
     */
    public static int getIndexScoredSortedset(String key, Object setKey) {
        RScoredSortedSet<Object> set = redissonClient.getScoredSortedSet(key);
        return set.rank(setKey);
    }

    /**
     * 返回计分排序set的分数 整个
     *
     * @param key       缓存key
     * @param setKey    key
     * @return
     */
    public static double getScoreScoredSortedset(String key, Object setKey) {
        RScoredSortedSet<Object> set = redissonClient.getScoredSortedSet(key);
        return set.getScore(setKey);
    }

    /**
     * 上传list  某个key
     *
     * @param key       缓存key
     * @param setKey    key
     * @return
     */
    public static boolean addList(String key, Object setKey) {
        RList<Object> list = redissonClient.getList(key);
        return list.add(setKey);
    }


    /**
     * 移除list  某个key
     *
     * @param key       缓存key
     * @param setKey    某个key
     * @return
     */
    public static boolean removeList(String key, Object setKey) {
        RList<Object> list = redissonClient.getList(key);
        return list.remove(setKey);
    }

    /**
     * 获取list  某个key
     *
     * @param key       缓存key
     * @param index
     * @return
     */
    public static Object getOneFromList(String key, int index) {
        RList<Object> list = redissonClient.getList(key);
        return list.get(index);
    }

    /**
     * 上传queue  某个key
     *
     * @param key       缓存key
     * @param setKey    key
     * @return
     */
    public static boolean addQueue(String key, Object setKey) {
        RQueue<Object> queue = redissonClient.getQueue(key);
        return queue.add(setKey);
    }


    /**
     * 取出头部queue  某个key
     *
     * @param key       缓存key
     * @return
     */
    public static Object peekQueue(String key) {
        RQueue<Object> queue = redissonClient.getQueue(key);
        return queue.peek();
    }

    /**
     * 删除queue  某个key
     *
     * @param key       缓存key
     * @return
     */
    public static Object pollQueue(String key) {
        RQueue<Object> queue = redissonClient.getQueue(key);
        return queue.poll();
    }





    /***** 客户端维护 *****/

    /**
     * 客户端维护的整长型  自增
     *
     * @param key       缓存key
     * @return
     */
    public static void longAdderIncrement(String key) {
        RLongAdder atomicLong = redissonClient.getLongAdder(key);
        atomicLong.increment();
    }

    /**
     * 客户端维护的整长型  自减
     *
     * @param key       缓存key
     * @return
     */
    public static void longAdderDecrement(String key) {
        RLongAdder atomicLong = redissonClient.getLongAdder(key);
        atomicLong.decrement();
    }

    /**
     * 客户端维护的整长型  增加
     *
     * @param key       缓存key
     * @return
     */
    public static void longAdderAdd(String key, long addValue) {
        RLongAdder atomicLong = redissonClient.getLongAdder(key);
        atomicLong.add(addValue);
    }

    /**
     * 客户端维护的整长型  求和
     *
     * @param key       缓存key
     * @return
     */
    public static long longAdderSum(String key) {
        RLongAdder atomicLong = redissonClient.getLongAdder(key);
        return atomicLong.sum();
    }

    /**
     * 客户端维护的整长型  销毁
     *
     * @param key       缓存key
     * @return
     */
    public static void longAdderDestroy(String key) {
        RLongAdder atomicLong = redissonClient.getLongAdder(key);
        atomicLong.destroy();
    }


}
