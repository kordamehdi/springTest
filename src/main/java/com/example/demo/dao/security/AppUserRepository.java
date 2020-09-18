package com.example.demo.dao.security;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.security.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
	public AppUser findByUsername(String username);



}