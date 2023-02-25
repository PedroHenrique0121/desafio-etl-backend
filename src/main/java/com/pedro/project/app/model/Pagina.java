package com.pedro.project.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Pagina {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "CONTEUDO_ORIGINAL", columnDefinition = "TEXT")
    private String conteudoOriginal;
    @Column(name = "CONTEUDO_MODIFICADO", columnDefinition = "TEXT")
    private String conteudoModificado;
    private boolean conteudoProcessado;

}
