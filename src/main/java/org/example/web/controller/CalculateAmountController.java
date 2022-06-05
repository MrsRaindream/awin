package org.example.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.example.model.TransactionData;
import org.example.model.TransactionDataWithTotal;
import org.example.service.implementaion.CalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

/**
 * REST-controller for calculate amounts for transactions
 */
@RestController
@RequestMapping("/api/transactions/calculate")
@Slf4j
@AllArgsConstructor
@Api(tags = "REST-controller for calculate total amount of products for transactions")
public class CalculateAmountController {

    /**
     * Calculator service
     */
    private final CalculatorService calculatorService;

    /**
     * GetTransaction data with total for single transaction
     *
     * @param transactionData
     * @return
     */
    //TODO: would be good to add authorization, validation and exception handler, omitted for now
    @PostMapping("single/getTransactionDataWithTotal")
    @ResponseStatus(OK)
    @ApiOperation(value = "Calculate total amount of products for transaction",
            response = TransactionDataWithTotal.class)
    public TransactionDataWithTotal getTransactionDataWithTotal(@RequestBody TransactionData transactionData) {
        log.info("POST single/getTransactionDataWithTotal, data: [{}]", transactionData);
        return calculatorService.calculateTotalProductSum(transactionData);
    }

    /**
     * Get transaction data with total for list of transactions
     *
     * @param transactionDataList
     * @return
     */
    //TODO: would be good to add authorization, validation and exception handler, omitted for now
    @PostMapping("batch/getTransactionDataWithTotal")
    @ResponseStatus(OK)
    @ApiOperation(value = "Calculate total amount of products for batch transactions",
            response = TransactionDataWithTotal.class)
    public List<TransactionDataWithTotal> getTransactionDataWithTotal(@RequestBody List<TransactionData> transactionDataList) {
        log.info("POST batch/getTransactionDataWithTotal, data: [{}]", transactionDataList);
        return calculatorService.calculateTotalProductSum(transactionDataList);
    }

}
