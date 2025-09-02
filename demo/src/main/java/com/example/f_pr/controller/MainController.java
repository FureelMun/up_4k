package com.example.f_pr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "home";
    }


    @GetMapping("/calculator")
    public String calculator() {
        return "calculator";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam("num1") double num1, 
                          @RequestParam("operation") String operation, 
                          @RequestParam("num2") double num2, 
                          Model model) {
        
        double result = 0;
        switch (operation) {
            case "add":
                result = num1 + num2;
                break;
            case "subtract":
                result = num1 - num2;
                break;
            case "multiply":
                result = num1 * num2;
                break;
                    case "divide":
            if (num2 != 0) {
                result = num1 / num2;
            } else {
                model.addAttribute("error", "Деление на ноль невозможно!");
                return "calculator";
            }
            break;
        }
        
        model.addAttribute("result", result);
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("operation", operation);
        
        return "result";
    }

    @GetMapping("/currency-converter")
    public String currencyConverter() {
        return "currency-converter";
    }

    @PostMapping("/convert-currency")
    public String convertCurrency(@RequestParam("fromCurrency") String fromCurrency, 
                                @RequestParam("toCurrency") String toCurrency, 
                                @RequestParam("amount") double amount, 
                                Model model) {
        double usdToEur = 0.85;
        double usdToRub = 95.0;
        double eurToUsd = 1.18;
        double eurToRub = 112.0;
        double rubToUsd = 0.0105;
        double rubToEur = 0.0089;
        
        double result = 0;
        String conversionInfo = "";
        if (fromCurrency.equals("USD") && toCurrency.equals("EUR")) {
            result = amount * usdToEur;
            conversionInfo = "USD → EUR";
        } else if (fromCurrency.equals("USD") && toCurrency.equals("RUB")) {
            result = amount * usdToRub;
            conversionInfo = "USD → RUB";
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("USD")) {
            result = amount * eurToUsd;
            conversionInfo = "EUR → USD";
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("RUB")) {
            result = amount * eurToRub;
            conversionInfo = "EUR → RUB";
        } else if (fromCurrency.equals("RUB") && toCurrency.equals("USD")) {
            result = amount * rubToUsd;
            conversionInfo = "RUB → USD";
        } else if (fromCurrency.equals("RUB") && toCurrency.equals("EUR")) {
            result = amount * rubToEur;
            conversionInfo = "RUB → EUR";
        } else if (fromCurrency.equals(toCurrency)) {
            result = amount;
            conversionInfo = fromCurrency + " → " + toCurrency;
        }

        model.addAttribute("fromCurrency", fromCurrency);
        model.addAttribute("toCurrency", toCurrency);
        model.addAttribute("amount", amount);
        model.addAttribute("result", result);
        model.addAttribute("conversionInfo", conversionInfo);
        return "conversion-result";
    }
} 