package com.example.stripepayment.controller;

import com.example.stripepayment.data.ChargeRequest;
import com.example.stripepayment.aspects.LogExecutionTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckoutController {

    @Value("${stripe.public.key}")
    private String stripePublicKey;

    @LogExecutionTime
    @RequestMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("amount", 50 * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "checkout";
    }
}
