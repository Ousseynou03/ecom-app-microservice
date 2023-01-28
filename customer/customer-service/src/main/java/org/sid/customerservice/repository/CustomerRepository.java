package org.sid.customerservice.repository;

import org.sid.customerservice.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;


@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @RestResource(path = "/byName")
    Page<Customer> findByNameContains(@Param("keyword") String keyword, Pageable pageable);
}
