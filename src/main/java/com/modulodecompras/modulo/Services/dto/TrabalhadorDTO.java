package com.modulodecompras.modulo.Services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrabalhadorDTO {
    private String nome,cargo,departamento;
    private int matricula;
    private boolean trabalhadorNoturo;


}
