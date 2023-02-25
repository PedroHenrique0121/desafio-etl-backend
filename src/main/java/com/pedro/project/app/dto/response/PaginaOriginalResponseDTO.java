package com.pedro.project.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginaOriginalResponseDTO {
    private Integer id;
    private String conteudoOriginal;
    private boolean conteudoProcessado;
}
