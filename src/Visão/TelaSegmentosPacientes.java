/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visão;

import Controle.Atendimento_Ctrl;
import Controle.MovimentacaoUsuario_Ctrl;
import Dao.AlteracoesLaboratoriais_Dao;
import Dao.AlteracoesTC_Dao;
import Dao.Atendimento_Dao;
import Dao.LoginSessao_Dao;
import Dao.MovimentacaoUsuario_Dao;
import Dao.Sintomas_Dao;
import Dao.Usuario_Dao;
import Modelo.AlteracoesLaboratoriais;
import Modelo.AlteracoesTC;
import Modelo.Atendimento;
import Modelo.MovimentacaoUsuario;
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
public class TelaSegmentosPacientes extends javax.swing.JFrame {

    /**
     * Creates new form TelaSegmentosPacientes
     */
    int Cod_usuario;
    int Cod_Perfil_Permitido;
    int Cod_movimentacao;
    int Cod_registro;
    String ipDaMaquina;

    //Pega data do comutador e faz Converção para o padrão do mysql
    Date d = new Date();
    SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd ");
    SimpleDateFormat hora = new SimpleDateFormat("hh:mm:ss");

    public TelaSegmentosPacientes() {
        initComponents();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Imagens/lOGO HR INR_2.png")).getImage());//Icone
        PegarIp();
        ObservacoesSintomas.setLineWrap(true); //Para quebrar a linha na area de texto
        ObservacoesAtendimento.setLineWrap(true); //Para quebrar a linha na area de texto
        PreencherComboTC();
        PreencherComboLaboratoriais();
        PreencherComboSintomas();
        BuscarDadosMedico(Cod_usuario);
        TxtProntuario.requestFocus();
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
    
    public void BuscarDadosMedico(int cod_usuario2) {
        cod_usuario2 = Cod_usuario;
        Usuario_Dao udao = new Usuario_Dao();
        
        udao.BuscarDadosMedico(Cod_usuario).forEach((u) -> {
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
    public void SalvarMovimentacaoUsuario_SalvarPaciente() {
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

    //Salva movimentação
    public void SalvarMovimentacaoUsuario_SalvarAtendimento() {
        if (Cod_movimentacao == 0) {
            JOptionPane.showMessageDialog(null, "Não deu tempo!!");
        } else {
            MovimentacaoUsuario m = new MovimentacaoUsuario();
            MovimentacaoUsuario_Ctrl ctrl = new MovimentacaoUsuario_Ctrl();
            
            m.setTipo_movimentacao("Salvar");
            m.setTabela_alterada("Atendimento");
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
    
    //Salva Atendimento no Banco de Dados
    public void SalvarAtendimento() {
        Atendimento a = new Atendimento();
        Atendimento_Ctrl ctrl = new Atendimento_Ctrl();
        if (TxtCodSintomas == null || TxtCodSintomas.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Selecione um sintoma!!");
            jComboSintomas.requestFocus();

        } else {
            a.setCod_Medico(Integer.parseInt(txtCodMedico.getText()));
            a.setProntuario(Integer.parseInt(TxtProntuario.getText()));
            a.setCod_Alteracoes_Laboratoriais(Integer.parseInt(TxtCodAlteracoesLaboratoriais.getText()));
            a.setCod_TC(Integer.parseInt(TxtCodTC.getText()));
            a.setCod_Sintomas(Integer.parseInt(TxtCodSintomas.getText()));
            a.setObservacoes_Atendimento(ObservacoesAtendimento.getText());
            a.setObservacoes_Sintomas(ObservacoesSintomas.getText());
            a.setData_Atendimento(data.format(d));
            a.setHora_Atendimento(hora.format(d));
            
            
            int i = JOptionPane.showConfirmDialog(null, "Revisão do Atendimento: \n"
                    + "CRM: " + TxtCrmMedico.getText() + " Médico: " + TxtNomeMedico.getText() + "\n"
                    + "Prontuário: " + TxtProntuario.getText() + " Paciente: " + TxtNomePaciente.getText() + "\n"
                    + "Alterações Laboratoriais: " + jComboLaboratoriais.getSelectedItem() + "\n"
                    + "Alterações TC: "+jComboTC.getSelectedItem()+"\n"
                    + "Sintoma: "+jComboSintomas.getSelectedItem()+"\n"
                    + "Deseja finalizar o atentimento?");
            if (i == 0) {
                ctrl.SalvarAtendimentoCtrl(a);
                AbrirMovimentacaoUsuario();

                //Busca o código que acabou de ser gerado para inserir na tabela de movimentação_usuario
                Atendimento_Dao udao = new Atendimento_Dao();
                udao.BuscarUltmoAtendimento().forEach((u) -> {

                    Cod_registro = u.getCod_Atendimento();
                });

                SalvarMovimentacaoUsuario_SalvarAtendimento();
                this.dispose();

            } else if (i == 1) {
                TxtProntuario.requestFocus();
            }

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
        PainelLogos = new javax.swing.JPanel();
        LogoSistem = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        Avancar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTPacientes = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTAtendimentos = new javax.swing.JTable();
        Codigo = new javax.swing.JRadioButton();
        Descricao = new javax.swing.JRadioButton();
        TxtPesquisa = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtCodMedico = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TxtCrmMedico = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TxtNomeMedico = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TxtProntuario = new javax.swing.JTextField();
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
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ObservacoesAtendimento = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ObservacoesSintomas = new javax.swing.JTextArea();
        Salvar = new javax.swing.JLabel();
        Fechar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        PainelLogos.setBackground(new java.awt.Color(0, 255, 255));

        LogoSistem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/lOGO SISTEM_1.png"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/logo hr_1.png"))); // NOI18N

        javax.swing.GroupLayout PainelLogosLayout = new javax.swing.GroupLayout(PainelLogos);
        PainelLogos.setLayout(PainelLogosLayout);
        PainelLogosLayout.setHorizontalGroup(
            PainelLogosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelLogosLayout.createSequentialGroup()
                .addComponent(LogoSistem, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PainelLogosLayout.setVerticalGroup(
            PainelLogosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LogoSistem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        Avancar.setText("Avançar");
        Avancar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AvancarActionPerformed(evt);
            }
        });

        jTPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Prontuário", "Nome", "Telefone"
            }
        ));
        jScrollPane3.setViewportView(jTPacientes);

        jTAtendimentos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Atendimentos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N
        jTAtendimentos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTAtendimentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Atendimento", "Médico"
            }
        ));
        jScrollPane4.setViewportView(jTAtendimentos);

