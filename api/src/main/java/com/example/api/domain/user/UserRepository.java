package com.example.api.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<AppUser,Long>{
    UserDetails findByUsername(String username);
}
