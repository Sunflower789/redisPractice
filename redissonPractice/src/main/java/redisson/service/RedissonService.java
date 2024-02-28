package redisson.service;

import org.redisson.api.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.concurrent.*;

@Service
public class RedissonService {

    @Resource
    RedissonClient redissonClient;

    @Resource
    RedissonReactiveClient redissonReactiveClient;

    @Resource
    RedissonRxClient redissonRxClient;

    public String test1(){
        RBucket<Object> bucket = redissonClient.getBucket("name3");
        bucket.set("a3",20, TimeUnit.SECONDS);
        Object a2 = bucket.get();
        return a2.toString();
    }

    // 异步
    public String test2() throws ExecutionException, InterruptedException {
        RBucket<Object> bucket = redissonClient.getBucket("name4");
        System.out.println("开始异步获取1");
        RFuture<Object> future = bucket.getAsync();
        System.out.println("开始异步获取2");
        future.whenCompleteAsync((res, exception) -> {
            System.out.println("异步获取res:" + res);
            // do something with res or else
        }, Executors.newSingleThreadExecutor());
        System.out.println("开始异步获取3");
        // 异步阻塞，否则主线程结束没有正确返回结果
        Object res = future.get();
        return res.toString();
    }

    // 响应式
    public String test3() throws ExecutionException, InterruptedException {
        RBucketReactive<Object> bucket = redissonReactiveClient.getBucket("name4");
        System.out.println("开始异步获取1");
        Mono<Object> get = bucket.get();
        System.out.println("开始异步获取2");
        get.doOnSuccess(res -> {
            System.out.println("异步获取res:" + res);
            // do something with res or else
        }).subscribe();
        System.out.println("开始异步获取3");
        Object result = get.block();
        return result.toString();
    }

}
