package com.app.rdv.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.rdv.security.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Integer > {

    
}