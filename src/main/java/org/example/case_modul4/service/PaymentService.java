package org.example.case_modul4.service;

import org.springframework.stereotype.Service;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;


@Service
public class PaymentService {

    public PaymentService() {

        Stripe.apiKey = "123123";
    }

    public Charge charge(String token, double amount) throws Exception {
        ChargeCreateParams params = ChargeCreateParams.builder()
                .setAmount((long) (amount * 100))  // Số tiền tính theo cent
                .setCurrency("usd")
                .setDescription("Book Purchase")
                .setSource(token)
                .build();

        return Charge.create(params);
    }
}