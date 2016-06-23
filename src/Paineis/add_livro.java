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
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Guilherme
 */
public class add_livro extends javax.swing.JPanel {

    Funcionario f;
    String id = null;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("libraryPU");

    LivroJpaController livrojpa;

    public add_livro() {
        initComponents();
        atualizarTabela();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_nomelivro = new javax.swing.JTextField();
        txt_editora = new javax.swing.JTextField();
        txt_edicao = new javax.swing.JTextField();
        txt_area = new javax.swing.JTextField();
        txt_ano = new javax.swing.JTextField();
        txt_idbiblio = new javax.swing.JTextField();
        btnaddlivro = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabela = new javax.swing.JTable();

        jLabel1.setText("Nome do Livro:");

        jLabel2.setText("Editora:");

        jLabel3.setText("Edição:");

        jLabel4.setText("Area:");

        jLabel5.setText("Ano:");

        jLabel6.setText("id Biblioteca");

        btnaddlivro.setText("Adiconar Livro");
        btnaddlivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddlivroActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_idbiblio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(txt_ano, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnaddlivro))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txt_editora, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txt_edicao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                    .addComponent(txt_area, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nomelivro, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_nomelivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_editora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_edicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_ano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_idbiblio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnaddlivro))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnaddlivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddlivroActionPerformed
        try {
            if (txt_nomelivro.getText().isEmpty() || txt_ano.getText().isEmpty() || txt_area.getText().isEmpty() || txt_edicao.getText().isEmpty() || txt_editora.getText().isEmpty() || txt_idbiblio.getText().isEmpty()) {
                IllegalArgumentException erro = new IllegalArgumentException();
                throw erro;
            } else {
                f = new Funcionario();
                Livro l = new Livro();
                l.setIdLivro(null);
                l.setNomeLivro(txt_nomelivro.getText());
                l.setEdicao(txt_edicao.getText());
                l.setEditora(txt_editora.getText());
                l.setArea(txt_area.getText());
                l.setAno(Integer.parseInt(txt_ano.getText()));
                Biblioteca b = new Biblioteca();
                b.setIdBiblioteca(Integer.parseInt(txt_idbiblio.getText()));
                l.setIdBiblioteca(b);
                f.createlivro(l);
                atualizarTabela();
                txt_ano.setText("");
                txt_area.setText("");
                txt_edicao.setText("");
                txt_editora.setText("");
                txt_idbiblio.setText("");
                txt_nomelivro.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir, ha campos em branco");
        }


    }//GEN-LAST:event_btnaddlivroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabela;
    private javax.swing.JButton btnaddlivro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_ano;
    private javax.swing.JTextField txt_area;
    private javax.swing.JTextField txt_edicao;
    private javax.swing.JTextField txt_editora;
    private javax.swing.JTextField txt_idbiblio;
    private javax.swing.JTextField txt_nomelivro;
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
