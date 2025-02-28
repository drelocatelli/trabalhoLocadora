package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.Cliente;

public class ClienteService {
    public String path = "clientes.txt";
    private static Scanner scan = new Scanner(System.in);

    public String consultar() {
        ArrayList<Cliente> clientes = get();
        StringBuilder sb = new StringBuilder();

        for (Cliente cliente : clientes) {
            sb.append(cliente).append("\n");
        }

        return sb.toString();
    }

    public ArrayList<Cliente> get() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile("codigo=(.*?), nome=(.*?), genero=(.*?), cpf=(.*?), endereco=(.*?)]");
                Matcher matcher = pattern.matcher(linha);

                if (matcher.find()) {
                    Cliente cliente = new Cliente();
                    cliente.setCodigo(matcher.group(1));
                    cliente.setNome(matcher.group(2));
                    cliente.setGenero(matcher.group(3));
                    cliente.setCpf(matcher.group(4));
                    cliente.setEndereco(matcher.group(5));
                    clientes.add(cliente);
                }

            }
        } catch (IOException ex) {
            System.out.println("Ocorreu um erro a contabilizar clientes: " + ex.getMessage());
        }

        return clientes;
    }

    public void cadastrar() {
        String opcao;
        do {
            System.out.println("_____________________________________________\n");
            System.out.println("      Cadastrando cliente da locadora   ");
            System.out.println("_____________________________________________\n\n");

            Cliente cliente = new Cliente();

            System.out.printf("Informe o codigo do cliente que sera cadastrado\n");
            String codigo = scan.nextLine();
            cliente.setCodigo(codigo);

            System.out.printf("Informe o nome do cliente:\n");
            String nome = scan.nextLine();
            cliente.setNome(nome);

            System.out.printf("Informe o gênero do cliente:\n");
            String genero = scan.nextLine();
            cliente.setGenero(genero);

            System.out.printf("Informe o CPF do cliente:\n");
            String cpf = scan.nextLine();
            cliente.setCpf(cpf);

            System.out.printf("Informe o endereco do cliente :\n");
            String endereco = scan.nextLine();
            cliente.setEndereco(endereco);

            FileService<Cliente> fileService = new FileService(this.path);
            fileService.insert(cliente);

            System.out.println("_____________________________________________\n");
            System.out.println("Deseja cadastrar um novo cliente S para sim ou N para Nao:  ");
            System.out.println("_____________________________________________");

            opcao = scan.nextLine().toLowerCase();

        } while (opcao.equals("s"));
    }

    public ArrayList<Cliente> search(String nome) {
        var clientes = get();
        ArrayList<Cliente> clientesEncontrados = new ArrayList<>();

        for (Cliente cliente : clientes) {
            if (cliente.getNome().toLowerCase().contains(nome.toLowerCase())) {
                clientesEncontrados.add(cliente);
            }
        }

        return clientesEncontrados;
    }

    public Cliente update(int clientIdx, Cliente cliente) {
        System.out.println("Digite o número da opção que deseja alterar:");
        String opcao = scan.nextLine();

        System.out.println("Digite o novo valor:");

        Cliente newCliente = new Cliente(cliente);
        
        switch (opcao) {
            case "1":
                newCliente.setCodigo(scan.nextLine());
                break;
            case "2":
                newCliente.setNome(scan.nextLine());
                break;
            case "3":
                newCliente.setGenero(scan.nextLine());
                break;
            case "4":
                newCliente.setCpf(scan.nextLine());
                break;
            case "5":
                newCliente.setEndereco(scan.nextLine());
                break;
            default:
                System.out.println("Opção inválida!");
        }

        FileService<Cliente> fileService = new FileService(this.path);
        fileService.updateItem(cliente, newCliente);

        return newCliente;
    }
}
