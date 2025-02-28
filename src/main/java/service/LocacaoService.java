package service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.Cliente;
import entity.Filme;
import entity.Locacao;

public class LocacaoService {
      public String path = "locacoes.txt";
    private static Scanner scan = new Scanner(System.in);

    private FilmeService filmeService = new FilmeService();
    private ClienteService clienteService = new ClienteService();

    public String consultar() {
        ArrayList<Locacao> locacoes = getLocacoes();
        StringBuilder sb = new StringBuilder();

        for (Locacao locacao : locacoes) {
            sb.append(locacao).append("\n");
        }

        return sb.toString();
    }

    public ArrayList<Locacao> getLocacoes() {
        ArrayList<Locacao> locacoes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Pattern clientePattern = Pattern.compile("Cliente \\[ codigo=(.*?), nome=(.*?), cpf=(.*?) \\]");
                Matcher clienteMatcher = clientePattern.matcher(linha);

                Pattern filmePattern = Pattern.compile("Filme \\[ codigo=(.*?), titulo=(.*?), genero=(.*?) \\]");
                Matcher filmeMatcher = filmePattern.matcher(linha);

                Cliente cliente = new Cliente();
                Filme filme = new Filme();
                Locacao locacao = new Locacao();

                if (clienteMatcher.find()) {
                    cliente.setCodigo(clienteMatcher.group(1));
                    cliente.setNome(clienteMatcher.group(2));
                    cliente.setCpf(clienteMatcher.group(3));
                }

                if(filmeMatcher.find()) {
                    filme.setCodigo(filmeMatcher.group(1));
                    filme.setTitulo(filmeMatcher.group(2));
                    filme.setGenero(filmeMatcher.group(3));
                }

                locacao.setCliente(cliente);
                locacao.setFilme(filme);

                locacoes.add(locacao);
                
            }

        } catch (IOException ex) {
            System.out.println("Ocorreu um erro a contabilizar locacoes: " + ex.getMessage());
        }

        return locacoes;
    }

    public int quantidade() {
        int linhas = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (reader.readLine() != null) {
                linhas++;
            }
        } catch (IOException ex) {
            System.out.println("Ocorreu um erro a contabilizar locações: " + ex.getMessage());
        }

        return linhas;
    }

    public void cadastrar() {
            String opcao;
            do {

                System.out.println("_____________________________________________\n");
                System.out.println("      Cadastrando Locacao   ");
                System.out.println("_____________________________________________\n\n");

                System.out.println("Selecione o cliente");
                ArrayList<Cliente> clientes = clienteService.get();

                for (int i = 0; i < clientes.size(); i++) {
                    System.out.println(i + " - " + clientes.get(i).getNome());
                }

                Locacao locacao = new Locacao();

                System.out.println("Escolha o cliente: ");
                int opcaoCliente = scan.nextInt();
                locacao.setCliente(clientes.get(opcaoCliente));

                System.out.println("Selecione o filme");
                ArrayList<Filme> filmes = filmeService.get();

                for (int i = 0; i < filmes.size(); i++) {
                    System.out.println(i + " - " + filmes.get(i).getTitulo());
                }

                System.out.println("Escolha o filme: ");
                int opcaoFilme = scan.nextInt();
                Filme filmeLocado = filmes.get(opcaoFilme);
                
                if(filmeLocado.getQtdEstoque() == 0) {
                    System.out.println("Filme sem estoque");
                    return;
                }

                if(filmeLocado.getQuantidade() == 0) {
                    System.out.println("Não há mais cópias disponíveis para locação");
                    return;
                }

                filmeLocado.setQuantidade(filmeLocado.getQuantidade() - 1);
                filmeService.alterarStatusLocacaoPorIndice(opcaoFilme, true);
                filmeService.alterarQuantidadePorIndice(opcaoFilme, filmeLocado.getQuantidade());

                locacao.setFilme(filmeLocado);

                FileService<Locacao> fileService = new FileService(this.path);
                fileService.insert(locacao);

                System.out.println("_____________________________________________\n");
                System.out.println("Deseja cadastrar uma nova locação S para sim ou N para Nao:  ");
                System.out.println("_____________________________________________");

                scan.nextLine();
                opcao = scan.nextLine().toLowerCase();

            } while (opcao.equals("s"));

    }
}
