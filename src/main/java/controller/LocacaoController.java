/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import service.LocacaoService;

/**
 *
 * @author andressa
 */
public class LocacaoController {

  LocacaoService locacaoService = new LocacaoService();

  public void index() {
    System.out.println(locacaoService.consultar());
  }

  public void create() {
    locacaoService.cadastrar();
  }

}
