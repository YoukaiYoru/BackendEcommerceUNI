package org.backend.trabajo.backendproyecto.repository;

import org.backend.trabajo.backendproyecto.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Optional<Categoria> findCategoriaByCategoryDescription(String categoryDescription);
}
