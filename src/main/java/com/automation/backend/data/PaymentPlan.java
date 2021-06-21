package com.automation.backend.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PaymentPlan {
    String id;
    String orderId;
    Long updatedTimestamp;
    String actionType;
    String clientTransactionId;
    String crmRefId;
    String login;
    String ppsType;
    String sourceId;
    String state;
    Long totalAmount;
    String returnId;
    String cartId;
    String deferredPaymentInitiator;

    //`id`, `orderId`, `updatedTimestamp`, `actionType`, `clientTransactionId`, `crmRefId`, `login`, `ppsType`, `sourceId`, `state`, `totalAmount`, `returnId`
}
