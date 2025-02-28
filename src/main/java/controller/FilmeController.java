/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entity.Filme;
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

/**
 *
 * @author andressa
 */
public class FilmeController {

    public String path = "filmes.txt";
    private static Scanner scan = new Scanner(System.in);
    private PrintWriter printWriter = null;
    private FileWriter fileWriter = null;

    public ArrayList<Filme> getFilmes() {
        ArrayList<Filme> filmes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile("codigo=(.*?), titulo=(.*?), genero=(.*?), quantidade=(\\d+), qtdEstoque=(\\d+), isLocado=(true|false)]");
                Matcher matcher = pattern.matcher(linha);

                if(matcher.find()) {
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

    public String consultar() {
        ArrayList<Filme> filmes = getFilmes();
        StringBuilder sb = new StringBuilder();
        
        for (Filme filme : filmes) {
            sb.append(filme).append("\n");
        }

        return sb.toString();
    }

    public int quantidade() {
        int linhas = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (reader.readLine() != null) {
                linhas++;
            }
        } catch (IOException ex) {
            System.out.println("Ocorreu um erro a contabilizar filmes: " + ex.getMessage());
        }

        return linhas;
    }

    public void cadastrar() {

        try {
            fileWriter = new FileWriter(this.path, true);
            printWriter = new PrintWriter(fileWriter);

            String opcao;
            do {

                System.out.println("_____________________________________________\n");
                System.out.println("      Cadastrando filme da locadora   ");
                System.out.println("_____________________________________________\n\n");

                Filme filme = new Filme();

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

                printWriter.println(filme);

                System.out.println("Filme cadastrado com sucesso");
                System.out.println("__________________________________________________");
                System.out.println("_____________________________________________\n");
                System.out.println("Deseja cadastrar um novo filme S para sim ou N para Nao:  ");
                System.out.println("_____________________________________________");

                scan.nextLine();
                opcao = scan.nextLine().toLowerCase();

            } while (opcao.equals("s"));

        } catch (IOException ex) {
            System.out.println("Não foi possível cadastrar filme");
            Logger.getLogger(FilmeController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (printWriter != null) {
                printWriter.close(); // Fechar o PrintWriter apenas uma vez
            }
            if (scan != null) {
                scan.close(); // Fechar o Scanner apenas uma vez
            }
        }
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

            ArrayList<Filme> filmes = getFilmes();
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

            ArrayList<Filme> filmes = getFilmes();
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
    

    public void atualizar() {
        try {
            // Lê o arquivo onde os dados dos filmes estão armazenados
            File file = new File(this.path);
            if (!file.exists()) {
                System.out.println("Arquivo de filmes não encontrado.");
                return;
            }

            List<String> lines = Files.readAllLines(file.toPath());

            System.out.println("Informe o código do filme para atualizar: ");
            String codigoToUpdate = scan.nextLine();

            // Flag para saber se o filme foi encontrado
            boolean filmeEncontrado = false;

            // Itera sobre as linhas do arquivo para encontrar o filme com o código
            // especificado
            for (int i = 0; i < lines.size(); i++) {
                String linha = lines.get(i);
                // Verifica se a linha contém o código do filme
                if (linha.contains(codigoToUpdate)) {
                    filmeEncontrado = true;
                    Filme filme = new Filme(); // Aqui você deve recriar o filme baseado na linha
                    String[] dados = linha.split("-"); // Supondo que o formato seja separado por "-"

                    // Atualiza os campos do filme com base nas informações da linha
                    filme.setCodigo(dados[0]);
                    filme.setTitulo(dados[1]);
                    filme.setGenero(dados[2]);
                    filme.setQtdEstoque(Long.parseLong(dados[3]));
                    filme.setQuantidade(Long.parseLong(dados[4]));
                    filme.setIsLocado(Boolean.parseBoolean(dados[5]));

                    System.out.println("Filme encontrado: " + filme.toString());

                    // Pergunta ao usuário quais campos ele quer atualizar (campos opcionais)
                    System.out.printf("Informe o novo título (Deixe em branco para não alterar): ");
                    String novoTitulo = scan.nextLine();
                    if (!novoTitulo.isEmpty()) {
                        filme.setTitulo(novoTitulo);
                    }

                    System.out.printf("Informe o novo gênero (Deixe em branco para não alterar): ");
                    String novoGenero = scan.nextLine();
                    if (!novoGenero.isEmpty()) {
                        filme.setGenero(novoGenero);
                    }

                    System.out.printf("Informe a nova quantidade no estoque (Deixe em branco para não alterar): ");
                    String novaQtdEstoque = scan.nextLine();
                    if (!novaQtdEstoque.isEmpty()) {
                        filme.setQtdEstoque(Long.parseLong(novaQtdEstoque));
                    }

                    System.out.printf(
                            "Informe a nova quantidade disponível para locação (Deixe em branco para não alterar): ");
                    String novaQuantidade = scan.nextLine();
                    if (!novaQuantidade.isEmpty()) {
                        filme.setQuantidade(Long.parseLong(novaQuantidade));
                    }

                    // Atualiza a linha do arquivo com os novos dados
                    lines.set(i, filme.toString());

                    break; // Sai do loop após atualizar o filme
                }
            }

            if (filmeEncontrado) {
                // Reescreve o arquivo com as atualizações feitas
                Files.write(file.toPath(), lines);
                System.out.println("Filme atualizado com sucesso.");
            } else {
                System.out.println("Filme não encontrado com o código especificado.");
            }

        } catch (IOException ex) {
            System.out.println("Erro ao atualizar o filme.");
            Logger.getLogger(FilmeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
