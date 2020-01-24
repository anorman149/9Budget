package com.ninebudget.model.mapper;

import com.ninebudget.model.Transaction;
import com.ninebudget.model.dto.TransactionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

/**
 * Mapper for the entity {@link Transaction} and its DTO {@link TransactionDto}.
 */
@Component
@Mapper(componentModel = "spring", uses = {CategoryMapper.class, BudgetMapper.class, InstitutionMapper.class})
public interface TransactionMapper extends EntityMapper<TransactionDto, Transaction> {

    @Mapping(source = "category", target = "category")
    @Mapping(source = "budget.id", target = "budgetId")
    @Mapping(source = "institution.id", target = "institutionId")
    TransactionDto toDto(Transaction transaction);

    @Mapping(source = "category", target = "category")
    @Mapping(source = "budgetId", target = "budget")
    @Mapping(source = "institutionId", target = "institution")
    Transaction toEntity(TransactionDto transactionDto);

    default Transaction fromId(Long id) {
        if (id == null) {
            return null;
        }
        Transaction transaction = new Transaction();
        transaction.setId(id);
        return transaction;
    }
}
