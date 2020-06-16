/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.MovimentacaoUsuario_Dao;
import Modelo.MovimentacaoUsuario;

/**
 *
 * @author Guilherme
 */
public class MovimentacaoUsuario_Ctrl {
    public int AbrirMovimentacaoCtrl(MovimentacaoUsuario m){
       return new MovimentacaoUsuario_Dao().AbrirMovimentacao(m);
    }
    public int SalvarMovimentacaoCtrl(MovimentacaoUsuario m){
       return new MovimentacaoUsuario_Dao().SalvaMovimentacao(m);
    }
    
    
}
