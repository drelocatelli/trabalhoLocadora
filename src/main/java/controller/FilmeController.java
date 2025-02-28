/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Scanner;

import entity.Filme;
import service.FileService;
import service.FilmeService;

/**
 *
 * @author andressa
 */
public class FilmeController {

    private static Scanner scan = new Scanner(System.in);
    
    FilmeService filmeService = new FilmeService();

    public void index() {
        System.out.println(filmeService.show());
    }

    public void create() {
        filmeService.create();
    }

    public void show() {
        System.out.println("Informe o título do filme que deseja pesquisar: ");
        String titulo = scan.nextLine();

        var filmes = filmeService.search(titulo);

        if(filmes.size() == 0) {
            System.out.println("Filmes nao encontrados");
            return;
        }

        for(int i = 0; i < filmes.size(); i++) {
            Filme filme = filmes.get(i);
            System.out.printf("%d - %s\n", (i + 1), filme);
        }

        System.out.println("Escolha o filme: ");
        int opcao = scan.nextInt();

        Filme filme = filmes.get(opcao - 1);

        System.out.println("Filme escolhido:  " + filme);

        System.out.println("O que deseja fazer?");
        System.out.println("1 - ALTERAR");
        System.out.println("2 - REMOVER");
        System.out.println("0 - CANCELAR");

        opcao = scan.nextInt();

        if(opcao == 1) {
            var updatedFilme = filmeService.update(filme);
            System.out.println("\nFilme alterado para:");
            System.out.println(updatedFilme);
        } else if(opcao == 2) {
            FileService<Filme> fileService = new FileService(filmeService.path);
            fileService.delete(filme);
        }

    }

}
