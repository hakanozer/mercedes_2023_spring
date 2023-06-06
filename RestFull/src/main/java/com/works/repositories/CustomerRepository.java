package com.works.repositories;

import com.works.entities.Customer;
import com.works.projections.ICustomer;
import com.works.projections.IProCatJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmailEqualsIgnoreCase(String email);

    Optional<Customer> findByEmailEqualsIgnoreCaseAndPasswordEquals(String email, String password);

    List<Customer> findByNameContainsOrEmailContainsAllIgnoreCase(String name, String email);

    @Query(value = "select P.PID, P.CID, P.TITLE, p.DETAIL, C.NAME from PUBLIC.PRODUCT as P inner join CATEGORY C on P.CID = C.CID", nativeQuery = true)
    List<IProCatJoin> allProCat();
    @Query(value = "select P.PID, P.CID, P.TITLE, p.DETAIL, C.NAME from PUBLIC.PRODUCT as P inner join CATEGORY C on P.CID = C.CID where C.CID = ?1", nativeQuery = true)
    List<IProCatJoin> allProCatID(Long cid);

    @Query(value = "select * from PUBLIC.CUSTOMER ", nativeQuery = true)
    List<ICustomer> allCustomer();

}