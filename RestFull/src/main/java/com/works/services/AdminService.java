package com.works.services;

import com.works.entities.Admin;
import com.works.entities.Role;
import com.works.repositories.AdminRepository;
import com.works.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService implements UserDetailsService {

    final AdminRepository adminRepository;
    final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> optionalAdmin = adminRepository.findByUsernameEqualsIgnoreCase(username);
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            return new User(
                    admin.getUsername(),
                    admin.getPassword(),
                    admin.getEnable(),
                    true,
                    true,
                    true,
                    parseRoles( admin.getRoles() )
            );
        }else {
            throw new UsernameNotFoundException("Not Found");
        }
    }

    private Collection<? extends GrantedAuthority> parseRoles(List<Role> roles) {
        List<GrantedAuthority> ls = new ArrayList<>();
        for( Role role : roles ) {
            ls.add( new SimpleGrantedAuthority(role.getName()));
        }
        return ls;
    }

    public ResponseEntity register(Admin admin) {
        Optional<Admin> optionalAdmin = adminRepository.findByUsernameEqualsIgnoreCase(admin.getUsername());
        if(optionalAdmin.isPresent()) {
            return Util.responseFalse("This username in use!", HttpStatus.BAD_REQUEST);
        }else {
            admin.setPassword( passwordEncoder.encode(admin.getPassword()) );
            adminRepository.save(admin);
            return Util.responseTrue(admin);
        }
    }


}
