package org.example.unit.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.TransactionDataWithTotal;
import org.example.utils.TestHelper;
import org.example.model.TransactionData;
import org.example.service.implementaion.CalculatorService;
import org.example.web.controller.CalculateAmountController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CalculateAmountController.class)
public class CalculateAmountControllerTest {

    @MockBean
    private CalculatorService calculatorService;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

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
    public void should_call_endpoint_for_single() throws Exception {
        given(calculatorService.calculateTotalProductSum(eq(transactionDataRequest))).willReturn(expectedResult);
        this.mockMvc
                .perform(post("/api/transactions/calculate/single/getTransactionDataWithTotal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionDataRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void should_call_endpoint_for_batch() throws Exception {
        given(calculatorService.calculateTotalProductSum(eq(transactionDataRequest))).willReturn(expectedResult);
        this.mockMvc
                .perform(post("/api/transactions/calculate/batch/getTransactionDataWithTotal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Arrays.asList(transactionDataRequest))))
                .andExpect(status().isOk());
    }

}
