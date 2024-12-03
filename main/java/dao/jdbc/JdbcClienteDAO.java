package dao.jdbc;

import dao.ClienteDAO;
import dao.DAOException;
import modelo.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class JdbcClienteDAO implements ClienteDAO {

    public JdbcClienteDAO(Connection connection) {
        this.connection = connection;
    }

    private Connection connection;

    @Override
    public void salvar(Cliente cliente) {
        try {
            String sql = String.format("insert into cliente (nome, dataNascimento, email, telefone, cpf)" +
                            " values ('%s', '%s', '%s', '%s', '%s')",
                    cliente.getNome(), new SimpleDateFormat("yyyy-MM-dd").format(cliente.getDataNascimento()),
                    cliente.getEmail(), cliente.getTelefone(), cliente.getCpf());

            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.executeUpdate();

        } catch(SQLException e){
            throw new DAOException("Erro ao salvar Cliente", e);
        } finally {
            try {
                this.connection.close();
            } catch (Exception _) {}
        }
    }
}
