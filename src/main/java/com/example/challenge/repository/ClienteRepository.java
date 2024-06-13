package com.example.challenge.repository;

import com.example.challenge.dto.ClienteDto;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends R2dbcRepository<ClienteDto, Integer> {



}
