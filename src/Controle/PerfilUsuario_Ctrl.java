/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.PerfilUsuario_Dao;
import Modelo.PerfilUsuario;

/**
 *
 * @author Suporte T.I 2
 */
public class PerfilUsuario_Ctrl {
    public int SalvarPerfilCtrl(PerfilUsuario pu){
        return new PerfilUsuario_Dao().SalvarUsuario(pu);
    }
}
