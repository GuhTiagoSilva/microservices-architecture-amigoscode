package com.amigoscode.notification;

import com.amigoscode.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void saveNotification(@RequestBody NotificationRequest notificationRequest) {
        log.info("notification for customer {}", notificationRequest.getToCustomerId());
        notificationService.saveNotification(notificationRequest);
    }

}
