/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visão;

import Controle.Atendimento_Ctrl;
import Controle.MovimentacaoUsuario_Ctrl;
import Controle.Paciente_Ctrl;
import Dao.AlteracoesLaboratoriais_Dao;
import Dao.AlteracoesTC_Dao;
import Dao.Atendimento_Dao;
import Dao.LoginSessao_Dao;
import Dao.MovimentacaoUsuario_Dao;
import Dao.Pacientes_Dao;
import Dao.Sintomas_Dao;
import Dao.Usuario_Dao;
import Modelo.AlteracoesLaboratoriais;
import Modelo.AlteracoesTC;
import Modelo.Atendimento;
import Modelo.MovimentacaoUsuario;
import Modelo.Paciente;
import Modelo.Sintomas;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class TelaAtenderPaciente extends javax.swing.JFrame {

    /**
     * Creates new form TelaGerenciarAlteracoesLaboratoriais
     */
    //Variáveis que iremos utilizar nessa tela
    int Cod_usuario;
    int Cod_Perfil_Permitido;
    int Cod_movimentacao;
    int Cod_registro;
    String ipDaMaquina;

    //Pega data do comutador e faz Converção para o padrão do mysql
    Date d = new Date();
    SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public TelaAtenderPaciente() {
        initComponents();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Imagens/lOGO HR INR_2.png")).getImage());//Icone
        PegarIp();
        ObservacoesSintomas.setLineWrap(true); //Para quebrar a linha na area de texto
        ObservacoesAtendimento.setLineWrap(true); //Para quebrar a linha na area de texto
        PreencherComboTC();
        PreencherComboLaboratoriais();
        PreencherComboSintomas();
        BuscarDadosMedico(Cod_usuario);
    }

    //Pegar Ip e busca o codigo do usuario da sessao
    public void PegarIp() {
        try {
            ipDaMaquina = InetAddress.getLocalHost().getHostAddress();
            //JOptionPane.showMessageDialog(this, ipDaMaquina);
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(this, "Falha ao setar IP da máquina!!");
        }
        LoginSessao_Dao dao = new LoginSessao_Dao();
        dao.BuscarSessao(ipDaMaquina).forEach((c) -> {
            Cod_usuario = c.getCod_usuario();

        });
    }
    
    public void BuscarDadosMedico(int cod_usuario2){
        cod_usuario2 = Cod_usuario;
        Usuario_Dao udao = new Usuario_Dao();
        
        udao.BuscarDadosMedico(Cod_usuario).forEach((u)->{
        txtCodMedico.setText(Integer.toString(u.getCod_Prestador()));
        TxtNomeMedico.setText(u.getNome_Medico());
        TxtCrmMedico.setText(u.getCRM_Medico());
    });
        
    }

    //Preenche JcomboBox com a descrição do TC
    public void PreencherComboTC() {
        AlteracoesTC_Dao bdao = new AlteracoesTC_Dao();
        bdao.BuscarAlteracoesTCAtivos().forEach((tc) -> {
            jComboTC.addItem(tc);
        });

    }

    //Preenche JcomboBox com a descrição das ALterações Laboratoriais
    public void PreencherComboLaboratoriais() {
        AlteracoesLaboratoriais_Dao bdao = new AlteracoesLaboratoriais_Dao();
        bdao.BuscarAlteracoesLaboratoriaisAtivos().forEach((tc) -> {
            jComboLaboratoriais.addItem(tc);
        });

    }

    //Preenche JcomboBox com a descrição de Sintomas
    public void PreencherComboSintomas() {
        Sintomas_Dao bdao = new Sintomas_Dao();
        bdao.BuscarSintomasAtivos().forEach((tc) -> {
            jComboSintomas.addItem(tc);
        });

    }

    //Abre movimentação
    public void AbrirMovimentacaoUsuario() {
        MovimentacaoUsuario m = new MovimentacaoUsuario();
        MovimentacaoUsuario_Ctrl ctrl = new MovimentacaoUsuario_Ctrl();
        MovimentacaoUsuario_Dao dao = new MovimentacaoUsuario_Dao();

        //insere o cod_usuario para abrir um registro na tabela de movimentação
        m.setCod_usuario_movimentacao(Cod_usuario);
        ctrl.AbrirMovimentacaoCtrl(m);

        //Pega o cod da movimentação que acabou de ser aberta nas linhas de cima
        dao.BuscarUltimaMovimentacao().forEach((t) -> {
            Cod_movimentacao = t.getCod_movimentacao();
            //JOptionPane.showMessageDialog(null, Cod_movimentacao);
        });

    }

    //Salva movimentação
    public void SalvarMovimentacaoUsuario_Salvar() {
        if (Cod_movimentacao == 0) {
            JOptionPane.showMessageDialog(null, "Não deu tempo!!");
        } else {
            MovimentacaoUsuario m = new MovimentacaoUsuario();
            MovimentacaoUsuario_Ctrl ctrl = new MovimentacaoUsuario_Ctrl();

            m.setTipo_movimentacao("Salvar");
            m.setTabela_alterada("Paciente");
            m.setCod_registro_alterado(Cod_registro);
            m.setData_Hora_movimentacao(form.format(d));
            m.setCod_usuario_movimentacao(Cod_usuario);
            m.setCod_movimentacao(Cod_movimentacao);
            ctrl.SalvarMovimentacaoCtrl(m);
        }

    }

    //Limpa Campos 
    public void LimparCampos() {
        TxtCrmMedico.setText("");
        TxtNomeMedico.setText("");
        TxtProntuario.setText("");
        TxtNomePaciente.setText("");
        txtTelefone.setText("");
        TxtCodTC.setText("");
        TxtCodAlteracoesLaboratoriais.setText("");
        TxtCodSintomas.setText("");
        ObservacoesSintomas.setText("");
        ObservacoesAtendimento.setText("");
    }

    //Salva Paciente no Banco de DADOS
    public void SalvarPaciente() {
        Paciente p = new Paciente();
        Paciente_Ctrl Pctrl = new Paciente_Ctrl();

        if (TxtProntuario.getText() == null || TxtProntuario.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o Prontuário do Paciente!!");
        } else if (TxtNomePaciente.getText() == null || TxtNomePaciente.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe Nome do Paciente!!");
        } else if (txtTelefone.getText() == null || txtTelefone.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe telefone do Paciente!!");
        } else {
            p.setProntuario(Integer.parseInt(TxtProntuario.getText()));
            p.setNome_Paciente(TxtNomePaciente.getText());
            p.setTelefone_Paciente(txtTelefone.getText());

            Pctrl.SalvarPacienteCtrl(p);
            AbrirMovimentacaoUsuario();

            //Busca o código que acabou de ser gerado para inserir na tabela de movimentação_usuario
            Pacientes_Dao udao = new Pacientes_Dao();
            udao.BuscarUltimoPaciente().forEach((u) -> {

                Cod_registro = u.getCod_Paciente();
            });

            SalvarMovimentacaoUsuario_Salvar();
            LimparCampos();
        }
    }
    
    //Salva Atendimento no Banco de Dados
    public void SalvarAtendimento(){
        Atendimento a = new Atendimento();
        Atendimento_Ctrl ctrl= new Atendimento_Ctrl();
        
        if(TxtCodAlteracoesLaboratoriais==null || TxtCodAlteracoesLaboratoriais.getText().equals("")){
            int i = JOptionPane.showConfirmDialog(null, "Deseja prosseguir com o atentimento sem alteração laboratorial!!");
            if(i == 0){
                TxtCodAlteracoesLaboratoriais.setText("");
            }else if (i == 1){
                jComboLaboratoriais.requestFocus();
            }
        }
        else if(TxtCodTC==null || TxtCodTC.getText().equals("")){
            int i = JOptionPane.showConfirmDialog(null, "Deseja prosseguir com o atentimento sem alteração TC!!");
            if(i == 0){
                TxtCodAlteracoesLaboratoriais.setText("");
            }else if (i == 1){
                jComboLaboratoriais.requestFocus();
            }
        }
        else if(TxtCodSintomas==null || TxtCodSintomas.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Selecione um sintoma!!");
            jComboSintomas.requestFocus();
        }
        else if(ObservacoesAtendimento.getText()==null || ObservacoesAtendimento.getText().equals("")){
            int i = JOptionPane.showConfirmDialog(null, "Deseja prosseguir com o atentimento sem observações no atendimento!!");
            if(i == 0){
                TxtCodAlteracoesLaboratoriais.setText("");
            }else if (i == 1){
                jComboLaboratoriais.requestFocus();
            }
        }
        else if(ObservacoesSintomas.getText()==null || ObservacoesSintomas.getText().equals("")){
            int i = JOptionPane.showConfirmDialog(null, "Deseja prosseguir com o atentimento sem observações de sintomas!!");
            if(i == 0){
                TxtCodAlteracoesLaboratoriais.setText("");
            }else if (i == 1){
                jComboLaboratoriais.requestFocus();
            }
        }else{
            a.setCod_Medico(Integer.parseInt(txtCodMedico.getText()));
            a.setProntuario(Integer.parseInt(TxtProntuario.getText()));
            a.setCod_Alteracoes_Laboratoriais(Integer.parseInt(TxtCodAlteracoesLaboratoriais.getText()));
            a.setCod_TC(Integer.parseInt(TxtCodTC.getText()));
            a.setCod_Sintomas(Integer.parseInt(TxtCodSintomas.getText()));
            a.setObservacoes_Atendimento(ObservacoesAtendimento.getText());
            a.setObservacoes_Sintomas(ObservacoesSintomas.getText());
            
            ctrl.SalvarAtendimentoCtrl(a);
            AbrirMovimentacaoUsuario();

            //Busca o código que acabou de ser gerado para inserir na tabela de movimentação_usuario
            Atendimento_Dao udao = new Atendimento_Dao();
            udao.BuscarUltmoAtendimento().forEach((u) -> {

                Cod_registro = u.getCod_Atendimento();
            });

            SalvarMovimentacaoUsuario_Salvar();
            this.dispose();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TxtCrmMedico = new javax.swing.JTextField();
        Fechar = new javax.swing.JLabel();
        TxtNomeMedico = new javax.swing.JTextField();
        Salvar = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TxtProntuario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TxtNomePaciente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        TxtCodTC = new javax.swing.JTextField();
        jComboTC = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        TxtCodAlteracoesLaboratoriais = new javax.swing.JTextField();
        jComboLaboratoriais = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        TxtCodSintomas = new javax.swing.JTextField();
        jComboSintomas = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ObservacoesSintomas = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ObservacoesAtendimento = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        txtCodMedico = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sistema de Monitoramento COVID-19");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        jLabel1.setText("Atender Paciente");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Logo menor.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(jLabel1)
                .addGap(168, 168, 168)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Nome do Médico");

        TxtCrmMedico.setEditable(false);

        Fechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Fechar.png"))); // NOI18N
        Fechar.setToolTipText("Fechar Tela");
        Fechar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Fechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FecharMouseClicked(evt);
            }
        });

        TxtNomeMedico.setEditable(false);

        Salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Salvar.png"))); // NOI18N
        Salvar.setToolTipText("Salvar Alteração Laboratorial");
        Salvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Salvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SalvarMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Pontuário");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("CRM do Médico");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Nome Paciente");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Telefone");

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)# ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Alterações TC");

        jComboTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboTCActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Alterações Laboratoriais");

        jComboLaboratoriais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboLaboratoriaisActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Sintomas");

        jComboSintomas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboSintomasActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("Observações dos Sintomas");

        ObservacoesSintomas.setColumns(20);
        ObservacoesSintomas.setRows(5);
        ObservacoesSintomas.setWrapStyleWord(true);
        jScrollPane2.setViewportView(ObservacoesSintomas);

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Observações do Atendimento");

        ObservacoesAtendimento.setColumns(20);
        ObservacoesAtendimento.setRows(5);
        ObservacoesAtendimento.setWrapStyleWord(true);
        jScrollPane1.setViewportView(ObservacoesAtendimento);

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("Código Médico");

        txtCodMedico.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Fechar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 118, Short.MAX_VALUE))
                            .addComponent(jScrollPane2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(TxtCodTC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboTC, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(TxtCodAlteracoesLaboratoriais, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboLaboratoriais, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(TxtCodSintomas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboSintomas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel8))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(TxtProntuario, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TxtNomePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCodMedico))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel6))
                                    .addComponent(TxtCrmMedico, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TxtNomeMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Fechar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Salvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addComponent(jLabel12))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtCrmMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNomeMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtProntuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNomePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtCodTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCodAlteracoesLaboratoriais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboLaboratoriais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCodSintomas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboSintomas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        LimparCampos();
    }//GEN-LAST:event_jPanel1MouseClicked

    private void SalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalvarMouseClicked
        SalvarPaciente();
        SalvarAtendimento();
    }//GEN-LAST:event_SalvarMouseClicked

    private void FecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FecharMouseClicked
        this.dispose();
    }//GEN-LAST:event_FecharMouseClicked

    private void jComboTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboTCActionPerformed
        //Metodo para trazer o código do atributo selecionado na JcomboBox
        AlteracoesTC a = (AlteracoesTC) jComboTC.getSelectedItem();
        TxtCodTC.setText(Integer.toString(a.getCod_TC()).trim());
    }//GEN-LAST:event_jComboTCActionPerformed

    private void jComboLaboratoriaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboLaboratoriaisActionPerformed
        //Metodo para trazer o código do atributo selecionado na JcomboBox
        AlteracoesLaboratoriais a = (AlteracoesLaboratoriais) jComboLaboratoriais.getSelectedItem();
        TxtCodAlteracoesLaboratoriais.setText(Integer.toString(a.getCod_Alteracoes_Laboratoriais()).trim());
    }//GEN-LAST:event_jComboLaboratoriaisActionPerformed

    private void jComboSintomasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboSintomasActionPerformed
        //Metodo para trazer o código do atributo selecionado na JcomboBox
        Sintomas a = (Sintomas) jComboSintomas.getSelectedItem();
        TxtCodSintomas.setText(Integer.toString(a.getCod_Sintoma()).trim());
    }//GEN-LAST:event_jComboSintomasActionPerformed

    /**
     * @param args the TxtCodMedicoarguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaAtenderPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAtenderPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAtenderPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAtenderPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAtenderPaciente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fechar;
    private javax.swing.JTextArea ObservacoesAtendimento;
    private javax.swing.JTextArea ObservacoesSintomas;
    private javax.swing.JLabel Salvar;
    private javax.swing.JTextField TxtCodAlteracoesLaboratoriais;
    private javax.swing.JTextField TxtCodSintomas;
    private javax.swing.JTextField TxtCodTC;
    private javax.swing.JTextField TxtCrmMedico;
    private javax.swing.JTextField TxtNomeMedico;
    private javax.swing.JTextField TxtNomePaciente;
    private javax.swing.JTextField TxtProntuario;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<Object> jComboLaboratoriais;
    private javax.swing.JComboBox<Object> jComboSintomas;
    private javax.swing.JComboBox<Object> jComboTC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtCodMedico;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
