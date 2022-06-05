package org.example.service;

import org.example.model.TransactionData;
import org.example.model.TransactionDataWithTotal;

import java.util.List;

/**
 * Calculator service
 * Calculating amounts for Transaction
 */
public interface CalculatorServiceInterface {

    /**
     * Calculating total amount of product list for one transaction
     *
     * @param transactionData data for transaction
     * @return Transaction data with total amount of products
     */
    TransactionDataWithTotal calculateTotalProductSum(TransactionData transactionData);

    /**
     * Calculating total amount of product list for transaction list
     *
     * @param transactionData transactions data list
     * @return Transaction data list with total amount of products for each transaction
     */
    List<TransactionDataWithTotal> calculateTotalProductSum(List<TransactionData> transactionData);

}
