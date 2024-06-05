package com.trabalho.frameworkspring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pedido_produto")
public class PedidoProduto {

    @Id
    @ManyToOne
    private Pedido pedido;

    @Id
    @ManyToOne
    private Produto produto;

    @Column
    private int quantidade;

    @Column
    private float preco_unitario;

    @Column
    private float desconto;
}
