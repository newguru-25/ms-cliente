package com.example.challenge.controller;

import com.example.challenge.dto.ClienteDto;
import com.example.challenge.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/cliente")
public class ClienteController {

  @Autowired
  ClienteService clienteService;

  @GetMapping("/{codigoUnico}")
  public Mono<ClienteDto> getCliente(
      @PathVariable("codigoUnico") String codigoUnico
  ) {
    return clienteService.findClient(codigoUnico);
  }

  @PostMapping()
  public Mono<ClienteDto> postCliente(
      @RequestBody ClienteDto clienteDto
  ) {
    return clienteService.save(clienteDto);
  }


}
