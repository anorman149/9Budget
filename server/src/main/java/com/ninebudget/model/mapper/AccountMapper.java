package com.ninebudget.model.mapper;

import com.ninebudget.model.Account;
import com.ninebudget.model.dto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

/**
 * Mapper for the entity {@link Account} and its DTO {@link AccountDto}.
 */
@Component
@Mapper(componentModel = "spring", uses = {})
public interface AccountMapper extends EntityMapper<AccountDto, Account> {

    @Mapping(target = "users", ignore = true)
    @Mapping(target = "removeUser", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "budgets", ignore = true)
    @Mapping(target = "institutionAccount", ignore = true)
    Account toEntity(AccountDto accountDto);

    default Account fromId(Long id) {
        if (id == null) {
            return null;
        }
        Account account = new Account();
        account.setId(id);
        return account;
    }
}
