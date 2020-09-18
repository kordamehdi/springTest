package com.example.demo.entities.security;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import com.example.demo.entities.Article;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="PROJET_USER_ROLE")
@NoArgsConstructor
@Getter
@Setter
public class ArticleAppUserRoleAsso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private AppUser user;

	@ManyToOne
	private Article article;

	@ManyToOne
	private AppRole role;

}
