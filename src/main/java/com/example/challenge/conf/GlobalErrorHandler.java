package com.example.challenge.conf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
@Order(-2)
public class GlobalErrorHandler implements ErrorWebExceptionHandler {

  private ObjectMapper objectMapper;

  public GlobalErrorHandler(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {

    DataBufferFactory bufferFactory = serverWebExchange.getResponse().bufferFactory();
    if (throwable instanceof Exception) {
      serverWebExchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
      DataBuffer dataBuffer = null;
      try {
        dataBuffer = bufferFactory.wrap(objectMapper.writeValueAsBytes(new HttpError(throwable.getMessage(),"TT2")));
      } catch (JsonProcessingException e) {
        dataBuffer = bufferFactory.wrap("".getBytes());
      }
      serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
      return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));
    }

    serverWebExchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
    serverWebExchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
    DataBuffer dataBuffer = bufferFactory.wrap("Unknown error".getBytes());
    return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));
  }

  public class HttpError {

    private String message;
    private String codigoMensaje;

    HttpError(String message, String codigoMensaje) {
      this.message = message;
      this.codigoMensaje= codigoMensaje;
    }

    public String getMessage() {
      return message;
    }

    public String getCodigoMensaje() {
      return codigoMensaje;
    }
  }

}
