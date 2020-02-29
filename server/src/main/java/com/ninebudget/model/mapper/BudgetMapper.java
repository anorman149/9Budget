package com.ninebudget.model.mapper;

import com.ninebudget.model.Budget;
import com.ninebudget.model.dto.BudgetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Mapper for the entity {@link Budget} and its DTO {@link BudgetDto}.
 */
@Component
@Mapper(componentModel = "spring", uses = {AccountMapper.class, CategoryMapper.class, TransactionMapper.class})
public interface BudgetMapper extends EntityMapper<BudgetDto, Budget> {

    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "transactions", target = "transactions")
    BudgetDto toDto(Budget budget);

    @Mapping(source = "accountId", target = "account")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "transactions", target = "transactions")
    @Mapping(target = "removeTransaction", ignore = true)
    Budget toEntity(BudgetDto budgetDto);

    default Budget fromId(UUID id) {
        if (id == null) {
            return null;
        }
        Budget budget = new Budget();
        budget.setId(id);
        return budget;
    }
}
