package com.tabeldata.example.repository;

import com.tabeldata.example.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
}
