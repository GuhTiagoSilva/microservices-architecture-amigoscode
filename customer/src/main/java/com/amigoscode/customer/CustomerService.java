package com.amigoscode.customer;

import com.amigoscode.amqp.RabbitMQMessageProducer;
import com.amigoscode.clients.fraud.FraudCheckResponse;
import com.amigoscode.clients.fraud.FraudClient;
import com.amigoscode.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    //private final NotificationClient notificationClient;
    private final RabbitMQMessageProducer producer;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {

        Customer customer = Customer.builder()
                .email(customerRegistrationRequest.getEmail())
                .firstName(customerRegistrationRequest.getFirstName())
                .lastName(customerRegistrationRequest.getLastName())
                .build();

        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.getIsFraudster())
            throw new IllegalStateException("fraudster");

        NotificationRequest notificationRequest = NotificationRequest.builder()
                .toCustomerId(customer.getId())
                .message("new notification microservice communication test")
                .sender("AmigosCode")
                .toCustomerEmail(customer.getEmail())
                .build();

        producer.publish(notificationRequest, "internal.exchange", "internal.notification.routing-key");
        //notificationClient.saveNotification(notificationRequest);

    }

}
