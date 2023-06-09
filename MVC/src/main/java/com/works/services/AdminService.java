package com.works.services;

import com.works.entities.Admin;
import com.works.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    final AdminRepository adminRepository;
    final HttpServletRequest req;

    public boolean login(Admin admin) {
        Optional<Admin> optionalAdmin = adminRepository.findByEmailEqualsIgnoreCaseAndPasswordEquals(admin.getEmail(), admin.getPassword());
        if (optionalAdmin.isPresent()) {
            Admin adm = optionalAdmin.get();
            req.getSession().setAttribute("admin", adm);
            return true;
        }
        return false;
    }

    public void logout() {
        req.getSession().removeAttribute("admin");
    }

}
