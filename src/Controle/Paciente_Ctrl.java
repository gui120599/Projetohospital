/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.Pacientes_Dao;
import Modelo.Paciente;

/**
 *
 * @author Suporte T.I 2
 */
public class Paciente_Ctrl {
    public int SalvarPacienteCtrl(Paciente p){
        return new Pacientes_Dao().SalvarPaciente(p);
    }
}
