package com.tabeldata.example.repository;

import com.tabeldata.example.model.GroupUserRoles;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface GroupUserRolesRepository extends PagingAndSortingRepository<GroupUserRoles, String> {

    List<GroupUserRoles> findByUserUsername(String username);

    List<GroupUserRoles> findAll();
}
