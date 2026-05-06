package com.app.controller;




import com.app.service.TwilioSmsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms") //%2B=+ in postMan
public class SmsController {

    private final TwilioSmsService smsService;

    public SmsController(TwilioSmsService smsService) {
        this.smsService = smsService;
    }

    @PostMapping("/send")
    public String sendSms(@RequestParam String phone, @RequestParam String message) {
        return smsService.sendSms(phone, message);
    }
}

