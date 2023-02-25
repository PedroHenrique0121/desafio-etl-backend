package com.pedro.project.app.modelmapper.conversores;

import com.pedro.project.app.dto.request.PaginaModificadaRequestDTO;
import com.pedro.project.app.dto.request.PaginaOriginalRequestDTO;
import com.pedro.project.app.dto.response.PaginaModificadaResponseDTO;
import com.pedro.project.app.dto.response.PaginaOriginalResponseDTO;
import com.pedro.project.app.model.Pagina;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PaginaConversor {

    private ModelMapper modelMapper;

    public Pagina PaginaOriginalRequestToModel(PaginaOriginalRequestDTO dto) {
        return this.modelMapper.map(dto, Pagina.class);
    }

    public Pagina PaginaOriginalResponseToModel(PaginaOriginalResponseDTO dto) {
        return this.modelMapper.map(dto, Pagina.class);
    }

    public Pagina PaginaModificadaRequestToModel(PaginaModificadaRequestDTO dto) {
        return this.modelMapper.map(dto, Pagina.class);
    }

    public PaginaOriginalResponseDTO modelToPaginalOriginalResponseDTO(Pagina model) {
        return this.modelMapper.map(model, PaginaOriginalResponseDTO.class);
    }

    public PaginaModificadaResponseDTO modelToPaginaModificadaResponseDTO(Pagina model) {
        return this.modelMapper.map(model, PaginaModificadaResponseDTO.class);
    }

    public List<PaginaOriginalResponseDTO> modelToPaginalOriginalResponseDTO(List<Pagina> list) {

        return list.stream().map(obj -> modelToPaginalOriginalResponseDTO(obj)).collect(Collectors.toList());
    }


}
