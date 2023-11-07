package com.csi.repo;

import com.csi.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends MongoRepository<Customer, Long> {

    public List<Customer> findByCustName(String custName);
}
