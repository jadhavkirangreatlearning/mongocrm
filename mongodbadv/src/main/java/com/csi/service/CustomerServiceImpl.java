package com.csi.service;

import com.csi.model.Customer;
import com.csi.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl {

    @Autowired
    private CustomerRepo customerRepoImpl;

    public Customer signUp(Customer customer) {
        return customerRepoImpl.save(customer);
    }

    public Optional<Customer> findById(long custId) {
        return customerRepoImpl.findById(custId);
    }

    public List<Customer> findAll() {
        return customerRepoImpl.findAll();
    }

    public boolean signIn(String custEmailId, String custPassword) {
        boolean flag = false;

        for (Customer customer : findAll()) {
            if (customer.getCustEmailId().equals(custEmailId)
                    && customer.getCustPassword().equals(custPassword)) {
                flag = true;
            }


        }

        return flag;
    }

    public Customer updateData(Customer customer){
        return customerRepoImpl.save(customer);
    }

    public void deleteById(long custId){
        customerRepoImpl.deleteById(custId);
    }

    public void deleteAll(){
        customerRepoImpl.deleteAll();
    }

    public List<Customer> findByCustName(String custName) {
        return customerRepoImpl.findByCustName(custName);
    }
}
