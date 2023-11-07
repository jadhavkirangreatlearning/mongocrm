package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Customer;
import com.csi.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<Customer> signUp(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerServiceImpl.signUp(customer));
    }

    @GetMapping("/signin/{custEmailId}/{custPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String custEmailId, @PathVariable String custPassword) {
        return ResponseEntity.ok(customerServiceImpl.signIn(custEmailId, custPassword));
    }

    @GetMapping("/findbyid/{custId}")
    public ResponseEntity<Optional<Customer>> findById(@PathVariable long custId) {
        return ResponseEntity.ok(customerServiceImpl.findById(custId));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity.ok(customerServiceImpl.findAll());
    }

    @GetMapping("/findbyname/{custName}")
    public ResponseEntity<List<Customer>> findByName(@PathVariable String custName) {
        return ResponseEntity.ok(customerServiceImpl.findByCustName(custName));
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Customer>> sortByName() {
        return ResponseEntity.ok(customerServiceImpl.findAll().stream().sorted(Comparator.comparing(Customer::getCustName)).toList());

    }

    @GetMapping("/sortbyaccbalance")
    public ResponseEntity<List<Customer>> sortByAccBalance() {
        return ResponseEntity.ok(customerServiceImpl.findAll().stream().sorted(Comparator.comparingDouble(Customer::getCustAccountBalance)).toList());

    }

    @GetMapping("/filterbyaccbalance/{custAccountBalance}")
    public ResponseEntity<List<Customer>> filterDataByAccBalance(@PathVariable double custAccountBalance) {
        return ResponseEntity.ok(customerServiceImpl.findAll().stream().filter(cust -> cust.getCustAccountBalance() >= custAccountBalance).toList());

    }

    @PutMapping("/update/{custId}")
    public ResponseEntity<Customer> update(@PathVariable long custId, @RequestBody Customer customer) {
        Customer customer1 = customerServiceImpl.findById(custId).orElseThrow(() -> new RecordNotFoundException("ID Does not exist"));

        customer1.setCustAddress(customer.getCustAddress());
        customer1.setCustDOB(customer.getCustDOB());
        customer1.setCustContactNumber(customer.getCustContactNumber());
        customer1.setCustName(customer.getCustName());
        customer1.setCustPassword(customer.getCustPassword());
        customer1.setCustEmailId(customer.getCustEmailId());
        customer1.setCustAccountBalance(customer.getCustAccountBalance());

        return ResponseEntity.ok(customerServiceImpl.updateData(customer1));
    }

    @DeleteMapping("/deletebyid/{custId}")
    public ResponseEntity<String> deleteById(@PathVariable long custId) {
        customerServiceImpl.deleteById(custId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll() {
        customerServiceImpl.deleteAll();

        return ResponseEntity.ok("All Data Deleted Successfully");
    }

}
