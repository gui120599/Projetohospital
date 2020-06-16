/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.Usuario_Dao;
import Modelo.Usuario;

/**
 *
 * @author Guilherme
 */
public class Usuario_Ctrl {
    public int SalvarUsuarioCtrl(Usuario usuario){
        return new Usuario_Dao().SalvarUsuario(usuario);
    }
}
