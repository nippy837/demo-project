package com.nippy.demo.controller;

import com.nippy.demo.entity.Memo;
import com.nippy.demo.service.MemoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// @RestController = @Controller + 返回值直接作为 JSON 写入响应体
@RestController
// 本类所有接口 URL 前缀为 /api/memos
@RequestMapping("/api/memos")
public class MemoController {
    private final MemoService memoService;


    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    /** 列表：GET /api/memos/list */
    @GetMapping("/list")
    public List<Memo> list(){
        return memoService.listAll();
    }

    /** 详情：GET /api/memos/1 */
    @GetMapping("/{id}")
    public Memo get(@PathVariable Long id){
        return memoService.getById(id);
    }

    /** 新增：POST /api/memos/insert */
    @PostMapping("/insert")
    public Memo insert(@RequestBody Memo memo){
        return memoService.create(memo);
    }

    /** 全量更新（按 id）：PUT /api/memos */
    @PutMapping
    public boolean update(@RequestBody Memo memo){
        return memoService.update(memo);
    }

    /** 删除：DELETE /api/memos/1 */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id){
        return memoService.deleteById(id);
    }
}
