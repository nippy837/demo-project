package com.nippy.demo.controller;

import com.nippy.demo.common.Result;
import com.nippy.demo.entity.Memo;
import com.nippy.demo.service.MemoService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Result<List<Memo>> list(){
        return Result.ok(memoService.listAll());
    }

    /** 详情：GET /api/memos/1 */
    @GetMapping("/{id}")
    public Result<Memo> get(@PathVariable Long id){
        // 不存在时 getById 会抛 BusinessException，由全局处理器返回 Result
        return Result.ok(memoService.getById(id));
    }

    /** 新增：POST /api/memos/insert */
    @PostMapping("/insert")
    public Result<Memo> insert(@Valid @RequestBody Memo memo){
        return Result.ok(memoService.create(memo));
    }

    /** 全量更新（按 id）：PUT /api/memos */
    @PutMapping
    public Result<Void> update(@Valid @RequestBody Memo memo){
        if(!memoService.update(memo)){
            return Result.fail(400, "更新失败或记录不存在");
        }
        return Result.ok();
    }

    /** 删除：DELETE /api/memos/1 */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id){
        if(!memoService.deleteById(id)){
            return Result.fail(400, "删除失败或记录不存在");
        }
        return Result.ok();
    }
}
