package com.project.goldSeller.RestController;

import com.project.goldSeller.Entity.Admin;
import com.project.goldSeller.Repository.AdminRepository;
import com.project.goldSeller.Service.adminService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@RestController
@CrossOrigin("*")
@RequestMapping(value="/admin")
public class AdminRestController {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired

    AdminRepository adminRepository;

    @Autowired
    adminService adminService;
    @RequestMapping(method = RequestMethod.POST )
    ResponseEntity<?> addAdmin (@RequestBody Admin admin){
        HashMap<String, Object> response = new HashMap<>();
        if(adminRepository.existsByEmail(admin.getEmail())){
            response.put("message", "email exist !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else{
            admin.setPassword(this.bCryptPasswordEncoder.encode(admin.getPassword()));
            Admin savedUser = adminRepository.save(admin);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);}

    }
    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public Admin updateAdmin(@PathVariable("id")Long id, @RequestBody Admin admin){
        admin.setPassword(this.bCryptPasswordEncoder.encode(admin.getPassword()));
        Admin savedUser = adminRepository.save(admin);

        Admin newAdmin = adminService.updateAdmin(admin);
        return newAdmin;
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )

    public void deletAdmin(@PathVariable("id") Long id){
        adminService.deletAdmin(id);

    }
    @RequestMapping(method = RequestMethod.GET )
    public List<Admin> displayAdmin(){
        return adminService.displayAdmin();

    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginAdmin(@RequestBody Admin admin) {
        System.out.println("in login-admin"+admin);
        HashMap<String, Object> response = new HashMap<>();

        Admin userFromDB = adminRepository.findAdminByEmail(admin.getEmail());
        System.out.println("userFromDB+admin"+userFromDB);
        if (userFromDB == null) {
            response.put("message", "Admin not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            boolean compare = this.bCryptPasswordEncoder.matches(admin.getPassword(), userFromDB.getPassword());
            System.out.println("compare"+compare);
            if (!compare) {
                response.put("message", "admin not found !");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }else
            {
                String token = Jwts.builder()
                        .claim("data", userFromDB)
                        .signWith(SignatureAlgorithm.HS256, "SECRET")
                        .compact();
                response.put("token", token);

                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

        }
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Optional<Admin> getAdminById(@PathVariable("id") Long id){

        Optional<Admin> admin = adminService.displayAdminByAd(id);
        return admin;
    }
}
