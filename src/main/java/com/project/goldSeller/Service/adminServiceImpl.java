package com.project.goldSeller.Service;

import com.project.goldSeller.Entity.Admin;
import com.project.goldSeller.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class adminServiceImpl implements adminService{
    @Autowired
    AdminRepository adminRepository;
    @Override
    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public void deletAdmin(Long id) {
    adminRepository.deleteById(id);
    }

    @Override
    public List<Admin> displayAdmin() {
        return adminRepository.findAll();
    }

    @Override
    public Optional<Admin> displayAdminByAd(Long id) {
        return adminRepository.findById(id);
    }
}
