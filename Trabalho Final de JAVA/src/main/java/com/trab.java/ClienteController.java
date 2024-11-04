package com.trab.java;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClienteController {

    private ClienteView view;
    private ClienteDAO clienteDAO;

    public ClienteController(ClienteView view) {
        this.view = view;
        this.clienteDAO = new ClienteDAO();
        configurarListeners();
    }

    public void iniciar() {
        carregarClientes();
        view.mostrarTela(); // Mostra a interface gráfica
    }

    // Configura os listeners dos botões
    private void configurarListeners() {
        view.adicionarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarCliente();
            }
        });

        view.editarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarCliente();
            }
        });

        view.deletarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarCliente();
            }
        });

        view.verPerfilListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verPerfil();
            }
        });
    }

    private void verPerfil() {
        int idClienteSelecionado = view.getClienteSelecionado();
        if (idClienteSelecionado != -1) {
            Cliente cliente = clienteDAO.obterClientePorId(idClienteSelecionado);
            if (cliente != null) {
                StringBuilder perfil = new StringBuilder();
                perfil.append("ID: ").append(cliente.getId()).append("\n");
                perfil.append("Nome: ").append(cliente.getNome()).append("\n");
                perfil.append("Email: ").append(cliente.getEmail()).append("\n");
                perfil.append("Telefone: ").append(cliente.getTelefone()).append("\n");
                perfil.append("Endereço: ").append(cliente.getEndereco()).append("\n");
                perfil.append("CPF: ").append(cliente.getCpf()).append("\n");
                perfil.append("Serviços:\n");

                if (cliente.getServico1() != null) {
                    perfil.append("- ").append(cliente.getServico1()).append("\n");
                }
                if (cliente.getServico2() != null) {
                    perfil.append("- ").append(cliente.getServico2()).append("\n");
                }
                if (cliente.getServico3() != null) {
                    perfil.append("- ").append(cliente.getServico3()).append("\n");
                }

                JOptionPane.showMessageDialog(view.getFrame(), perfil.toString(), "Perfil do Cliente", JOptionPane.INFORMATION_MESSAGE);
            } else {
                view.mostrarMensagem("Cliente não encontrado.");
            }
        } else {
            view.mostrarMensagem("Selecione um cliente para ver o perfil.");
        }
    }

    // Carrega a lista de clientes da base de dados e atualiza a view
    private void carregarClientes() {
        List<Cliente> clientes = clienteDAO.obterTodosClientes();
        view.atualizarListaClientes(clientes);
    }

    private void adicionarCliente() {
        Cliente novoCliente = view.getClienteForm("Adicionar Cliente");
        if (novoCliente != null) {
            clienteDAO.inserirCliente(novoCliente);
            carregarClientes(); // Atualiza a lista de clientes na view
            view.mostrarMensagem("Cliente adicionado com sucesso.");
        } else {
            view.mostrarMensagem("Erro ao adicionar cliente.");
        }
    }

    private void editarCliente() {
        int idClienteSelecionado = view.getClienteSelecionado();
        if (idClienteSelecionado != -1) {
            Cliente clienteExistente = clienteDAO.obterClientePorId(idClienteSelecionado);
            if (clienteExistente != null) {
                Cliente clienteEditado = view.getClienteForm("Editar Cliente", clienteExistente);
                if (clienteEditado != null) {
                    // Atualiza os dados do cliente no banco de dados
                    clienteDAO.alterarCliente(
                            clienteEditado.getId(),
                            clienteEditado.getNome(),
                            clienteEditado.getEmail(),
                            clienteEditado.getTelefone(),
                            clienteEditado.getEndereco(),
                            clienteEditado.getCpf(),
                            clienteEditado.getServico1(),
                            clienteEditado.getServico2(),
                            clienteEditado.getServico3()
                    );
                    carregarClientes(); // Atualiza a lista de clientes na view
                    view.mostrarMensagem("Cliente editado com sucesso.");
                } else {
                    view.mostrarMensagem("Erro ao editar cliente.");
                }
            } else {
                view.mostrarMensagem("Cliente não encontrado.");
            }
        } else {
            view.mostrarMensagem("Selecione um cliente para editar.");
        }
    }

    private void deletarCliente() {
        int idClienteSelecionado = view.getClienteSelecionado();
        if (idClienteSelecionado != -1) {
            // Exibe uma caixa de confirmação antes de excluir
            int confirmacao = JOptionPane.showConfirmDialog(view.getFrame(),
                    "Tem certeza que deseja deletar o cliente selecionado?",
                    "Confirmar Exclusão",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacao == JOptionPane.YES_OPTION) {
                clienteDAO.apagarCliente(idClienteSelecionado);
                carregarClientes(); // Atualiza a lista de clientes na view
                view.mostrarMensagem("Cliente deletado com sucesso.");
            }
        } else {
            view.mostrarMensagem("Selecione um cliente para deletar.");
        }
    }
}
