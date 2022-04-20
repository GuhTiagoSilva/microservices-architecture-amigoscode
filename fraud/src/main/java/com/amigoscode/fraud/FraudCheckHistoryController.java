package com.amigoscode.fraud;

import com.amigoscode.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
public class FraudCheckHistoryController {

    private final FraudCheckHistoryService fraudCheckHistoryService;

    @GetMapping("/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable(value = "customerId") Integer customerId) {
        boolean isFraudlentCustomer = fraudCheckHistoryService.isFraudlentCustomer(customerId);
        log.info("fraud check request for customer {}", customerId);
        return new FraudCheckResponse(isFraudlentCustomer);
    }

}
