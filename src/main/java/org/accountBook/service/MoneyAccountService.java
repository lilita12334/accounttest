package org.accountBook.service;

import org.accountBook.dto.Account;
import org.accountBook.dto.Category;

import java.util.List;

public interface MoneyAccountService {
     void addAccountType(Category category);
     void addAccount(Account account);

     void deleteType(Long id,Long userid);

     void deleteAccount(Long id);

     List<Category> getCategory(Long id);

     List<Account> getAccount();
}
