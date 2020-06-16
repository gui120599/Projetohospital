/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.Atendimento_Dao;
import Modelo.Atendimento;

/**
 *
 * @author Suporte T.I 2
 */
public class Atendimento_Ctrl {
    public int SalvarAtendimentoCtrl(Atendimento a){
        return new Atendimento_Dao().SalvarAtendimento(a);
    }
}
