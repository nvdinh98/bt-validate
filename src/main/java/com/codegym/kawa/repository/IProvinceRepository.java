package com.codegym.kawa.repository;

import com.codegym.kawa.model.Customer;
import com.codegym.kawa.model.Provinces;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvinceRepository extends PagingAndSortingRepository<Provinces,Long> {

}
