/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paineis;

import ClassesDeEntidades.Funcionario;
import ClassesJPAController.FuncionarioJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.table.DefaultTableModel;

public class list_atendente extends javax.swing.JPanel {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("libraryPU");
    FuncionarioJpaController funcionarioJpa;

    List<Funcionario> list = null;

    public list_atendente() {
        initComponents();
        atualizarTabela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Tabela = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        btn_buscar = new javax.swing.JButton();

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
        jScrollPane1.setViewportView(Tabela);

        jLabel1.setText("Nome:");

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        btn_buscar.setText("Buscar");
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_buscar))
                .addGap(67, 67, 67)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(btn_buscar)
                .addGap(296, 296, 296))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        Classes.Funcionario f = new Classes.Funcionario();
        try {
            if (txtNome.getText().isEmpty()) {
                list = f.listartodosfuncionarios();
                listarbusca();
            } else {
                list = f.listarFuncionarios(txtNome.getText());
                listarbusca();
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabela;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtNome;
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

    public void listarbusca() {
        funcionarioJpa = new FuncionarioJpaController(emf);
        String[] col = {"IdFuncionario", "Nome", "CPF", "Idade", "Telefone", "Tipo", "User", "Pass", "IdBiblioteca"};
        DefaultTableModel modelo = new DefaultTableModel(col, 0);
        for (Funcionario p : list) {
            modelo.addRow(new String[]{String.valueOf(p.getIdFuncionario()), p.getNome(), String.valueOf(p.getCpf()), String.valueOf(p.getIdade()), String.valueOf(p.getTelefone()),p.getTipo(), p.getUser(), String.valueOf(p.getPass()), String.valueOf(p.getIdBiblioteca().getIdBiblioteca())});
        }
        Tabela.setModel(modelo);

    }
}