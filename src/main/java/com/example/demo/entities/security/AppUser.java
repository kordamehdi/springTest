package com.example.demo.entities.security;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.demo.entities.Affectation;
import com.example.demo.entities.Comite;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class AppUser {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String username;


	private String password;
	@OneToMany(mappedBy = "utilisateur")
	@JsonIgnore
	private List<Affectation> affectaions;
	@ManyToOne
	private Comite comite;


	@OneToMany(mappedBy = "user" , cascade=CascadeType.REMOVE)
	List<ArticleAppUserRoleAsso> roles;

}
