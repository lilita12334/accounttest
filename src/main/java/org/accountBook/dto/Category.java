package org.accountBook.dto;

import lombok.Data;

@Data
public class Category {

    private Long id;
    private Long user_id;
    //支出类型
    private String type;
    //名称
    private String name;
    //权重
    private Long sort_order;
}
