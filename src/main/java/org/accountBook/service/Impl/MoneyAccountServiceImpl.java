package org.accountBook.service.Impl;

import org.accountBook.common.BusinessException;
import org.accountBook.dto.Account;
import org.accountBook.dto.Category;
import org.accountBook.mapper.MoneyAccountMapper;
import org.accountBook.service.MoneyAccountService;
import org.ehcache.spi.service.MaintainableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoneyAccountServiceImpl implements MoneyAccountService {
    @Autowired
    MoneyAccountMapper moneyAccountMapper;

    @Override
    public void addAccountType(Category category){


        int rows=moneyAccountMapper.addTypeName(category);
        if(rows==0){
            throw new BusinessException("已有该分类，无法重复添加");
        }
    }


    @Override
    public List<Category> getCategory(Long id){
// 可选：检查 id 本身是否为 null
        if (id == null) {
            throw new BusinessException("用户ID不能为空");
        }

        List<Category> list = moneyAccountMapper.getCategory(id);

        // 判断返回结果是否为空
        if (list == null || list.isEmpty()) {
            throw new BusinessException("暂无分类数据，请先添加分类");
        }

        return list;
    }

    @Override
    public void addAccount(Account account){
        moneyAccountMapper.addAccount(account);
    }

    @Override
    public void deleteType(Long id,Long userid){
        int count= moneyAccountMapper.countTypeByCaId(id);
        if(count>0){
            throw new RuntimeException("该分类下已有记录，无法删除分类");
        }
        int row= moneyAccountMapper.deleteType(id, userid);
    if(row==0){throw new BusinessException("删除失败，该分类不存在或不属于您");
    }
    }
}
