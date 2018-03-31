package cache.local;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 本地缓存实现
 */
public class LocalCache<K, V> {
    private final Lock lock = new ReentrantLock();
    private final int maxCapacity;     //最大存储能力
    private final Map<K, V> eden;       //边界缓存or临时缓存
    private final Map<K, V> longterm;   //长久缓存 当 对象不使用时会被虚拟机gc()的

    public LocalCache(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.eden = new ConcurrentHashMap<K, V>(maxCapacity);
        this.longterm = new WeakHashMap<K, V>(maxCapacity);
    }

    //清空缓存中某个key和value先清空缓存中的值然后清除固定缓存中的值
    public void deleteKey(K k) {
        if (null != this.get(k)) {
            this.doDel(k);
        }
    }

    public void doDel(K k) {
        this.eden.remove(k);
        if (null != this.longterm.get(k)) {
            this.longterm.remove(k);
        }
    }

    //根据某Key获取相对应的值，先从临时缓存中查找，若不存在则去固定缓存中查找，若存在则将查到的值放入到临时缓存中
    public V get(K k) {
        V v = this.eden.get(k);
        if (v == null) {
            lock.lock();
            try {
                v = this.longterm.get(k);
            } finally {
                lock.unlock();
            }
            if (v != null) {
                this.eden.put(k, v);
            }
        }
        return v;
    }

    //将值放入临时缓存中，若临时缓存溢出，则将临时缓存中全部取出放入固定缓存中，同时清空临时缓存，并将新加入的值放入临时缓存中
    public void put(K k, V v) {
        if (this.eden.size() >= maxCapacity) {
            lock.lock();
            try {
                this.longterm.putAll(this.eden);
            } finally {
                lock.unlock();
            }
            this.eden.clear();
        }
        this.eden.put(k, v);
    }

    public static class MyCache extends LocalCache<String, Object> {

        public MyCache(int maxCapacity) {
            super(maxCapacity);
        }

        public static void main(String[] args) {
            MyCache myCache = new MyCache(10);
            myCache.put("1", 123);
            System.out.println(myCache.get("1"));
            myCache.deleteKey("1");
            System.out.println(myCache.get("1"));
        }
    }
}
