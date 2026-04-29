package com.rsai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.annotation.PostConstruct;

@Service
public class WhatsAppService {

    @Value("${twilio.sid}")
    private String sid;

    @Value("${twilio.token}")
    private String token;

    @Value("${twilio.whatsapp}")
    private String from;

    private boolean enabled;

    @PostConstruct
    public void init() {
        enabled = hasText(sid) && hasText(token) && hasText(from);
        if (!enabled) {
            System.out.println("Twilio credentials are not configured. WhatsApp notifications are disabled.");
            return;
        }
        Twilio.init(sid, token);
    }

    public void send(String to, String msg) {
        if (!enabled) {
            System.out.println("Skipping WhatsApp send because Twilio is disabled: " + to + "-" + msg);
            return;
        }
    	System.out.println(to+"-"+msg);
        Message.creator(
                new PhoneNumber("whatsapp:" + to),
                new PhoneNumber(from),
                msg
        ).create();
    }

    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }
}
