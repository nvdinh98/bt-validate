package com.codegym.kawa.repository;

import com.codegym.kawa.model.Customer;
import com.codegym.kawa.model.Provinces;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends PagingAndSortingRepository<Customer,Long> {

    Iterable<Customer> findAllByProvinces(Provinces provinces);

}
