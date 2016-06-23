/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paineis;

import ClassesDeEntidades.Biblioteca;
import ClassesDeEntidades.Funcionario;
import ClassesJPAController.BibliotecaJpaController;
import ClassesJPAController.FuncionarioJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Guilherme
 */
public class delete_atendente extends javax.swing.JPanel {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("libraryPU");
    FuncionarioJpaController funcionarioJpa;
    String id = null;

    public delete_atendente() {
        initComponents();
        atualizarTabela();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Tabela = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCPF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtIdade = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtcargo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtuser = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtpass = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtidbiblio = new javax.swing.JTextField();
        btn_excluir = new javax.swing.JButton();

        Tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        Tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabela);

        jLabel1.setText("Id Funcionario:");

        txtId.setEnabled(false);

        jLabel2.setText("Nome:");

        txtNome.setEnabled(false);

        jLabel3.setText("CPF:");

        txtCPF.setEnabled(false);

        jLabel4.setText("Idade:");

        txtIdade.setEnabled(false);

        jLabel5.setText("Telefone:");

        txtTelefone.setEnabled(false);

        jLabel6.setText("Cargo:");

        txtcargo.setEnabled(false);

        jLabel7.setText("User:");

        txtuser.setEnabled(false);

        jLabel8.setText("Pass:");

        txtpass.setEnabled(false);

        jLabel9.setText("IdBiblioteca:");

        txtidbiblio.setEnabled(false);

        btn_excluir.setText("Excluir");
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel4))
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                .addComponent(txtCPF))
                            .addComponent(txtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtpass, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                .addComponent(txtuser, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtcargo, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTelefone, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(txtidbiblio, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_excluir)
                        .addGap(59, 59, 59)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtcargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtidbiblio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_excluir)))
                .addGap(106, 106, 106))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        try {
            if (txtId.getText().isEmpty()) {
                IllegalArgumentException erro = new IllegalArgumentException();
                throw erro;
            } else {
                funcionarioJpa.destroy(Integer.parseInt(txtId.getText()));
                atualizarTabela();
                limpar();
                JOptionPane.showMessageDialog(null, "Item excluido com sucesso");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nenhum funcionario selecionado");
        }
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void TabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaMouseClicked

        try {
            int linha = Tabela.getSelectedRow();
            id = Tabela.getModel().getValueAt(linha, 0).toString();
            String id = Tabela.getModel().getValueAt(linha, 0).toString();
            String nome = Tabela.getModel().getValueAt(linha, 1).toString();
            String cpf = Tabela.getModel().getValueAt(linha, 2).toString();
            String idade = Tabela.getModel().getValueAt(linha, 3).toString();
            String tel = Tabela.getModel().getValueAt(linha, 4).toString();
            String tipo = Tabela.getModel().getValueAt(linha, 5).toString();
            String user = Tabela.getModel().getValueAt(linha, 6).toString();
            String pass = Tabela.getModel().getValueAt(linha, 7).toString();
            String biblio = Tabela.getModel().getValueAt(linha, 8).toString();

            txtId.setText(id);
            txtNome.setText(nome);
            txtCPF.setText(cpf);
            txtIdade.setText(idade);
            txtTelefone.setText(tel);

            txtcargo.setText(tipo);

            txtuser.setText(user);
            txtpass.setText(pass);

            txtidbiblio.setText(biblio);

        } catch (Exception e) {
        }

    }//GEN-LAST:event_TabelaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabela;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCPF;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdade;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtTelefone;
    private javax.swing.JTextField txtcargo;
    private javax.swing.JTextField txtidbiblio;
    private javax.swing.JTextField txtpass;
    private javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables

    public void atualizarTabela() {
        List<Funcionario> lista = null;
        funcionarioJpa = new FuncionarioJpaController(emf);
        lista = funcionarioJpa.findFuncionarioEntities();
        String[] col = {"IdFuncionario", "Nome", "CPF", "Idade", "Telefone", "Tipo", "User", "Pass", "IdBiblioteca"};
        DefaultTableModel modelo = new DefaultTableModel(col, 0);
        for (Funcionario p : lista) {
            modelo.addRow(new String[]{String.valueOf(p.getIdFuncionario()), p.getNome(), String.valueOf(p.getCpf()), String.valueOf(p.getIdade()), String.valueOf(p.getTelefone()), p.getTipo(), p.getUser(), String.valueOf(p.getPass()), String.valueOf(p.getIdBiblioteca().getIdBiblioteca())});
        }
        Tabela.setModel(modelo);

    }

    private void limpar() {
        txtCPF.setText("");
        txtId.setText("");
        txtIdade.setText("");
        txtNome.setText("");
        txtTelefone.setText("");
        txtcargo.setText("");
        txtidbiblio.setText("");
        txtpass.setText("");
        txtuser.setText("");
    }
}
