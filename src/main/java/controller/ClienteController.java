/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Scanner;

import entity.Cliente;
import service.ClienteService;
import service.FileService;

/**
 *
 * @author andressa
 */
public class ClienteController {

    private static Scanner scan = new Scanner(System.in);
    ClienteService clienteService = new ClienteService();
   

    public void index() {
        System.out.println(clienteService.consultar());
    }

    public void create() {
        clienteService.cadastrar();
    }

    public void show() {
        System.out.println("Informe o nome do cliente que deseja pesquisar");
        String nome = scan.nextLine();

        var clientes = clienteService.search(nome);

        if(clientes.size() == 0) {
            System.out.println("Clientes nao encontrados");
            return;
        }

        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            System.out.printf("%d - %s\n", (i + 1), cliente);
        }

        System.out.println("Escolha um cliente");
        int opcao = scan.nextInt();
        
        Cliente cliente = clientes.get(opcao - 1);
        int clienteIdx = opcao - 1;
        System.out.println("Cliente escolhido:  " + cliente);

        System.out.println("O que deseja fazer?");
        System.out.println("1 - ALTERAR");
        System.out.println("2 - REMOVER");
        System.out.println("0 - CANCELAR");
        opcao = scan.nextInt();

        if(opcao == 1) {
            var updated = clienteService.update(clienteIdx, cliente);
            System.out.println("\nCliente alterado para:");
            System.out.println(updated);
        } else {
            FileService<Cliente> fileService = new FileService<>(this.clienteService.path);
            fileService.delete(cliente);
        }
    }

}
