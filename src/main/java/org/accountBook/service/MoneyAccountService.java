package org.accountBook.service;

import org.accountBook.dto.Account;
import org.accountBook.dto.Category;

public interface MoneyAccountService {
     void addAccountType(Category category);
     void addAccount(Account account);
}
