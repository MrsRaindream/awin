package org.example.IT;

import org.example.model.TransactionData;
import org.example.model.TransactionDataWithTotal;
import org.example.service.implementaion.CalculatorService;
import org.example.utils.TestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CalculateAmountControllerIT {

    @Autowired
    private CalculatorService calculatorService;

    @Autowired
    private TestRestTemplate restTemplate;

    private TransactionData transactionDataRequest;

    private HttpEntity<TransactionData> httpRequest;

    private TransactionDataWithTotal expectedResult;

    @Before
    public void setUp() {
        transactionDataRequest = TestHelper.buildTransactionData();
        httpRequest = new HttpEntity<>(transactionDataRequest);
        expectedResult = TestHelper.buildTransactionDataWithTotal(transactionDataRequest);
    }

    @Test
    public void should_calculate_totalAmount_for_single() {
        ResponseEntity<TransactionDataWithTotal> responseEntity = restTemplate
                .postForEntity("/api/transactions/calculate/single/getTransactionDataWithTotal",
                        httpRequest,
                        TransactionDataWithTotal.class);
        TransactionDataWithTotal actual = calculatorService.calculateTotalProductSum(transactionDataRequest);

        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
        assertThat(actual)
                .isNotNull()
                .usingComparatorForType(BigDecimal::compareTo, BigDecimal.class)
                .isEqualTo(expectedResult);
    }

    @Test
    public void should_calculate_totalAmount_for_multiple() {
        ResponseEntity<TransactionDataWithTotal> responseEntity = restTemplate
                .postForEntity("/api/transactions/calculate/single/getTransactionDataWithTotal",
                        httpRequest,
                        TransactionDataWithTotal.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
        List<TransactionDataWithTotal> actual = calculatorService
                .calculateTotalProductSum(Arrays.asList(transactionDataRequest, transactionDataRequest));

        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
        assertThat(actual).containsExactlyInAnyOrder(expectedResult, expectedResult);
    }

}