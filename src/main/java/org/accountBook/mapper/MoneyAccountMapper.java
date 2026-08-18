package org.accountBook.mapper;

import org.accountBook.dto.Account;
import org.accountBook.dto.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MoneyAccountMapper {


   int addTypeName(Category category);

    void addAccount(Account account);

    List<Category> getCategory(Long id);

    //为了避免删除已有记录的分类的统计函数
    int countTypeByCaId(Long id);

    int deleteType(@Param("id") Long id,
                    @Param("userid") Long userid);
}
