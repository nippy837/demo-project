package com.nippy.demo.controller;

import com.nippy.demo.entity.Memo;
import com.nippy.demo.mapper.MemoMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//// @RestController = @Controller + 返回值直接作为 JSON 写入响应体
@RestController
// 本类所有接口 URL 前缀为 /api/memos
@RequestMapping("/api/memos")
public class MemoController {
    private final MemoMapper memoMapper;

    // 构造器注入：Spring 自动把已注册的 MemoMapper 实现（其实是 MyBatis 生成的代理）传进来
    public MemoController(MemoMapper memoMapper) {
        this.memoMapper = memoMapper;
    }

    @GetMapping("/list")
    public List<Memo> list(){
        return memoMapper.selectList(null);
    }

    @PostMapping("/insert")
    public Memo insert(@RequestBody Memo body){
        //防止客户端传id干扰自增
        body.setId(null);
        memoMapper.insert(body);
        return body;
    }
}
