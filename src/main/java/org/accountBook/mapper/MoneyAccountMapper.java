package org.accountBook.mapper;

import org.accountBook.dto.Category;

public interface MoneyAccountMapper {
    void addTypaName(String typename);

    void addTypeName(Category category);
}
