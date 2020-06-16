/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.AlteracoesLaboratoriais_Dao;
import Modelo.AlteracoesLaboratoriais;

/**
 *
 * @author Suporte T.I 2
 */
public class AlteracoesLaboratoriais_Ctrl {
    public int SalvarLaboratorialCtlr(AlteracoesLaboratoriais Al){
        return new AlteracoesLaboratoriais_Dao().SalvarAlteracoesLaboratoriais(Al);
    }
    public int AtualizarLaboratorialCtlr(AlteracoesLaboratoriais Al){
        return new AlteracoesLaboratoriais_Dao().AtualizarAlteracoesLaboratoriais(Al);
    }
}
