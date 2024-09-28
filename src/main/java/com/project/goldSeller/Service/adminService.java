package com.project.goldSeller.Service;

import com.project.goldSeller.Entity.Admin;

import java.util.List;
import java.util.Optional;

public interface adminService {
    Admin addAdmin(Admin admin);
    Admin updateAdmin(Admin admin);
    void deletAdmin (Long id);
    List <Admin>displayAdmin();
    Optional <Admin>displayAdminByAd(Long id);
}
