package org.example;

import dao.ClienteDAO;
import dao.DAOFactory;
import modelo.Cliente;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.util.Date;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        Cliente cliente = new Cliente();

        BasicConfigurator.configure();

        try {
            cliente.setNome(obterEntrada("Nome do cliente (apenas letras)", "nome"));
            cliente.setDataNascimento(obterData("Data de nascimento (formato: dd/MM/yyyy)"));
            cliente.setEmail(obterEntrada("E-mail valido", "email"));
            cliente.setTelefone(obterEntrada("Telefone (somente numeros)", "telefone"));
            cliente.setCpf(obterEntrada("CPF", "cpf"));

            ClienteDAO clienteDAO = DAOFactory.getDAOFactory().getClienteDAO();
            logger.info("Salvando cliente ...");
            clienteDAO.salvar(cliente);
            logger.info("Cliente salvo com sucesso!");
            JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!");

        } catch (Exception e) {
            logger.error("Erro ao salvar cliente: ", e);
            JOptionPane.showMessageDialog(null, "Erro ao salvar cliente: " + e.getMessage());
        }
    }

    private static String obterEntrada(String mensagem, String tipoValidacao) throws Exception {
        while (true) {
            String entrada = JOptionPane.showInputDialog(null, mensagem, JOptionPane.QUESTION_MESSAGE);
            try {
                Verificacoes.validar(entrada, tipoValidacao);
                return entrada;
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    private static Date obterData(String mensagem) throws Exception {
        while (true) {
            String entrada = JOptionPane.showInputDialog(null, mensagem, JOptionPane.QUESTION_MESSAGE);
            if (entrada == null) {
                throw new Exception("Operação cancelada pelo usuário.");
            }
            try {
                return Verificacoes.validarData(entrada);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }
}
