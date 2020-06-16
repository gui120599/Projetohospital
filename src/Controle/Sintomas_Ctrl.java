/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.Sintomas_Dao;
import Modelo.Sintomas;

/**
 *
 * @author Suporte T.I 2
 */
public class Sintomas_Ctrl {
    public int SalvarLaboratorialCtlr(Sintomas Al){
        return new Sintomas_Dao().SalvarSintoma(Al);
    }
    public int AtualizarLaboratorialCtlr(Sintomas Al){
        return new Sintomas_Dao().AtualizarSintoma(Al);
    }
}
