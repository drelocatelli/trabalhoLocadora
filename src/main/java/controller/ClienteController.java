/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import service.ClienteService;

/**
 *
 * @author andressa
 */
public class ClienteController {

    ClienteService clienteService = new ClienteService();
   

    public void index() {
        System.out.println(clienteService.consultar());
    }

    public void create() {
        clienteService.cadastrar();
    }

}
