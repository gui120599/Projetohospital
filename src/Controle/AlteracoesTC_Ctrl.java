/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.AlteracoesTC_Dao;
import Modelo.AlteracoesTC;

/**
 *
 * @author Suporte T.I 2
 */
public class AlteracoesTC_Ctrl {
    public int SalvarLaboratorialCtlr(AlteracoesTC Al){
        return new AlteracoesTC_Dao().SalvarAlteracoesTC(Al);
    }
    public int AtualizarLaboratorialCtlr(AlteracoesTC Al){
        return new AlteracoesTC_Dao().AtualizarAlteracoesTC(Al);
    }
}
