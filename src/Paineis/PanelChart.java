/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paineis;

import Classes.Funcionario;
import ClassesDeEntidades.Livro;
import Classes.GerarGrafico;
import ClassesJPAController.LivroJpaController;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Guilherme
 */
public class PanelChart extends javax.swing.JPanel {

    List<Livro> list = null;
    List<GerarGrafico> graf;

    public PanelChart() {
        initComponents();
    }

    private CategoryDataset createDataset() {
        Funcionario f = new Funcionario();
        try {
            list = f.listartodos();//lista com todos os livros
        } catch (Exception e) {
            System.err.println("Problema ao criar lista de livros- FrameGerente");
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        graf = LivroJpaController.grafico(list);
        for (GerarGrafico gr : graf) {
            dataset.setValue(gr.getQtd(), gr.getArea(), gr.getArea());
        }

        return dataset;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 601, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 391, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void gerargrafico() {
        CategoryDataset cds = createDataset();
        JFreeChart chart = ChartFactory.createBarChart3D("LIVROS", "Área", "Quantidade", cds, PlotOrientation.VERTICAL, false, true, true);
        ChartPanel myChartPanel = new ChartPanel(chart, true);
        myChartPanel.setSize(this.getWidth(), this.getHeight());
        myChartPanel.setVisible(true);
        this.removeAll();
        this.add(myChartPanel);
        this.revalidate();
        this.repaint();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
