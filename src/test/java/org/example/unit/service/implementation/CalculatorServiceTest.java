package org.example.unit.service.implementation;

import org.example.model.TransactionData;
import org.example.model.TransactionDataWithTotal;
import org.example.service.implementaion.CalculatorService;
import org.example.utils.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorServiceTest {

    @InjectMocks
    private CalculatorService calculatorService;

    @Test
    public void should_calculate_total_products_amount_for_single() {
        TransactionData transactionData = TestHelper.buildTransactionData();
        TransactionDataWithTotal expected = TestHelper.buildTransactionDataWithTotal(transactionData);
        TransactionDataWithTotal actual = calculatorService.calculateTotalProductSum(transactionData);

        assertThat(actual)
                .isNotNull()
                .isEqualTo(expected);
    }

    @Test
    public void should_calculate_total_products_amount_for_batch() {
        TransactionData transactionDataOne = TestHelper.buildTransactionData();
        TransactionData transactionDataTwo = TestHelper.buildTransactionData();
        TransactionDataWithTotal expectedOne = TestHelper.buildTransactionDataWithTotal(transactionDataOne);
        TransactionDataWithTotal expectedTwo = TestHelper.buildTransactionDataWithTotal(transactionDataTwo);
        List<TransactionDataWithTotal> actualList = calculatorService
                .calculateTotalProductSum(Arrays.asList(transactionDataOne, transactionDataTwo));

        assertThat(actualList)
                .isNotEmpty()
                .containsExactlyInAnyOrder(expectedOne, expectedTwo);
    }

}
