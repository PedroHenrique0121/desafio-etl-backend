package com.pedro.project.app.repository;

import com.pedro.project.app.model.Pagina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaginaRepository extends JpaRepository<Pagina, Integer> {

}
