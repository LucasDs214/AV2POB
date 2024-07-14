import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha uma opção:");
        System.out.println("1. Incluir Cliente");
        System.out.println("2. Alterar Cliente");
        System.out.println("3. Excluir Cliente");
        System.out.println("4. Listar Clientes");
        System.out.println("5. Incluir Clientes de Arquivo");
        System.out.println("6. Buscar ID por CPF");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha

        switch (opcao) {
            case 1:
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Endereço: ");
                String endereco = scanner.nextLine();
                System.out.print("CEP: ");
                String cep = scanner.nextLine();

                Cliente novoCliente = new Cliente(nome, cpf, email, endereco, cep);
                clienteDAO.incluirCliente(novoCliente);
                System.out.println("Cliente incluído com sucesso!");
                break;

            case 2:
                System.out.print("CPF do Cliente a alterar: ");
                cpf = scanner.nextLine();
                System.out.print("Novo Nome: ");
                nome = scanner.nextLine();
                System.out.print("Novo Email: ");
                email = scanner.nextLine();
                System.out.print("Novo Endereço: ");
                endereco = scanner.nextLine();
                System.out.print("Novo CEP: ");
                cep = scanner.nextLine();

                Cliente clienteAlterado = new Cliente(nome, cpf, email, endereco, cep);
                clienteDAO.alterarCliente(clienteAlterado);
                System.out.println("Cliente alterado com sucesso!");
                break;

            case 3:
                System.out.print("CPF do Cliente a excluir: ");
                cpf = scanner.nextLine();
                clienteDAO.excluirCliente(cpf);
                System.out.println("Cliente excluído com sucesso!");
                break;

            case 4:
                List<Cliente> clientes = clienteDAO.listarClientes();
                for (Cliente cliente : clientes) {
                    System.out.println(cliente.getNome() + " - " + cliente.getCpf() + " - " + cliente.getEmail() + " - " + cliente.getEndereco() + " - " + cliente.getCep());
                }
                break;

                case 5:
                System.out.print("Caminho do arquivo de carga: ");
                String filePath = scanner.nextLine();
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader(filePath));
                    String linha;
                    br.readLine(); // Ignorar a primeira linha (cabeçalho)
                    while ((linha = br.readLine()) != null) {
                        String[] dados = linha.split(",");
                        Cliente cliente = new Cliente(dados[0], dados[1], dados[2], dados[3], dados[4]);
                        clienteDAO.incluirCliente(cliente);
                    }
                    System.out.println("Clientes incluídos a partir do arquivo!");
                } catch (IOException e) {
                    System.out.println("Erro ao ler arquivo: " + e.getMessage());
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            System.out.println("Erro ao fechar o BufferedReader: " + e.getMessage());
                        }
                    }
                }
                break;


            /*case 6:
                System.out.print("CPF do Cliente: ");
                cpf = scanner.nextLine();
                int id = clienteDAO.obterIdPorCpf(cpf);
                if (id != -1) {
                    System.out.println("ID do Cliente: " + id);
                } else {
                    System.out.println("Cliente não encontrado.");
                }
                break;

            default:
                System.out.println("Opção inválida!");*/
        }
        scanner.close();
    }
}
