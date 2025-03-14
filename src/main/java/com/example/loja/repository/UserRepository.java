package com.example.loja.repository;

import com.example.loja.models.UserModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<UserModels, UUID> {
      Optional<UserModels> findByUsername(String username);

      String getUsername();

      CharSequence getPassword();
}