package com.exemplo;

import javax.persistence.*;

@Entity
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String quantidade;
    private String fornecedor;

    // Construtor padrão
    public Curso() {}

    public Curso(String nome, String quantidade, String fornecedor) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getQuantidade() { return quantidade; }
    public void setQuantidade(String quantidade) { this.quantidade = quantidade; }
    public String getFornecedor() { return fornecedor; }
    public void setFornecedor(String fornecedor) { this.fornecedor = fornecedor; }
}