package com.example.Task.Management.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Task.Management.Entity.Lable;

@Repository
public interface LableRepository extends JpaRepository<Lable, Long> {

	Optional<Lable>findByLablename(String lableName);
	
}
