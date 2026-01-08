package com.example.tempUser.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.tempUser.entity.TempUser;

public interface TempUserRepository extends JpaRepository<TempUser, Integer>{
	
	public Optional<TempUser> findByUsername(String username);
	
	@Modifying
	@Query(value = "DELETE from tamp_user WHERE username =:username",nativeQuery = true)
	public void deleteByUsername(@Param("username") String username);

}
