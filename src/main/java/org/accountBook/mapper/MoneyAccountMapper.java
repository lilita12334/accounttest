package org.accountBook.mapper;

import org.accountBook.dto.Account;
import org.accountBook.dto.Category;

public interface MoneyAccountMapper {


    void addTypeName(Category category);

    void addAccount(Account account);
}
