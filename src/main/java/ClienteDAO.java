import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void incluirCliente(Cliente cliente) {
        String sql = "INSERT INTO Clientes (nome, cpf, email, endereco, cep) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Conexao.getConnection(); // Para conectar
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCpf());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setString(4, cliente.getEndereco());
            pstmt.setString(5, cliente.getCep());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao incluir cliente: " + e.getMessage());
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar PreparedStatement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar Connection: " + e.getMessage());
                }
            }
        }
    }

    public void alterarCliente(Cliente cliente) {
        String sql = "UPDATE Clientes SET nome = ?, email = ?, endereco = ?, cep = ? WHERE cpf = ?";
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Conexao.getConnection(); // para usar Conexao
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getEndereco());
            pstmt.setString(4, cliente.getCep());
            pstmt.setString(5, cliente.getCpf());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao alterar cliente: " + e.getMessage());
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar PreparedStatement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar Connection: " + e.getMessage());
                }
            }
        }
    }

    public void excluirCliente(String cpf) {
        String sql = "DELETE FROM Clientes WHERE cpf = ?";
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Conexao.getConnection(); // Atualizado para usar Conexao
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, cpf);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar PreparedStatement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar Connection: " + e.getMessage());
                }
            }
        }
    }

    public List<Cliente> listarClientes() {
        String sql = "SELECT * FROM Clientes";
        List<Cliente> clientes = new ArrayList<Cliente>();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = Conexao.getConnection(); // ara usar Conexao
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setCep(rs.getString("cep"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar ResultSet: " + e.getMessage());
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar PreparedStatement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar Connection: " + e.getMessage());
                }
            }
        }
        return clientes;
    }
}
