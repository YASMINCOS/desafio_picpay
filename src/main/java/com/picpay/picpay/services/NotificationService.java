package com.picpay.picpay.services;


import com.picpay.picpay.domain.users.User;
import com.picpay.picpay.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationDTO = new NotificationDTO(email, message);

        //  ResponseEntity<String> notificationResponse =
        //         restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationDTO, String.class);
//
        //  if (!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
        //     //      System.out.println("erro ao enviar notificacao");
        //       throw new Exception("Servico de notificacao esta fora do ar");

        //    }
        System.out.println("erro ao enviar notificacao");
    }
}
