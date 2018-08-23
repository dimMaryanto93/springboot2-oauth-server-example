package com.tabeldata.example.repository;

import com.tabeldata.example.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {

    List<Role> findAll();
}
