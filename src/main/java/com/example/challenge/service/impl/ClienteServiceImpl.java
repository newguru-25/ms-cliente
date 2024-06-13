package com.example.challenge.service.impl;

import com.example.challenge.controller.ClienteController;
import com.example.challenge.dto.ClienteDto;
import com.example.challenge.exception.NotFoundException;
import com.example.challenge.repository.ClienteRepository;
import com.example.challenge.service.ClienteService;
import java.util.Base64;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ClienteServiceImpl implements ClienteService {

  Logger logger = LoggerFactory.getLogger(ClienteController.class);

  public static final String NOT_FOUND = "no hay elementos";

  @Autowired
  ClienteRepository clienteRepository;

  public Mono<ClienteDto> findClient(String codigoUnico) {
    byte[] decodedBytes = Base64.getDecoder().decode(codigoUnico);
    String decodedCodigounico = new String(decodedBytes);
    logger.debug("codigoUnico => {}", decodedCodigounico);
    return clienteRepository.findById(Integer.valueOf(decodedCodigounico))
        .switchIfEmpty(Mono.error(new NotFoundException(NOT_FOUND)))
        .onErrorResume(Mono::error);
  }


  public Mono<ClienteDto> save(ClienteDto clienteDto) {
    logger.debug("requestBody => {}", clienteDto);
    return clienteRepository.findById(clienteDto.getId()).map(Optional::of)
        .defaultIfEmpty(Optional.empty())
        .flatMap(cliente1 -> {
          if (cliente1.isPresent()) {
            clienteDto.setId(cliente1.get().getId());
            return clienteRepository.save(clienteDto);
          }
          return Mono.error(new NotFoundException("No se encontro ningun registro para actualizar"));
        })
        .onErrorResume(Mono::error);
  }

}
