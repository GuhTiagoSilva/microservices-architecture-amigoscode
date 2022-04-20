package com.amigoscode.notification;

import com.amigoscode.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void saveNotification(NotificationRequest notificationRequest) {
        Notification notification = Notification.builder()
                .message(notificationRequest.getMessage())
                .toCustomerId(notificationRequest.getToCustomerId())
                .toCustomerEmail(notificationRequest.getToCustomerEmail())
                .sender(notificationRequest.getSender())
                .sentAt(LocalDateTime.now())
                .build();
        notificationRepository.save(notification);
    }

}
