package com.trabalho.frameworkspring.repository;

import com.trabalho.frameworkspring.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findAllById_funcionario(long id_funcionario);

    List<Pedido> findAllById_cliente(long id_cliente);
}
