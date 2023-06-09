package com.works.services;

import com.works.entities.ProJoinCat;
import com.works.projections.IProCatJoin;
import com.works.repositories.CustomerRepository;
import com.works.repositories.ProJoinCatRepository;
import com.works.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProJoinCatRepository proJoinCatRepository;
    final CustomerRepository customerRepository;

    public ResponseEntity proCatJoin( int page, String sortSt ) {
        //List<ProJoinCat> proJoinCats = proJoinCatRepository.fncProCatJoin();
        Sort sort = Sort.by(sortSt).descending();
        Pageable pageable = PageRequest.of(page, 10, sort);
        Page<IProCatJoin> proJoinCats = customerRepository.allProCat(pageable);
        return Util.responseTrue(proJoinCats);
    }

    public ResponseEntity proCatJoinID( Long cid ) {
        List<IProCatJoin> proJoinCats = customerRepository.allProCatID(cid);
        return Util.responseTrue(proJoinCats);
    }


}
