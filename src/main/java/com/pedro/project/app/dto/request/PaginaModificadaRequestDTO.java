package com.pedro.project.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginaModificadaRequestDTO {
    private Integer id;
    private String  conteudoModificado;
    private String conteudoProcessado;

}