        buttonGroup1.add(Codigo);
        Codigo.setText("Prontário");

        buttonGroup1.add(Descricao);
        Descricao.setText("Nome");

        jButton1.setText("Buscar");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Telefone");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(Codigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Descricao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TxtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Avancar)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(23, 23, 23))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Codigo)
                    .addComponent(Descricao)
                    .addComponent(TxtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jRadioButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(Avancar))
        );

        jTabbedPane1.addTab("Pacientes Atendidos", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("Código Médico");

        txtCodMedico.setEditable(false);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("CRM do Médico");

        TxtCrmMedico.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Nome do Médico");

        TxtNomeMedico.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Pontuário");

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

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Observações do Atendimento");

        ObservacoesAtendimento.setColumns(20);
        ObservacoesAtendimento.setRows(5);
        ObservacoesAtendimento.setWrapStyleWord(true);
        jScrollPane1.setViewportView(ObservacoesAtendimento);

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("Observações dos Sintomas");

        ObservacoesSintomas.setColumns(20);
        ObservacoesSintomas.setRows(5);
        ObservacoesSintomas.setWrapStyleWord(true);
        jScrollPane2.setViewportView(ObservacoesSintomas);

        Salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Salvar.png"))); // NOI18N
        Salvar.setToolTipText("Salvar Alteração Laboratorial");
        Salvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Salvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SalvarMouseClicked(evt);
            }
        });

        Fechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Fechar.png"))); // NOI18N
        Fechar.setToolTipText("Fechar Tela");
        Fechar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Fechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FecharMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(206, 206, 206)
                        .addComponent(jLabel11))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(TxtCodTC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboTC, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel9))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(TxtCodAlteracoesLaboratoriais, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboLaboratoriais, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(TxtCodSintomas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboSintomas, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(TxtProntuario, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtNomePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCodMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel6))
                            .addComponent(TxtCrmMedico, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtNomeMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Fechar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Salvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Fechar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addComponent(jLabel12))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtCrmMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNomeMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtProntuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNomePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtCodTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCodAlteracoesLaboratoriais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboLaboratoriais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCodSintomas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboSintomas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(113, 113, 113))
        );

        jTabbedPane1.addTab("Atendimento", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelLogos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(PainelLogos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AvancarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AvancarActionPerformed
        this.jTabbedPane1.setSelectedIndex(1);        // VAI PARA OUTRA ABA
    }//GEN-LAST:event_AvancarActionPerformed

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

    private void SalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalvarMouseClicked
        
        SalvarAtendimento();
    }//GEN-LAST:event_SalvarMouseClicked

    private void FecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FecharMouseClicked
        this.dispose();
    }//GEN-LAST:event_FecharMouseClicked

    /**
     * @param args the command line arguments
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
            java.util.logging.Logger.getLogger(TelaSegmentosPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaSegmentosPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaSegmentosPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaSegmentosPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaSegmentosPacientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Avancar;
    private javax.swing.JRadioButton Codigo;
    private javax.swing.JRadioButton Descricao;
    private javax.swing.JLabel Fechar;
    private javax.swing.JLabel LogoSistem;
    private javax.swing.JTextArea ObservacoesAtendimento;
    private javax.swing.JTextArea ObservacoesSintomas;
    private javax.swing.JPanel PainelLogos;
    private javax.swing.JLabel Salvar;
    private javax.swing.JTextField TxtCodAlteracoesLaboratoriais;
    private javax.swing.JTextField TxtCodSintomas;
    private javax.swing.JTextField TxtCodTC;
    private javax.swing.JTextField TxtCrmMedico;
    private javax.swing.JTextField TxtNomeMedico;
    private javax.swing.JTextField TxtNomePaciente;
    private javax.swing.JTextField TxtPesquisa;
    private javax.swing.JTextField TxtProntuario;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTAtendimentos;
    private javax.swing.JTable jTPacientes;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField txtCodMedico;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
