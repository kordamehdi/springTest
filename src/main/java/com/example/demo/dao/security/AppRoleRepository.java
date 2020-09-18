package com.example.demo.dao.security;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.security.AppRole;
public interface AppRoleRepository extends JpaRepository<AppRole,Integer>{
public AppRole findByRole(String role);
}