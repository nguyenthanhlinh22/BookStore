package org.example.payviewss.service;


import org.springframework.stereotype.Service;

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