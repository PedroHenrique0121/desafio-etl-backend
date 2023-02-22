package com.pedro.project.app.service;

import com.pedro.project.app.model.Pagina;
import com.pedro.project.app.repository.PaginaRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaginaService {

    private PaginaRepository paginaRepository;

    public Pagina salvarConteudoOriginal(Pagina pagina) {
        return this.paginaRepository.save(pagina);
    }

    public Pagina salvarConteudoModificado(Pagina pagina, Integer id) {
        return this.paginaRepository.findById(id)
                .map(obj -> {
                    obj.setConteudoModificado(pagina.getConteudoModificado());
                    return this.paginaRepository.save(obj);
                })
                .orElseThrow(() -> {
                            return new RuntimeException("Não foi possivel salvar o conteudo modificado");
                        }
                );
    }

    public Pagina retornarPagina(Integer id) {
        return this.paginaRepository.findById(id)
                .orElseThrow(
                        () -> {
                            return new RuntimeException("pagina não encontrada");
                        }
                );
    }
}
