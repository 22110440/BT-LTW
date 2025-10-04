package com.ltw.demo1;

import com.ltw.demo1.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Security 6!";
    }

    @GetMapping("/customers")
    public List<Customer> getCustomerList() {
        return List.of(
                new Customer(1, "Nguyen Van A"),
                new Customer(2, "Tran Thi B")
        );
    }
}
