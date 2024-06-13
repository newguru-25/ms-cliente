package com.example.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table(name = "cliente")
public class ClienteDto {

  @Id
  private Integer id;
  private String nombre;
  private String apellidos;
  private String tipoDocumento;
  private String numeroDocumento;

}
