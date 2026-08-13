package org.accountBook.dto;

import lombok.Data;

@Data
public class Category {
    private Long user_id;
    private String type;
    private String name;
    private Long sore_order;
}
