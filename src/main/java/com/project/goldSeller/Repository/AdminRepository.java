package com.project.goldSeller.Repository;

import com.project.goldSeller.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository  extends JpaRepository<Admin,Long> {
    Admin findAdminByEmail(String email);

    boolean existsByEmail(String email);
}
