package com.codegym.kawa.service.customer;

import com.codegym.kawa.model.Customer;
import com.codegym.kawa.model.Provinces;
import com.codegym.kawa.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService  extends IGeneralService<Customer> {

    Page<Customer> findAll(Pageable pageable);

}
