package com.trab.java;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Connection conexao;

    public ClienteDAO() {
        try {
            // Conectar ao banco SQLite
            String url = "jdbc:sqlite:clientes.db";
            conexao = DriverManager.getConnection(url);
            criarTabelaClientes();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    // Método para criar a tabela 'clientes' caso não exista
    private void criarTabelaClientes() {
        String sql = "CREATE TABLE IF NOT EXISTS clientes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL, " +
                "email TEXT NOT NULL, " +
                "telefone TEXT NOT NULL, " +
                "endereco TEXT NOT NULL, " +
                "cpf TEXT NOT NULL, " +
                "servico1 TEXT, " +
                "servico2 TEXT, " +
                "servico3 TEXT)";
        try (Statement stmt = conexao.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    // Método para inserir um novo cliente no banco de dados
    public void inserirCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, email, telefone, endereco, cpf, servico1, servico2, servico3) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getTelefone());
            pstmt.setString(4, cliente.getEndereco());
            pstmt.setString(5, cliente.getCpf());
            pstmt.setString(6, cliente.getServico1());
            pstmt.setString(7, cliente.getServico2());
            pstmt.setString(8, cliente.getServico3());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir cliente: " + e.getMessage());
        }
    }

    // Método para alterar um cliente existente no banco de dados
    public void alterarCliente(int id, String nome, String email, String telefone, String endereco, String cpf,
                               String servico1, String servico2, String servico3) {
        String sql = "UPDATE clientes SET nome = ?, email = ?, telefone = ?, endereco = ?, cpf = ?, servico1 = ?, servico2 = ?, servico3 = ? WHERE id = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setString(3, telefone);
            pstmt.setString(4, endereco);
            pstmt.setString(5, cpf);
            pstmt.setString(6, servico1);
            pstmt.setString(7, servico2);
            pstmt.setString(8, servico3);
            pstmt.setInt(9, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    // Método para deletar um cliente pelo ID
    public void apagarCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar cliente: " + e.getMessage());
        }
    }

    public List<Cliente> obterTodosClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT id, nome, email, telefone, endereco, cpf, servico1, servico2, servico3 FROM clientes";
        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setServico1(rs.getString("servico1"));
                cliente.setServico2(rs.getString("servico2"));
                cliente.setServico3(rs.getString("servico3"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter clientes: " + e.getMessage());
        }
        return clientes;
    }

    // Método para obter um cliente específico pelo ID
    public Cliente obterClientePorId(int id) {
        String sql = "SELECT id, nome, email, telefone, endereco, cpf, servico1, servico2, servico3 FROM clientes WHERE id = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("telefone"),
                            rs.getString("endereco"),
                            rs.getString("cpf"),
                            rs.getString("servico1"),
                            rs.getString("servico2"),
                            rs.getString("servico3"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter cliente por ID: " + e.getMessage());
        }
        return null;
    }
}
