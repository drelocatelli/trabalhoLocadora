/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Scanner;

import entity.Locacao;
import service.ClienteService;
import service.FileService;
import service.FilmeService;
import service.LocacaoService;

/**
 *
 * @author andressa
 */
public class LocacaoController {

  private Scanner scan = new Scanner(System.in);
  LocacaoService locacaoService = new LocacaoService();

  public void index() {
    System.out.println(locacaoService.consultar());
  }

  public void create() {
    ClienteService clienteService = new ClienteService();
    FilmeService filmeService = new FilmeService();
    
    if (clienteService.get().size() == 0) {
      System.out.println("Nenhum cliente cadastrado para locar");
      return;
    }

    if (filmeService.get().size() == 0) {
      System.out.println("Nenhum filme cadastrado para locar");
      return;
    }

    locacaoService.cadastrar();
  }

  public void delete() {
    var locacoes = locacaoService.getLocacoes();

    if (locacoes.size() == 0) {
      System.out.println("Nenhuma locacao cadastrada");
      return;
    }

    for (int i = 0; i < locacoes.size(); i++) {
      System.out.println((i + 1) + " - " + locacoes.get(i));
    }

    System.out.println("Escolha uma locação: ");
    int opcao = scan.nextInt();

    System.out.println("Locação escolhida: " + locacoes.get(opcao - 1));

    if (opcao < 1 || opcao > locacoes.size()) {
      System.out.println("Opção inválida");
      return;
    }

    FileService<Locacao> fileService = new FileService<>(locacaoService.path);
    fileService.delete(locacoes.get(opcao - 1));
  }

}
