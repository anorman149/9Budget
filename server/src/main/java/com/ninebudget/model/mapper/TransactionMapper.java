package com.ninebudget.model.mapper;

import com.ninebudget.model.Transaction;
import com.ninebudget.model.dto.TransactionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

/**
 * Mapper for the entity {@link Transaction} and its DTO {@link TransactionDto}.
 */
@Component
@Mapper(componentModel = "spring", imports = {Instant.class}, uses = {CategoryMapper.class, BudgetMapper.class, InstitutionAccountMapper.class, AccountMapper.class})
public interface TransactionMapper extends EntityMapper<TransactionDto, Transaction> {

    @Mapping(source = "category", target = "category")
    @Mapping(source = "budget.id", target = "budgetID")
    @Mapping(source = "budget.name", target = "budgetName")
    @Mapping(source = "account.id", target = "accountID")
    @Mapping(source = "institutionAccount", target = "institutionAccount")
    @Mapping(target = "date", expression = "java(transaction.getDate().toString())")
    TransactionDto toDto(Transaction transaction);

    @Mapping(source = "category", target = "category")
    @Mapping(source = "budgetID", target = "budget.id")
    @Mapping(source = "accountID", target = "account.id")
    @Mapping(source = "institutionAccount", target = "institutionAccount")
    @Mapping(target = "date", expression = "java(Instant.parse(transactionDto.getDate()))")
    Transaction toEntity(TransactionDto transactionDto);

    default Transaction fromId(UUID id) {
        if (id == null) {
            return null;
        }
        Transaction transaction = new Transaction();
        transaction.setId(id);
        return transaction;
    }
}
