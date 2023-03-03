package com.redacode;

//now in order for this to be a springboot app we have to add few things:

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
//so we can be able to have methods that we can expose as rest endpoints to create APIs
//this indicates that the class is a controller and all the methods will return a JSON response
@RestController
@RequestMapping("api/customers")
public class main {

    private final CustomerRepository customerRepository;

    public main(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }
    public static void main(String[] args) {
        SpringApplication.run(main.class, args);
    }

    @GetMapping
    public List<Customer> getCustomer(){
        return customerRepository.findAll();
    }
}
