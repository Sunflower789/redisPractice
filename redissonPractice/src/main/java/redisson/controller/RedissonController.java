package redisson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redisson.service.RedissonService;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/redisson")
public class RedissonController {

    @Autowired
    RedissonService redissonService;

    @PostMapping("/test1")
    private String test1() throws ExecutionException, InterruptedException {
        return redissonService.test3();
    }

    @PostMapping("/test2")
    private String test1(@RequestHeader("key") String key) throws ExecutionException, InterruptedException {
        redissonService.test4(key);
        return "1122";
    }

    @PostMapping("/test3")
    private String test2(@RequestHeader("key") String key) throws ExecutionException, InterruptedException {
        return redissonService.test5(key);
    }
}
