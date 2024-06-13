package com.example.challenge.service;

import com.example.challenge.dto.ClienteDto;
import reactor.core.publisher.Mono;

public interface ClienteService {

  Mono<ClienteDto> save(ClienteDto clienteDto);
  Mono<ClienteDto> findClient(String codigoUnico);

}
