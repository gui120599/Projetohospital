/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Suporte T.I 2
 */
public class Atendimento {
    private int Cod_Atendimento;
    private int Prontuario;
    private int Cod_Colaborador;
    private int Cod_TC;
    private int Cod_Alteracoes_Laboratoriais;
    private int Cod_Sintomas;
    private String Observacoes_Sintomas;
    private String Data_Atendimento;
    private String Hora_Atendimento;
    private String Observacoes_Atendimento;

    public Atendimento() {
    }

    public Atendimento(int Cod_Atendimento, int Prontuario, int Cod_Colaborador, int Cod_TC, int Cod_Alteracoes_Laboratoriais, int Cod_Sintomas, String Observacoes_Sintomas, String Data_Atendimento, String Hora_Atendimento, String Observacoes_Atendimento) {
        this.Cod_Atendimento = Cod_Atendimento;
        this.Prontuario = Prontuario;
        this.Cod_Colaborador = Cod_Colaborador;
        this.Cod_TC = Cod_TC;
        this.Cod_Alteracoes_Laboratoriais = Cod_Alteracoes_Laboratoriais;
        this.Cod_Sintomas = Cod_Sintomas;
        this.Observacoes_Sintomas = Observacoes_Sintomas;
        this.Data_Atendimento = Data_Atendimento;
        this.Hora_Atendimento = Hora_Atendimento;
        this.Observacoes_Atendimento = Observacoes_Atendimento;
    }

    public int getCod_Atendimento() {
        return Cod_Atendimento;
    }

    public void setCod_Atendimento(int Cod_Atendimento) {
        this.Cod_Atendimento = Cod_Atendimento;
    }

    public int getProntuario() {
        return Prontuario;
    }

    public void setProntuario(int Prontuario) {
        this.Prontuario = Prontuario;
    }

    public int getCod_Colaborador() {
        return Cod_Colaborador;
    }

    public void setCod_Colaborador(int Cod_Colaborador) {
        this.Cod_Colaborador = Cod_Colaborador;
    }

    public int getCod_TC() {
        return Cod_TC;
    }

    public void setCod_TC(int Cod_TC) {
        this.Cod_TC = Cod_TC;
    }

    public int getCod_Alteracoes_Laboratoriais() {
        return Cod_Alteracoes_Laboratoriais;
    }

    public void setCod_Alteracoes_Laboratoriais(int Cod_Alteracoes_Laboratoriais) {
        this.Cod_Alteracoes_Laboratoriais = Cod_Alteracoes_Laboratoriais;
    }

    public int getCod_Sintomas() {
        return Cod_Sintomas;
    }

    public void setCod_Sintomas(int Cod_Sintomas) {
        this.Cod_Sintomas = Cod_Sintomas;
    }

    public String getObservacoes_Sintomas() {
        return Observacoes_Sintomas;
    }

    public void setObservacoes_Sintomas(String Observacoes_Sintomas) {
        this.Observacoes_Sintomas = Observacoes_Sintomas;
    }

    public String getData_Atendimento() {
        return Data_Atendimento;
    }

    public void setData_Atendimento(String Data_Atendimento) {
        this.Data_Atendimento = Data_Atendimento;
    }

    public String getHora_Atendimento() {
        return Hora_Atendimento;
    }

    public void setHora_Atendimento(String Hora_Atendimento) {
        this.Hora_Atendimento = Hora_Atendimento;
    }

    public String getObservacoes_Atendimento() {
        return Observacoes_Atendimento;
    }

    public void setObservacoes_Atendimento(String Observacoes_Atendimento) {
        this.Observacoes_Atendimento = Observacoes_Atendimento;
    }

}
