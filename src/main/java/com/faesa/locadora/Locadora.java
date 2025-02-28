/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.faesa.locadora;

import controller.ClienteController;
import controller.FilmeController;
import controller.LocacaoController;

import java.util.Scanner;

/**
 *
 * @author andressa
 */
public class Locadora {

    private static Scanner scan = new Scanner(System.in);
    private static FilmeController filmeController = new FilmeController();
    private static ClienteController clienteController = new ClienteController();
    private static LocacaoController locacaoController = new LocacaoController();

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        System.out.println("************************************");
        System.out.println("*************** \033[0;30m\033[1;41mMENU\033[0m ***************");
        System.out.println("1 - CADASTRAR ");
        System.out.println("2 - CONSULTAR ");
        System.out.println("3 - PESQUISAR");
        System.out.println("0 - Sair");
        System.out.println("Escolha a opção: ");
        System.out.println("************************************");
        System.out.println("************************************");

        int opcao = scan.nextInt();

        switch (opcao) {
            case 1 ->
                cadastro();
            case 2 ->
                consulta();
        }

        scan.close();
    }

    public static void cadastro() {
        System.out.println("*****************************************");
        System.out.println("*************** \033[0;30m\033[1;41mCADASTRAR\033[0m ***************");
        System.out.println("1 - FILME ");
        System.out.println("2 - CLIENTE ");
        System.out.println("3 - LOCAÇÃO ");
        System.out.println("0 - Voltar");
        System.out.println("Escolha a opção: ");
        System.out.println("******************************************");
        System.out.println("******************************************");

        int opcao = scan.nextInt();

        switch (opcao) {
            case 1 ->
                filmeController.cadastrar();
            case 2 ->
                clienteController.cadastrar();
            case 3 ->
                locar();
            case 0 ->
                mainMenu();
        }

        scan.close();

    }

    public static void consulta() {
        System.out.println("*****************************************");
        System.out.println("*************** \033[0;30m\033[1;41mCONSULTA\033[0m ***************");
        System.out.println("1 - FILMES ");
        System.out.println("2 - CLIENTES ");
        System.out.println("3 - LOCAÇÕES ");
        System.out.println("0 - Voltar");
        System.out.println("Escolha a opção: ");
        System.out.println("******************************************");
        System.out.println("******************************************");

        int opcao = scan.nextInt();

        switch (opcao) {
            case 1 ->
                System.out.println(filmeController.consultar());
            case 2 ->
                System.out.println(clienteController.consultar());
            case 3 ->
                System.out.println(locacaoController.consultar());
            case 0 ->
                mainMenu();
           
        }

        scan.close();
    }

    public static void locar() {
        if (clienteController.quantidade() == 0) {
            System.out.println("Nenhum cliente cadastrado para locar");
            mainMenu();
            return;
        }

        if (filmeController.quantidade() == 0) {
            System.out.println("Nenhum filme cadastrado para locar");
            mainMenu();
            return;
        }

        locacaoController.cadastrar();
        
    }
}
