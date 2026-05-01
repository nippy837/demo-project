package com.nippy.demo.service.impl;

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
}
