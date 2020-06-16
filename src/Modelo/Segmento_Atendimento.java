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
public class Segmento_Atendimento {
    private int Cod_Segmento_Atendimento;
    private int Cod_Atendimento;
    private int Cod_Medico_Segmento;
    private String Data_Segmento;
    private String Hora_Segmento;
    private int Cod_Sintomas;
    private String Observacoes_Sintomas;

    public Segmento_Atendimento() {
    }

    public Segmento_Atendimento(int Cod_Segmento_Atendimento, int Cod_Atendimento, int Cod_Medico_Segmento, String Data_Segmento, String Hora_Segmento, int Cod_Sintomas, String Observacoes_Sintomas) {
        this.Cod_Segmento_Atendimento = Cod_Segmento_Atendimento;
        this.Cod_Atendimento = Cod_Atendimento;
        this.Cod_Medico_Segmento = Cod_Medico_Segmento;
        this.Data_Segmento = Data_Segmento;
        this.Hora_Segmento = Hora_Segmento;
        this.Cod_Sintomas = Cod_Sintomas;
        this.Observacoes_Sintomas = Observacoes_Sintomas;
    }

    public int getCod_Segmento_Atendimento() {
        return Cod_Segmento_Atendimento;
    }

    public void setCod_Segmento_Atendimento(int Cod_Segmento_Atendimento) {
        this.Cod_Segmento_Atendimento = Cod_Segmento_Atendimento;
    }

    public int getCod_Atendimento() {
        return Cod_Atendimento;
    }

    public void setCod_Atendimento(int Cod_Atendimento) {
        this.Cod_Atendimento = Cod_Atendimento;
    }

    public int getCod_Medico_Segmento() {
        return Cod_Medico_Segmento;
    }

    public void setCod_Medico_Segmento(int Cod_Medico_Segmento) {
        this.Cod_Medico_Segmento = Cod_Medico_Segmento;
    }

    public String getData_Segmento() {
        return Data_Segmento;
    }

    public void setData_Segmento(String Data_Segmento) {
        this.Data_Segmento = Data_Segmento;
    }

    public String getHora_Segmento() {
        return Hora_Segmento;
    }

    public void setHora_Segmento(String Hora_Segmento) {
        this.Hora_Segmento = Hora_Segmento;
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
    
    
    
}
