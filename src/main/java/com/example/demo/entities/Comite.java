package com.example.demo.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.demo.entities.security.AppUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comite {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO )
	private Long id;
	private String nom;
	@OneToMany(mappedBy = "comite")
	private List<AppUser> utilisateurs;
	
	
	
	
	
}
