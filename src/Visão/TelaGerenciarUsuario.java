/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visão;

import Controle.MovimentacaoUsuario_Ctrl;
import Controle.Usuario_Ctrl;
import Dao.LoginSessao_Dao;
import Dao.Medico_Dao;
import Dao.MovimentacaoUsuario_Dao;
import Dao.PerfilUsuario_Dao;
import Dao.Usuario_Dao;
import Modelo.Medico;
import Modelo.MovimentacaoUsuario;
import Modelo.PerfilUsuario;
import Modelo.Usuario;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Suporte T.I 2
 */
public class TelaGerenciarUsuario extends javax.swing.JFrame {

    /**
     * Creates new form TelaGerenciarUsuario
     */
    int Cod_usuario;
    int Cod_movimentacao;
    int Cod_registro;
    String ipDaMaquina;

    //Pega data do comutador e faz Converção para o padrão do mysql
    Date d = new Date();
    SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public TelaGerenciarUsuario() {
        initComponents();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Imagens/lOGO HR INR_2.png")).getImage());//Icone
        Codigo.setSelected(true);//Aciona BOTAO DE PESQUISA
        MostrarUsuarios();
        PreenchercomboPerfis();
        PreencherComboPrestador();
        PegarIp();
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

    public void SalvarMovimentacaoUsuario_Salvar() {
        if (Cod_movimentacao == 0) {
            JOptionPane.showMessageDialog(null, "Não deu tempo!!");
        } else {
            MovimentacaoUsuario m = new MovimentacaoUsuario();
            MovimentacaoUsuario_Ctrl ctrl = new MovimentacaoUsuario_Ctrl();

            m.setTipo_movimentacao("Salvar");
            m.setTabela_alterada("Usuario");
            m.setCod_registro_alterado(Cod_registro);
            m.setData_Hora_movimentacao(form.format(d));
            m.setCod_usuario_movimentacao(Cod_usuario);
            m.setCod_movimentacao(Cod_movimentacao);
            ctrl.SalvarMovimentacaoCtrl(m);
        }

    }

    //carrega Tabela
    public void MostrarUsuarios() {
        jTUsuario.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTUsuario.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTUsuario.getColumnModel().getColumn(2).setPreferredWidth(60);
        DefaultTableModel modelo = (DefaultTableModel) jTUsuario.getModel();
        modelo.setNumRows(0);
        Usuario_Dao bdao = new Usuario_Dao();

        bdao.MostrarUsuario().forEach((b) -> {
            modelo.addRow(new Object[]{
                b.getCod_usuario(),
                b.getLogin(),
                b.getDesc_Perfil()
            });
        });
    }

    public void PreenchercomboPerfis() {
        PerfilUsuario_Dao udao = new PerfilUsuario_Dao();

        udao.MostrarPerfis().forEach((u) -> {
            ComboBoxPerfis.addItem(u);
        });
    }
    
    public void PreencherComboPrestador(){
        Medico_Dao udao = new Medico_Dao();
        udao.BuscarMedicoAtivos().forEach((u)->{
            ComboBoxPrestador.addItem(u);
        });
    }
    
//Limpa Campos 

    public void LimparCampos() {
        TxtCodigo.setText("");
        TxtLogin.setText("");
        TxtSenha.setText("");
        TxtPesquisa.setText("");
    }

//Salva usuario
    public void Salvar() {
        Usuario u = new Usuario();
        Usuario_Ctrl ctrl = new Usuario_Ctrl();
        if (TxtLogin.getText() == null || TxtLogin.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Insira o login!!");
        } else if (TxtSenha.getText() == null || TxtSenha.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Insira a Senha!!");
        } else if(txtCodPerfil.getText()==null || txtCodPerfil.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Selecione um perfil!!");
        } else if(txtCodPrestador.getText()==null || txtCodPrestador.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Selecione um prestador!!");
        }
            else {
            u.setLogin(TxtLogin.getText());
            u.setSenha(TxtSenha.getText());
            u.setCod_Perfil(Integer.parseInt(txtCodPerfil.getText()));
            u.setCod_Prestador(Integer.parseInt(txtCodPrestador.getText()));
            ctrl.SalvarUsuarioCtrl(u);
            AbrirMovimentacaoUsuario();

            //Busca o código que acabou de ser gerado para inserir na tabela de movimentação_usuario
            Usuario_Dao udao = new Usuario_Dao();
            udao.MostrarUltimoUsuario().forEach((uA) -> {

                Cod_registro = uA.getCod_usuario();
            });

            SalvarMovimentacaoUsuario_Salvar();
            LimparCampos();
            MostrarUsuarios();
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Salvar = new javax.swing.JLabel();
        Pesquisar = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TxtCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TxtLogin = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TxtSenha = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTUsuario = new javax.swing.JTable();
        Codigo = new javax.swing.JRadioButton();
        Descricao = new javax.swing.JRadioButton();
        TxtPesquisa = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        ComboBoxPerfis = new javax.swing.JComboBox<>();
        txtCodPerfil = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCodPrestador = new javax.swing.JTextField();
        ComboBoxPrestador = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sistema de Monitoramento COVID-19");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        jLabel1.setText("Usuários");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Logo menor.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(77, 77, 77)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Salvar.png"))); // NOI18N
        Salvar.setToolTipText("Salvar Alteração Laboratorial");
        Salvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Salvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SalvarMouseClicked(evt);
            }
        });

        Pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Pesquisar.png"))); // NOI18N
        Pesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Fechar.png"))); // NOI18N
        jLabel4.setToolTipText("Fechar Tela");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Código");

        TxtCodigo.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Login");

        TxtLogin.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Senha");

        TxtSenha.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jTUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Usuário", "Perfil"
            }
        ));
        jScrollPane1.setViewportView(jTUsuario);

        Codigo.setText("Código");

        Descricao.setText("Descrição");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Perfil do Usuário");

        ComboBoxPerfis.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ComboBoxPerfis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxPerfisActionPerformed(evt);
            }
        });

        txtCodPerfil.setEditable(false);
        txtCodPerfil.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Prestador");

        txtCodPrestador.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        ComboBoxPrestador.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ComboBoxPrestador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxPrestadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtLogin)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(TxtSenha)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCodPrestador, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboBoxPrestador, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Codigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Descricao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtCodPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboBoxPerfis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jLabel8))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Pesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Salvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ComboBoxPerfis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodPrestador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxPrestador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Codigo)
                    .addComponent(Descricao)
                    .addComponent(TxtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void SalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalvarMouseClicked
        Salvar();
    }//GEN-LAST:event_SalvarMouseClicked

    private void ComboBoxPerfisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxPerfisActionPerformed
        PerfilUsuario a = (PerfilUsuario) ComboBoxPerfis.getSelectedItem();
        txtCodPerfil.setText(Integer.toString(a.getCod_Perfil()).trim());        
    }//GEN-LAST:event_ComboBoxPerfisActionPerformed

    private void ComboBoxPrestadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxPrestadorActionPerformed
       Medico m = (Medico) ComboBoxPrestador.getSelectedItem();
       txtCodPrestador.setText(Integer.toString(m.getCod_medico()).trim());
    }//GEN-LAST:event_ComboBoxPrestadorActionPerformed

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
            java.util.logging.Logger.getLogger(TelaGerenciarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaGerenciarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaGerenciarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaGerenciarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaGerenciarUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Codigo;
    private javax.swing.JComboBox<Object> ComboBoxPerfis;
    private javax.swing.JComboBox<Object> ComboBoxPrestador;
    private javax.swing.JRadioButton Descricao;
    private javax.swing.JLabel Pesquisar;
    private javax.swing.JLabel Salvar;
    private javax.swing.JTextField TxtCodigo;
    private javax.swing.JTextField TxtLogin;
    private javax.swing.JTextField TxtPesquisa;
    private javax.swing.JPasswordField TxtSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTUsuario;
    private javax.swing.JTextField txtCodPerfil;
    private javax.swing.JTextField txtCodPrestador;
    // End of variables declaration//GEN-END:variables
}
