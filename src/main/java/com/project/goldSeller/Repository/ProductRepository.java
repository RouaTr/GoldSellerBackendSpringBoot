package com.project.goldSeller.Repository;

import com.project.goldSeller.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
