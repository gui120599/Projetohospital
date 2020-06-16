/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.LoginSessao_Dao;
import Modelo.LoginSessao;

/**
 *
 * @author Guilherme
 */
public class LoginSessao_Ctrl {
    public int AbrirSessao(LoginSessao l){
        return new LoginSessao_Dao().AbrirSessao(l);
    }
}
