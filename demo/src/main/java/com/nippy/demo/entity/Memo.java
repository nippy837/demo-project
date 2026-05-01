package com.nippy.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

//对应数据库表名
@TableName("memo")
@Data
public class Memo {

    //主键；auto表示数据库自增id
    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    private String content;
}
