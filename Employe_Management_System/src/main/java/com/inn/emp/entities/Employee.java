package com.inn.emp.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;




@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Employee implements UserDetails  {
     



	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE)
	 private Integer id;
	 
	 private String fname;
	 
	 private String lname;

	 private String dateOfBirth;
	 
	 private String gender;
	 
	 private String contact;
	 
	 private String email;
	 
	 private String password;
	 
	 private String address;

	 private String role ;
	 
	 private String jobDescription ;
	 
	 private String joining ;
	
	 
	 private String img ;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    Set<GrantedAuthority> authorities = new HashSet<>();
	    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.role));
	    System.out.println(authorities);
	    // Add more roles if needed
	    return authorities;
	}

	

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	 
}
