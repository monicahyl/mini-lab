package com.example.minispringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author huangyulu
 * @Date 2026/2/4 16:22
 * @Description 没有意识到线程重用导致用户信息错乱的 Bug
 */
@RestController
public class UserController {

    private static final ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);

    @GetMapping("/wrong")
    public Map wrong(@RequestParam("userId") Integer userId) {
        //设置用户信息之前先查询一次ThreadLocal中的用户信息
        String before = Thread.currentThread().getName() + ":" + currentUser.get();
        //设置用户信息到ThreadLocal
        currentUser.set(userId);
        Map result = new HashMap();
        try {
            //设置用户信息之后再查询一次ThreadLocal中的用户信息
            String after = Thread.currentThread().getName() + ":" + currentUser.get();
            //汇总输出两次查询结果
            result.put("before", before);
            result.put("after", after);
        } finally {
            currentUser.remove();
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(currentUser.get());
    }
}
