/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paineis;

import Classes.Funcionario;
import ClassesDeEntidades.Biblioteca;
import ClassesDeEntidades.Livro;
import ClassesJPAController.LivroJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Guilherme
 */
public class delete_livro extends javax.swing.JPanel {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("libraryPU");
    LivroJpaController livrojpa;
    String id = null;

    public delete_livro() {
        initComponents();
        atualizarTabela();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txt_idlivro = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtnomelivro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txteditora = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtedicao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtarea = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtano = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtidbiblio = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabela = new javax.swing.JTable();
        txtexluir = new javax.swing.JButton();

        jLabel1.setText("id Livro:");

        txt_idlivro.setEnabled(false);

        jLabel2.setText("Nome Livro: ");

        txtnomelivro.setEnabled(false);

        jLabel3.setText("Editora:");

        txteditora.setEnabled(false);

        jLabel4.setText("Edição:");

        txtedicao.setEnabled(false);

        jLabel5.setText("Area:");

        txtarea.setEnabled(false);

        jLabel6.setText("Ano:");

        txtano.setEnabled(false);

        jLabel7.setText("id Biblioteca:");

        txtidbiblio.setEnabled(false);

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

        txtexluir.setText("Excluir Item");
        txtexluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtexluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_idlivro, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtnomelivro, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                                .addComponent(txtedicao)
                                .addComponent(txteditora))
                            .addComponent(txtarea, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtano, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtidbiblio, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(txtexluir)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_idlivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnomelivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txteditora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtedicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtidbiblio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtexluir))
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaMouseClicked
        try {
            int linha = Tabela.getSelectedRow();
            id = Tabela.getModel().getValueAt(linha, 0).toString();
            String id = Tabela.getModel().getValueAt(linha, 0).toString();
            String nome = Tabela.getModel().getValueAt(linha, 1).toString();
            String editora = Tabela.getModel().getValueAt(linha, 2).toString();
            String edicao = Tabela.getModel().getValueAt(linha, 3).toString();
            String area = Tabela.getModel().getValueAt(linha, 4).toString();
            String ano = Tabela.getModel().getValueAt(linha, 5).toString();
            String idbilio = Tabela.getModel().getValueAt(linha, 6).toString();

            txt_idlivro.setText(id);
            txtnomelivro.setText(nome);
            txteditora.setText(editora);
            txtedicao.setText(edicao);
            txtarea.setText(area);
            txtano.setText(ano);
            txtidbiblio.setText(idbilio);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "linha inválida!");
        }
    }//GEN-LAST:event_TabelaMouseClicked

    private void txtexluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtexluirActionPerformed
        try {
            if (txt_idlivro.getText().isEmpty()) {
                IllegalArgumentException erro = new IllegalArgumentException();
                throw erro;
            } else {
                Funcionario f = new Funcionario();
                f.deletelivro(Integer.parseInt(txt_idlivro.getText()));
                atualizarTabela();
                txt_idlivro.setText("");
                txtnomelivro.setText("");
                txteditora.setText("");
                txtedicao.setText("");
                txtarea.setText("");
                txtano.setText("");
                txtidbiblio.setText("");
                JOptionPane.showMessageDialog(null, "Livro excluido com sucesso");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nenhum Livro Selecionado");
        }
    }//GEN-LAST:event_txtexluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabela;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_idlivro;
    private javax.swing.JTextField txtano;
    private javax.swing.JTextField txtarea;
    private javax.swing.JTextField txtedicao;
    private javax.swing.JTextField txteditora;
    private javax.swing.JButton txtexluir;
    private javax.swing.JTextField txtidbiblio;
    private javax.swing.JTextField txtnomelivro;
    // End of variables declaration//GEN-END:variables

    public void atualizarTabela() {
        List<Livro> lista = null;
        livrojpa = new LivroJpaController(emf);
        lista = livrojpa.findLivroEntities();
        String[] col = {"IdLivro", "Nome Livro", "Editora", "Edição", "Area", "Ano", "idBiblioteca"};
        DefaultTableModel modelo = new DefaultTableModel(col, 0);
        for (Livro p : lista) {
            modelo.addRow(new String[]{String.valueOf(p.getIdLivro()), p.getNomeLivro(), p.getEditora(), p.getEdicao(), String.valueOf(p.getArea()), String.valueOf(p.getAno()), String.valueOf(p.getIdBiblioteca().getIdBiblioteca())});
        }
        Tabela.setModel(modelo);

    }
}
