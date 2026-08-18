package org.accountBook.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class Account {
    private Long user_id;
    private Long category_id;
    private Long amount;

   private  String note;
//localdate只记到年月日
   private LocalDateTime transaction_time;


}
