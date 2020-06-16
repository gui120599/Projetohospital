/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.Medico_Dao;
import Modelo.Medico;

/**
 *
 * @author Suporte T.I 2
 */
public class Medicos_Ctrl {
    public int SalvarLaboratorialCtlr(Medico Al){
        return new Medico_Dao().SalvarMedico(Al);
    }
    public int AtualizarLaboratorialCtlr(Medico Al){
        return new Medico_Dao().AtualizarMedico(Al);
    }
}
