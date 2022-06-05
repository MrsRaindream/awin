package org.example.service.implementaion;

import org.example.model.Product;
import org.example.model.TransactionData;
import org.example.model.TransactionDataWithTotal;
import org.example.service.CalculatorServiceInterface;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CalculatorService implements CalculatorServiceInterface {

    public TransactionDataWithTotal calculateTotalProductSum(TransactionData transactionData) {
        BigDecimal totalAmount = Optional.ofNullable(transactionData)
                .map(TransactionData::getProducts)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(Product::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new TransactionDataWithTotal(transactionData, totalAmount);
    }

    public List<TransactionDataWithTotal> calculateTotalProductSum(List<TransactionData> transactionData) {
        return Optional.ofNullable(transactionData)
                .orElse(Collections.emptyList())
                .parallelStream()
                .filter(Objects::nonNull)
                .map(this::calculateTotalProductSum)
                .collect(Collectors.toList());
    }
}
