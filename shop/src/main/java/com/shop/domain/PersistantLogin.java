package com.shop.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersistantLogin {
	
	@Id
	@Column(length = 64)
	private String series;
	
	@Column(length = 64)
	private String username;
	
	@Column(length = 64)
	private String token;
	
	@CreationTimestamp
	private LocalDateTime lastUsed;
}
