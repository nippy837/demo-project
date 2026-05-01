package com.nippy.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nippy.demo.entity.Memo;

import java.util.List;

/**
 * 业务层接口：对外暴露「备忘录」用例，隐藏 Mapper 细节。
 */
public interface MemoService {
    //查询全部
    List<Memo> listAll();

    //按主键查询
    Memo getById(Long id);

    //新增：返回的Memo会带数据库生成的id
    Memo create(Memo memo);

    //更新：要求body里带id，且记录存在
    boolean update(Memo memo);

    //按主键删除
    boolean deleteById(Long id);

    /**
     * 分页查询；current 第几页（从 1 开始），size 每页条数。
     */
    IPage<Memo> page(long current, long size);
}