package com.nippy.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nippy.demo.entity.Memo;
import com.nippy.demo.exception.BusinessException;
import com.nippy.demo.mapper.MemoMapper;
import com.nippy.demo.service.MemoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Service：交给 Spring 管理，可被 Controller 构造器注入
 */
@Service
public class MemoServiceImpl implements MemoService {

    private final MemoMapper memoMapper;


    public MemoServiceImpl(MemoMapper memoMapper) {
        this.memoMapper = memoMapper;
    }

    @Override
    public List<Memo> listAll(){
        //null表示无条件，查出整张表
        return memoMapper.selectList(null);
    }

    @Override
    public Memo getById(Long id) {
        Memo memo = memoMapper.selectById(id);
        if(memo == null){
            throw new BusinessException(404, "备忘录不存在");
        }
        return memo;
    }

    @Override
    public Memo create(Memo memo) {
        memo.setId(null);
        memoMapper.insert(memo);
        return memo;
    }

    @Override
    public boolean update(Memo memo) {
        if(memo.getId() == null){
            return false;
        }
        // updateById：按主键更新非空字段
        int rows = memoMapper.updateById(memo);
        return rows > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        int rows = memoMapper.deleteById(id);
        return rows > 0;
    }

    @Override
    public IPage<Memo> page(long current, long size) {
        // Page(当前页, 每页条数)；MP 会从 1 开始计页
        Page<Memo> p = new Page<>(current, size);
        // 第二个参数 null 表示无额外条件；以后要按标题查可换成 QueryWrapper
        return memoMapper.selectPage(p, null);
    }
}
