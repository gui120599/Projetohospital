/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.Colaboradores_Dao;
import Modelo.Colaboradores;

/**
 *
 * @author Suporte T.I 2
 */
public class Colaboradores_Ctrl {
    public int SalvarColaboradorCtlr(Colaboradores Al){
        return new Colaboradores_Dao().SalvarMedico(Al);
    }
    public int AtualizarColaboradorCtlr(Colaboradores Al){
        return new Colaboradores_Dao().AtualizarMedico(Al);
    }
}
