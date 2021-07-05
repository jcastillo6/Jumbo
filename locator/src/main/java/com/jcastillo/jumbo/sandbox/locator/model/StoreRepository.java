package com.jcastillo.jumbo.sandbox.locator.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.jcastillo.jumbo.sandbox.locator.entity.Store;

@Repository
public interface StoreRepository extends PagingAndSortingRepository<Store,Long> {
    public Page<Store> findAll(Pageable pageable);
}
