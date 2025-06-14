package com.exemplo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SistemaCursos {
    private CursoDAO cursoDAO;
    private JTable tabela;
    private DefaultTableModel modelo;

    public SistemaCursos() {
        cursoDAO = new CursoDAO();
        criarTela();
    }

    private void criarTela() {
        JFrame frame = new JFrame("Sistema de Cursos");
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 600);

        modelo = new DefaultTableModel(new String[]{"ID", "Nome", "Quantidade", "Fornecedor"}, 0);
        tabela = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabela);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel painel = new JPanel();
        JButton btnNovo = new JButton("Novo Curso");
        JButton btnEditar = new JButton("Editar Curso");
        JButton btnExcluir = new JButton("Excluir Curso");

        painel.add(btnNovo);
        painel.add(btnEditar);
        painel.add(btnExcluir);
        frame.add(painel, BorderLayout.SOUTH);

        btnNovo.addActionListener(e -> cadastrarCurso());
        btnEditar.addActionListener(e -> editarCurso());
        btnExcluir.addActionListener(e -> excluirCurso());

        atualizarTabela();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void cadastrarCurso() {
        String nome = JOptionPane.showInputDialog("Nome do Curso:");
        String quantidade = JOptionPane.showInputDialog("Quantidade:");
        String fornecedor = JOptionPane.showInputDialog("Fornecedor:");

        Curso curso = new Curso(nome, quantidade, fornecedor);
        cursoDAO.adicionarCurso(curso);
        atualizarTabela();
    }

    private void editarCurso() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada >= 0) {
            Long id = (Long) modelo.getValueAt(linhaSelecionada, 0);
            String nome = JOptionPane.showInputDialog("Novo Nome do Curso:", modelo.getValueAt(linhaSelecionada, 1));
            String quantidade = JOptionPane.showInputDialog("Nova Quantidade:", modelo.getValueAt(linhaSelecionada, 2));
            String fornecedor = JOptionPane.showInputDialog("Novo Fornecedor:", modelo.getValueAt(linhaSelecionada, 3));

            Curso curso = new Curso(nome, quantidade, fornecedor);
            curso.setId(id);
            cursoDAO.atualizarCurso(curso);
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um curso para editar.");
        }
    }

    private void excluirCurso() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada >= 0) {
            Long id = (Long) modelo.getValueAt(linhaSelecionada, 0);
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir o curso?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                cursoDAO.excluirCurso(id);
                atualizarTabela();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um curso para excluir.");
        }
    }

    private void atualizarTabela() {
        modelo.setRowCount(0);
        List<Curso> cursos = cursoDAO.listarCursos();
        for (Curso curso : cursos) {
            modelo.addRow(new Object[]{curso.getId(), curso.getNome(), curso.getQuantidade(), curso.getFornecedor()});
        }
    }

    public static void main(String[] args) {
        new SistemaCursos();
    }
}