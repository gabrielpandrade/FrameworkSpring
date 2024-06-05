package com.trabalho.frameworkspring.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private long id_funcionario;

    @Column
    private long id_cliente;

    @Column
    private Timestamp data_pedido;

    @Column
    private Timestamp data_remessa;

    public Pedido() {
    }

    public Pedido(long id_funcionario, long id_cliente, Timestamp data_pedido, Timestamp data_remessa) {
        this.id_funcionario = id_funcionario;
        this.id_cliente = id_cliente;
        this.data_pedido = data_pedido;
        this.data_remessa = data_remessa;
    }

    public long getId() {
        return id;
    }

    public long getId_funcionario() {
        return id_funcionario;
    }

    public long getId_cliente() {
        return id_cliente;
    }

    public Timestamp getData_pedido() {
        return data_pedido;
    }

    public Timestamp getData_remessa() {
        return data_remessa;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId_funcionario(long id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public void setId_cliente(long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setData_pedido(Timestamp data_pedido) {
        this.data_pedido = data_pedido;
    }

    public void setData_remessa(Timestamp data_remessa) {
        this.data_remessa = data_remessa;
    }
}
