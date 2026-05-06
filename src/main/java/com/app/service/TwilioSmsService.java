package com.app.service;


import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsService {

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    public String sendSms(String toPhoneNumber, String messageBody) {
        try {
            Message message = Message.creator(
                    new PhoneNumber(toPhoneNumber),
                    new PhoneNumber(twilioPhoneNumber),
                    messageBody
            ).create();

            return "Message sent successfully with SID: " + message.getSid();
        } catch (Exception e) {
            return "Error sending message: " + e.getMessage();
        }
    }
}

