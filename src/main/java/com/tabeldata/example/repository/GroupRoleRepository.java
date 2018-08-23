package com.tabeldata.example.repository;

import com.tabeldata.example.model.GroupRoles;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface GroupRoleRepository extends PagingAndSortingRepository<GroupRoles, String> {

    List<GroupRoles> findAll();
}
