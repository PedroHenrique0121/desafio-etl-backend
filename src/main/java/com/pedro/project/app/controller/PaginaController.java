package com.pedro.project.app.controller;

import com.pedro.project.app.dto.request.PaginaModificadaRequestDTO;
import com.pedro.project.app.dto.request.PaginaOriginalRequestDTO;
import com.pedro.project.app.dto.response.PaginaModificadaResponseDTO;
import com.pedro.project.app.dto.response.PaginaOriginalResponseDTO;
import com.pedro.project.app.model.Pagina;
import com.pedro.project.app.model.Resultado;
import com.pedro.project.app.modelmapper.conversores.PaginaConversor;
import com.pedro.project.app.repository.PaginaRepository;
import com.pedro.project.app.service.PaginaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pagina")
@AllArgsConstructor
public class PaginaController {

    private PaginaService paginaService;
    private PaginaConversor paginaConversor;

    @PostMapping("/conteudo-original")
    public ResponseEntity<PaginaOriginalResponseDTO> salvarConteudoOriginal(@RequestBody PaginaOriginalRequestDTO dto) {
        Pagina pagina = this.paginaService.salvarConteudoOriginal(this.paginaConversor.PaginaOriginalRequestToModel(dto));
        PaginaOriginalResponseDTO paginaOriginalResponseDTO = this.paginaConversor.modelToPaginalOriginalResponseDTO(pagina);

        return ResponseEntity.ok(paginaOriginalResponseDTO);
    }

    @PostMapping("/conteudo-modificado/{id}")
    public ResponseEntity<PaginaModificadaResponseDTO> salvarConteudoModificado(@RequestBody PaginaModificadaRequestDTO dto, @PathVariable Integer id) {
        Pagina pagina = this.paginaService.salvarConteudoModificado(this.paginaConversor.PaginaModificadaRequestToModel(dto), id);
        PaginaModificadaResponseDTO  paginaModificadaResponseDTO  = this.paginaConversor.modelToPaginaModificadaResponseDTO(pagina);

        return ResponseEntity.ok(paginaModificadaResponseDTO);
    }

    @GetMapping("/conteudo-original/{id}")
    public ResponseEntity<PaginaOriginalResponseDTO> retornarConteudoOriginal(@PathVariable Integer id) {
        Pagina pagina = this.paginaService.retornarPagina(id);
        PaginaOriginalResponseDTO paginaOriginalResponseDTO = this.paginaConversor.modelToPaginalOriginalResponseDTO(pagina);

        return ResponseEntity.ok(paginaOriginalResponseDTO);
    }


    @GetMapping()
    public ResponseEntity<List<PaginaOriginalResponseDTO>> retornarTodas() {
        List<Pagina> paginas= this.paginaService.retornarTodasPaginas();
        List<PaginaOriginalResponseDTO> listDTO =this.paginaConversor.modelToPaginalOriginalResponseDTO(paginas);

        return ResponseEntity.ok(listDTO);
    }

    @GetMapping("/start")

    public  ResponseEntity<Resultado> startarServicoModificacaoPagina(){
        String status = this.paginaService.modificarPaginas();
        Resultado resultado= new Resultado();
        resultado.setStatus(status);
        return ResponseEntity.ok(resultado);
    }
}
