package com.pedro.project.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PaginaOriginalRequestDTO {
    private Integer id;
    private String conteudoOriginal;
    private boolean conteudoProcessado;
}
