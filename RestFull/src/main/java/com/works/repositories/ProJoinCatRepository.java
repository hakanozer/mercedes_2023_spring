package com.works.repositories;

import com.works.entities.ProJoinCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProJoinCatRepository extends JpaRepository<ProJoinCat, Long> {

    @Query(value = "select P.PID, P.CID, P.TITLE, p.DETAIL, C.NAME from PUBLIC.PRODUCT as P inner join CATEGORY C on P.CID = C.CID", nativeQuery = true)
    List<ProJoinCat> fncProCatJoin();
}