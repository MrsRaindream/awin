package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Transaction details with calculated total amount of products
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@JsonSerialize
@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDataWithTotal implements Serializable {

    /**
     * Transaction data
     */
    private TransactionData transactionData;

    /**
     * Total amount of products
     */
    private BigDecimal totalAmount;

}
