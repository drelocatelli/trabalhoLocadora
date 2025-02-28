package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.FilmeController;
import entity.Filme;

public class FilmeService {
    private static Scanner scan = new Scanner(System.in);
    private PrintWriter printWriter = null;
    private FileWriter fileWriter = null;
    public String path = "filmes.txt";

    public ArrayList<Filme> get() {
        ArrayList<Filme> filmes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile(
                        "codigo=(.*?), titulo=(.*?), genero=(.*?), quantidade=(\\d+), qtdEstoque=(\\d+), isLocado=(true|false)]");
                Matcher matcher = pattern.matcher(linha);

                if (matcher.find()) {
                    Filme filme = new Filme();
                    filme.setCodigo(matcher.group(1));
                    filme.setTitulo(matcher.group(2));
                    filme.setGenero(matcher.group(3));
                    filme.setQuantidade(Long.parseLong(matcher.group(4)));
                    filme.setQtdEstoque(Long.parseLong(matcher.group(5)));
                    filme.setIsLocado(Boolean.parseBoolean(matcher.group(6)));

                    filmes.add(filme);
                }

            }

        } catch (IOException ex) {
            System.out.println("Ocorreu um erro a contabilizar filmes: " + ex.getMessage());
        }

        return filmes;
    }

    public String show() {
        ArrayList<Filme> filmes = get();
        StringBuilder sb = new StringBuilder();

        for (Filme filme : filmes) {
            sb.append(filme).append("\n");
        }

        return sb.toString();
    }

    public Filme create() {
        Filme filme = new Filme();
            String opcao;
            do {
                System.out.println("_____________________________________________\n");
                System.out.println("      Cadastrando filme da locadora   ");
                System.out.println("_____________________________________________\n\n");

                System.out.printf("Informe o codigo do filme que sera cadastrado\n");
                String codigo = scan.nextLine();
                filme.setCodigo(codigo);

                System.out.printf("Informe o titulo do filme:\n");
                String titulo = scan.nextLine();
                filme.setTitulo(titulo);

                System.out.printf("Informe  o genero do filme:\n");
                String genero = scan.nextLine();
                filme.setGenero(genero);

                System.out.printf("Informe a quantidade do estoque:\n");
                long qtdEstoque = scan.nextLong();
                filme.setQtdEstoque(qtdEstoque);

                System.out.printf("Informe a quantidade disponivel para locacao :\n");
                long quantidade = scan.nextLong();
                filme.setQuantidade(quantidade);

                FileService<Filme> fileService = new FileService<>(this.path);
                fileService.insert(filme);

                System.out.println("Filme cadastrado com sucesso");
                System.out.println("__________________________________________________");
                System.out.println("_____________________________________________\n");
                System.out.println("Deseja cadastrar um novo filme S para sim ou N para Nao:  ");
                System.out.println("_____________________________________________");

                scan.nextLine();
                opcao = scan.nextLine().toLowerCase();

            } while (opcao.equals("s"));

        return filme;
    }

    public void alterarQuantidadePorIndice(int indice, long quantidade) {
        try {
            // Lê o arquivo onde os dados dos filmes estão armazenados
            File file = new File(this.path);
            if (!file.exists()) {
                System.out.println("Arquivo de filmes não encontrado.");
                return;
            }

            List<String> lines = Files.readAllLines(file.toPath());

            // Verifica se o índice é válido
            if (indice < 0 || indice >= lines.size()) {
                System.out.println("Índice inválido.");
                return;
            }

            // Obtém o filme pela linha especificada pelo índice

            ArrayList<Filme> filmes = get();
            Filme filme = filmes.get(indice);
            filme.setQuantidade(quantidade);

            System.out.println("Quantidade restantes: " + filme.getQuantidade());

            // Atualiza a linha do arquivo com os novos dados
            lines.set(indice, filme.toString());

            // Reescreve o arquivo com a atualização
            Files.write(file.toPath(), lines);
            System.out.println("Arquivo atualizado com sucesso.");

        } catch (IOException ex) {
            System.out.println("Erro ao alterar o status de locação.");
            Logger.getLogger(FilmeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void alterarStatusLocacaoPorIndice(int indice, boolean isLocado) {
        try {
            // Lê o arquivo onde os dados dos filmes estão armazenados
            File file = new File(this.path);
            if (!file.exists()) {
                System.out.println("Arquivo de filmes não encontrado.");
                return;
            }

            List<String> lines = Files.readAllLines(file.toPath());

            // Verifica se o índice é válido
            if (indice < 0 || indice >= lines.size()) {
                System.out.println("Índice inválido.");
                return;
            }

            // Obtém o filme pela linha especificada pelo índice

            ArrayList<Filme> filmes = get();
            Filme filme = filmes.get(indice);
            filme.setIsLocado(isLocado);

            System.out.println("Status de locação alterado para: " + filme.isIsLocado());

            // Atualiza a linha do arquivo com os novos dados
            lines.set(indice, filme.toString());

            // Reescreve o arquivo com a atualização
            Files.write(file.toPath(), lines);
            System.out.println("Arquivo atualizado com sucesso.");

        } catch (IOException ex) {
            System.out.println("Erro ao alterar o status de locação.");
            Logger.getLogger(FilmeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Filme> search(String titulo) {
        var filmes = get();
        ArrayList<Filme> filmesEncontrados = new ArrayList<>();

        for (Filme filme : filmes) {
            if (filme.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                filmesEncontrados.add(filme);
            }
        }

        return filmesEncontrados;
    }

    public Filme update(Filme filme) {
        try {
            File file = new File(this.path);

            if (file.exists()) {

                List<String> lines = Files.readAllLines(file.toPath());
                int lineIdx = lines.indexOf(filme.toString());

                // altera se achar a linha
                if (lineIdx != -1) {
                    System.out.println("Digite o número da opção que você deseja alterar:");
                    String opcao = scan.nextLine();

                    System.out.println("Digite o novo valor:");

                    Filme newFilme = new Filme();
                    switch (opcao) {
                        case "1":
                            newFilme.setCodigo(scan.nextLine());
                            break;
                        case "2":
                            newFilme.setTitulo(scan.nextLine());
                            break;
                        case "3":
                            newFilme.setGenero(scan.nextLine());
                            break;
                        case "4":
                            newFilme.setQuantidade(scan.nextLong());
                            break;
                        case "5":
                            newFilme.setQtdEstoque(scan.nextLong());
                            break;
                        case "6":
                            newFilme.setIsLocado(scan.nextBoolean());
                            break;
                        default:
                            System.out.println("Opção inválida!");
                    }

                    FileService<Filme> fileService = new FileService<>(this.path);
                    fileService.updateItem(filme, newFilme);
                }

            } else {
                System.out.println("Arquivo nao encontrado");
            }

        } catch (IOException ex) {
            System.out.println("Erro ao alterar o filme");
            Logger.getLogger(FilmeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return filme;
    }

}
