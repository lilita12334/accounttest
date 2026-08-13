package org.accountBook.dto;

import lombok.Data;

@Data
public class Category {
    Long user_id;
    String type;
    String name;
    Long sore_order;
}
