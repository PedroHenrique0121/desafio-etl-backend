package com.pedro.project.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PaginaModificadaResponseDTO {
    private Integer id;
    private String conteudoModificado;
}
