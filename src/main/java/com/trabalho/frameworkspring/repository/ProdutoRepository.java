package com.trabalho.frameworkspring.repository;

import com.trabalho.frameworkspring.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findAllByCodigo(String codigo);
}
