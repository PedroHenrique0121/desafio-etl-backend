package com.pedro.project.app.controller;

import com.pedro.project.app.model.Pagina;
import com.pedro.project.app.repository.PaginaRepository;
import com.pedro.project.app.service.PaginaService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pagina")
@AllArgsConstructor
public class PaginaController {

    private PaginaService paginaService;
    private PaginaRepository paginaRepository;
    @PostMapping("/conteudo-original")
    public ResponseEntity<Pagina> salvarConteudoOriginal(@RequestBody Pagina pagina) {
        return ResponseEntity.ok(this.paginaService.salvarConteudoOriginal(pagina));
    }
    @PostMapping("/conteudo-modificado/{id}")
    public ResponseEntity<Pagina> salvarConteudoModificado(@RequestBody Pagina pagina, @PathVariable Integer id) {
        return ResponseEntity.ok(this.paginaService.salvarConteudoModificado(pagina,id));
    }

    @GetMapping("/conteudo-original/{id}")
    public ResponseEntity<Pagina> retornarConteudoOriginal(@PathVariable Integer id) {
        return ResponseEntity.ok(this.paginaService.retornarPagina(id));
    }

    @GetMapping()
    public List<Pagina>retornarTodos() {
        return this.paginaRepository.findAll();
    }
}
