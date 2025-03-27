package com.pa2aresgi.pa2a.repository;

import com.pa2aresgi.pa2a.modele.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}
