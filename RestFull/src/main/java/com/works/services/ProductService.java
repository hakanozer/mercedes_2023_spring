package com.works.services;

import com.works.entities.ProJoinCat;
import com.works.projections.IProCatJoin;
import com.works.repositories.CustomerRepository;
import com.works.repositories.ProJoinCatRepository;
import com.works.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProJoinCatRepository proJoinCatRepository;
    final CustomerRepository customerRepository;

    public ResponseEntity proCatJoin() {
        //List<ProJoinCat> proJoinCats = proJoinCatRepository.fncProCatJoin();
        List<IProCatJoin> proJoinCats = customerRepository.allProCat();
        return Util.responseTrue(proJoinCats);
    }

    public ResponseEntity proCatJoinID( Long cid ) {
        List<IProCatJoin> proJoinCats = customerRepository.allProCatID(cid);
        return Util.responseTrue(proJoinCats);
    }


}
