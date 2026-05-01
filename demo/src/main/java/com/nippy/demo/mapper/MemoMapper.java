package com.nippy.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nippy.demo.entity.Memo;

// 继承 BaseMapper<Memo> 后，无需 XML 即可使用 insert、deleteById、selectById、selectList 等
public interface MemoMapper extends BaseMapper<Memo> {
}
