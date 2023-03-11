package com.redacode;

//now in order for this to be a springboot app we have to add few things:

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
//so we can be able to have methods that we can expose as rest endpoints to create APIs
//this indicates that the class is a controller and all the methods will return a JSON response
@RestController
@RequestMapping("/api/customer")
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
    record newCustomerRequest(
            String name,
            String email,
            Integer age
    ){

    }
    @PostMapping
    public void createCustomer(@RequestBody newCustomerRequest request){
        System.out.println("heeeeeeeeyn");
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }
    record oldCustomer(
            String name,
            String email,
            Integer age
    ){

    }
    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody oldCustomer request){
       Customer customer = customerRepository.findById(id).get();
        System.out.println(customer);
        if (request.name() != null)
            customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }
}
