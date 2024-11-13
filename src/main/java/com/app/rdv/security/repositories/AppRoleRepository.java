package com.app.rdv.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.rdv.security.entity.AppRole;

public interface AppRoleRepository extends JpaRepository <AppRole, Integer> {

    
}