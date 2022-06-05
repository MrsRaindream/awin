package org.example.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.model.Product;
import org.example.model.TransactionData;
import org.example.model.TransactionDataWithTotal;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

/**
 * Helper to generate test models
 */
public class TestHelper {

    /**
     * Count letters in product name (generated)
     */
    public static final Integer COUNT_LETTERS_IN_PRODUCT_NAME = 10;

    public static TransactionData buildTransactionData() {
        TransactionData transactionData = new TransactionData();
        transactionData.setId(UUID.randomUUID());
        transactionData.setDate(LocalDateTime.now());
        transactionData.setProducts(Arrays.asList(buildProduct(genRandomString()), buildProduct(genRandomString())));
        return transactionData;
    }

    public static TransactionDataWithTotal buildTransactionDataWithTotal() {
        TransactionDataWithTotal transactionDataWithTotal = new TransactionDataWithTotal();
        TransactionData transactionData = buildTransactionData();
        transactionDataWithTotal.setTransactionData(transactionData);
        BigDecimal totalAmount = transactionData
                .getProducts()
                .stream()
                .map(Product::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        transactionDataWithTotal.setTotalAmount(totalAmount);
        return transactionDataWithTotal;
    }

    public static TransactionDataWithTotal buildTransactionDataWithTotal(TransactionData transactionData) {
        TransactionDataWithTotal transactionDataWithTotal = new TransactionDataWithTotal();
        transactionDataWithTotal.setTransactionData(transactionData);
        BigDecimal totalAmount = transactionData
                .getProducts()
                .stream()
                .map(Product::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        transactionDataWithTotal.setTotalAmount(totalAmount);
        return transactionDataWithTotal;
    }

    public static Product buildProduct(String name) {
        Product product = new Product();
        product.setName(name);
        product.setAmount(genRandomBigDecimal());
        return product;
    }

    public static String genRandomString() {
        return RandomStringUtils.randomAlphabetic(COUNT_LETTERS_IN_PRODUCT_NAME);
    }

    public static BigDecimal genRandomBigDecimal() {
        return new BigDecimal(Math.random());
    }

}
