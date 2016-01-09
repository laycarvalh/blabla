package cc.labiras.serial;

public class Dados{
	private int elemX;
	private int elemY;
	
	public Dados(int elemX, String elemY){
		setElemX(elemX);
		setElemY(elemY.to);
	}
	
	public int getElemX() {
		return elemX;
	}
	public void setElemX(int elemX) {
		this.elemX = elemX;
	}
	public int getElemY() {
		return elemY;
	}
	public void setElemY(int elemY) {
		this.elemY = elemY;
	}
	
	
	

}










/*
import Labiras2.Calculos;
import Labiras2.RelacaoXFuncao;
import Labiras2.RelacaoXFuncao_1;
import cc.labiras.cargaIsoestatica.GerarRelatorio;
import static cc.labiras.gui.JanelaPrincipal.TIPO_MATERIAL;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.pow;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class JanelaCalculoTensoes extends javax.swing.JFrame {

    protected static final NumberFormat NUMBER_FORMAT = NumberFormat.getNumberInstance();
    public static JanelaCalculoTensoes TENSOES = null;

    protected JFreeChart graficoTensao;
    
    protected JanelaPrincipal janelaPrincipal;
    private List<RelacaoXFuncao> listaTensao = new ArrayList();

    private List<RelacaoXFuncao> listaPerigoTensao = new ArrayList();
    
    /**
     *
     */
  /*

    public void setListaTensao(List<RelacaoXFuncao> listaTensao) {
        this.listaTensao = listaTensao;
    }
    public List<RelacaoXFuncao> getListaTensao() {
        return listaTensao;
    }


    public List<RelacaoXFuncao> getListaPerigoTensao() {
        return listaPerigoTensao;
    }


    static {
        NUMBER_FORMAT.setMaximumFractionDigits(4);
    }

    public JanelaCalculoTensoes(JanelaPrincipal janelaPrincipal) {
        initComponents();
        atualizarValores();
        this.janelaPrincipal = janelaPrincipal;
        JanelaPrincipal.TIPO_MATERIAL = comboTipoMaterial.getSelectedIndex();
        comboTipoMaterial.setSelectedIndex(JanelaPrincipal.TIPO_MATERIAL);
        
    }

    private void atualizarValores() {
        TENSOES = this;
        JanelaPrincipal.TIPO_MATERIAL = comboTipoMaterial.getSelectedIndex();
        comboTipoMaterial.setSelectedIndex(JanelaPrincipal.TIPO_MATERIAL);
        System.out.println("Atualizou os valores do tipo do tipo de material");

        double resistencia = 0.0;
        switch (JanelaPrincipal.TIPO_MATERIAL) {
            case 0:
                resistencia = 210 * pow(10, 6);
                break;
            case 1:
                resistencia = 290 * pow(10, 6);
                break;
            case 2:
                resistencia = 241 * pow(10, 6);
                break;

        }

        System.out.println("Material escolhido: " + JanelaPrincipal.TIPO_MATERIAL);

        List<RelacaoXFuncao> calculoTensoes = Calculos.calculoDasTensoes(JanelaPrincipal.GEOMETRIA.getCentroMassa(), JanelaPrincipal.GEOMETRIA.getMomentoInercia(), JanelaPrincipal.CARREGAMENTO.getFuncaoMomentoFletor(), 0, JanelaPrincipal.CARREGAMENTO.getComprimento());

       
        setListaTensao(calculoTensoes);

        DefaultCategoryDataset datasetGrafico = new DefaultCategoryDataset();

        DefaultTableModel model = (DefaultTableModel) tabelaTensao.getModel();

        model.setDataVector(null, new String[]{"X (m)", "Tensão Calculada (Pa)"});

        DefaultTableModel perigo = (DefaultTableModel) tabelaPontosDeRisco.getModel();
        perigo.setDataVector(null, new String[]{"X (m)", "Tensão Calculada (Pa)"});

        for (RelacaoXFuncao relacao : calculoTensoes) {
            model.addRow(new Object[]{NUMBER_FORMAT.format(relacao.getX()), NUMBER_FORMAT.format(relacao.getResultadoFuncao())});
            datasetGrafico.addValue(relacao.getResultadoFuncao(), "Tensão Calculada", NUMBER_FORMAT.format(relacao.getX()));
            if (relacao.getResultadoFuncao() > resistencia) {
                perigo.addRow(new Object[]{NUMBER_FORMAT.format(relacao.getX()), NUMBER_FORMAT.format(relacao.getResultadoFuncao())});
                datasetGrafico.addValue(relacao.getResultadoFuncao(), "Pontos de Risco", NUMBER_FORMAT.format(relacao.getX()));
                listaPerigoTensao.add(relacao);
            }
        }

        graficoTensao = ChartFactory.createLineChart("Tensão Calculada ", "X (m)", "Tensão Calculada (Pa)", datasetGrafico, PlotOrientation.VERTICAL, true, true, false);
        ((ChartPanel) panelGrafico).setChart(graficoTensao);

       


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

/*
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolbarMain = new javax.swing.JToolBar();
        btnToolbarVoltar = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        labelToolbarTipoDeMaterial = new javax.swing.JLabel();
        comboTipoMaterial = new javax.swing.JComboBox();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        btnToolbarCalcular = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        panelGrafico = new ChartPanel(null);
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        labelSemento1Tensao = new javax.swing.JLabel();
        scrollPageSegmento1TabelaTensao = new javax.swing.JScrollPane();
        tabelaTensao = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        labelSemento1PontosDeRisco = new javax.swing.JLabel();
        scrollPageSegmento1TabelaPontosDeRisco = new javax.swing.JScrollPane();
        tabelaPontosDeRisco = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArquivo = new javax.swing.JMenu();
        menuItemNovo = new javax.swing.JMenuItem();
        menuItemAbrir = new javax.swing.JMenuItem();
        menuItemSalvar = new javax.swing.JMenuItem();
        menuItemSalvarComo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuItemImprimir = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuItemSair = new javax.swing.JMenuItem();
        menuAjuda = new javax.swing.JMenu();
        menuItemSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cálculo das Tensões e Análise Isoestática - Tensão Calculada");
        setResizable(false);

        toolbarMain.setFloatable(false);
        toolbarMain.setRollover(true);

        btnToolbarVoltar.setFont(btnToolbarVoltar.getFont().deriveFont(btnToolbarVoltar.getFont().getStyle() | java.awt.Font.BOLD));
        btnToolbarVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cc/labiras/gui/icones/document-back.png"))); // NOI18N
        btnToolbarVoltar.setText("Voltar");
        btnToolbarVoltar.setFocusable(false);
        btnToolbarVoltar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnToolbarVoltar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnToolbarVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToolbarVoltarActionPerformed(evt);
            }
        });
        toolbarMain.add(btnToolbarVoltar);
        toolbarMain.add(jSeparator6);

        labelToolbarTipoDeMaterial.setText("Tipo de Material:");
        toolbarMain.add(labelToolbarTipoDeMaterial);

        comboTipoMaterial.setFont(comboTipoMaterial.getFont().deriveFont(comboTipoMaterial.getFont().getSize()+5f));
        comboTipoMaterial.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aço 1020 - Laminado a quente", "Aço 1040 - Laminado a quente", "Liga de Alumínio 6063-75" }));
        comboTipoMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoMaterialActionPerformed(evt);
            }
        });
        toolbarMain.add(comboTipoMaterial);
        toolbarMain.add(jSeparator5);

        btnToolbarCalcular.setFont(btnToolbarCalcular.getFont().deriveFont(btnToolbarCalcular.getFont().getStyle() | java.awt.Font.BOLD));
        btnToolbarCalcular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cc/labiras/gui/icones/media-playback-start.png"))); // NOI18N
        btnToolbarCalcular.setText("Recalcular");
        btnToolbarCalcular.setFocusable(false);
        btnToolbarCalcular.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnToolbarCalcular.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnToolbarCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToolbarCalcularActionPerformed(evt);
            }
        });
        toolbarMain.add(btnToolbarCalcular);

        jSplitPane1.setBorder(null);
        jSplitPane1.setDividerLocation(280);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        javax.swing.GroupLayout panelGraficoLayout = new javax.swing.GroupLayout(panelGrafico);
        panelGrafico.setLayout(panelGraficoLayout);
        panelGraficoLayout.setHorizontalGroup(
            panelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 795, Short.MAX_VALUE)
        );
        panelGraficoLayout.setVerticalGroup(
            panelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 282, Short.MAX_VALUE)
        );

        jSplitPane1.setBottomComponent(panelGrafico);

        jSplitPane2.setBorder(null);
        jSplitPane2.setDividerLocation(400);

        labelSemento1Tensao.setFont(labelSemento1Tensao.getFont().deriveFont(labelSemento1Tensao.getFont().getSize()+4f));
        labelSemento1Tensao.setText("Tensão Calculada");

        tabelaTensao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "X (m)", "Tensão Calculada (Pa)"
            }
        ));
        scrollPageSegmento1TabelaTensao.setViewportView(tabelaTensao);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelSemento1Tensao)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(scrollPageSegmento1TabelaTensao, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelSemento1Tensao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPageSegmento1TabelaTensao, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane2.setLeftComponent(jPanel2);

        labelSemento1PontosDeRisco.setFont(labelSemento1PontosDeRisco.getFont().deriveFont(labelSemento1PontosDeRisco.getFont().getSize()+4f));
        labelSemento1PontosDeRisco.setText("Pontos de Risco de Falha Estrutural");

        tabelaPontosDeRisco.setForeground(new java.awt.Color(204, 0, 0));
        tabelaPontosDeRisco.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "X (m)", "Tensão Calculada (Pa)"
            }
        ));
        scrollPageSegmento1TabelaPontosDeRisco.setViewportView(tabelaPontosDeRisco);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(labelSemento1PontosDeRisco)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(scrollPageSegmento1TabelaPontosDeRisco, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelSemento1PontosDeRisco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPageSegmento1TabelaPontosDeRisco, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane2.setRightComponent(jPanel4);

        jSplitPane1.setLeftComponent(jSplitPane2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("TENSÃO", jPanel5);

        menuArquivo.setText("Arquivo");

        menuItemNovo.setText("Novo");
        menuItemNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemNovoActionPerformed(evt);
            }
        });
        menuArquivo.add(menuItemNovo);

        menuItemAbrir.setText("Abrir...");
        menuItemAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAbrirActionPerformed(evt);
            }
        });
        menuArquivo.add(menuItemAbrir);

        menuItemSalvar.setText("Salvar");
        menuItemSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSalvarActionPerformed(evt);
            }
        });
        menuArquivo.add(menuItemSalvar);

        menuItemSalvarComo.setText("Salvar Como...");
        menuItemSalvarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSalvarComoActionPerformed(evt);
            }
        });
        menuArquivo.add(menuItemSalvarComo);
        menuArquivo.add(jSeparator1);

        menuItemImprimir.setText("Imprimir");
        menuItemImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemImprimirActionPerformed(evt);
            }
        });
        menuArquivo.add(menuItemImprimir);
        menuArquivo.add(jSeparator2);

        menuItemSair.setText("Sair");
        menuItemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSairActionPerformed(evt);
            }
        });
        menuArquivo.add(menuItemSair);

        jMenuBar1.add(menuArquivo);

        menuAjuda.setText("Ajuda");

        menuItemSobre.setText("Sobre");
        menuItemSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSobreActionPerformed(evt);
            }
        });
        menuAjuda.add(menuItemSobre);

        jMenuBar1.add(menuAjuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbarMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolbarMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemNovoActionPerformed
        this.setVisible(false);
        new JanelaPrincipal().setVisible(true);
    }//GEN-LAST:event_menuItemNovoActionPerformed

    private void menuItemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAbrirActionPerformed
        janelaPrincipal.abrir(evt);
        atualizarValores();
    }//GEN-LAST:event_menuItemAbrirActionPerformed

    private void menuItemSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSalvarActionPerformed
        File arquivo = janelaPrincipal.selecionarArquivo(false);
        if (arquivo != null) {
            janelaPrincipal.salvar(arquivo);
        }
    }//GEN-LAST:event_menuItemSalvarActionPerformed

    private void menuItemSalvarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSalvarComoActionPerformed
        File arquivo = janelaPrincipal.selecionarArquivo(true);
        if (arquivo != null) {
            janelaPrincipal.salvar(arquivo);
        }
    }//GEN-LAST:event_menuItemSalvarComoActionPerformed

    private void menuItemImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemImprimirActionPerformed
        final JFileChooser fileChooser = new JFileChooser();
        final int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                if (!file.getName().toLowerCase().endsWith(".pdf")) {
                    file = new File(file.getCanonicalPath() + ".pdf");
                }
                System.out.println((JanelaPrincipal.TIPO_CARREGAMENTO+" "+ JanelaPrincipal.CARREGAMENTO+" "+ JanelaPrincipal.GEOMETRIA+" "+ JanelaPrincipal.TIPO_MATERIAL +" "+ TENSOES));
                final GerarRelatorio gerarRelatorio = new GerarRelatorio(JanelaPrincipal.TIPO_CARREGAMENTO, JanelaPrincipal.CARREGAMENTO, JanelaPrincipal.GEOMETRIA, JanelaPrincipal.TIPO_MATERIAL, TENSOES);
                gerarRelatorio.montarRelatorio(file);
            } catch (final Exception e) {
                JOptionPane.showMessageDialog(this, "Não foi possível acessar o arquivo. Por favor, tente novamente com outro arquivo.");
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_menuItemImprimirActionPerformed

    private void menuItemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuItemSairActionPerformed

    private void menuItemSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSobreActionPerformed
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaSobre().setVisible(true);
            }
        });
    }//GEN-LAST:event_menuItemSobreActionPerformed
    /*
     ABRIR OU SALVAR
    
     Properties prop = new Properties();
     prop.setProperty("xpto", "123");
     prop.setProperty("abc", "123");
     prop.setProperty("xyz", "aaaaaaaaaaaaaa");
     prop.setProperty("duh", "123123123213");
     prop.setProperty("hadouken", "123123213213213");
        
     try {
     prop.store(new FileWriter("C:\\temp\\arquivo.txt"), "Hadouken!!!!!!!!");
     } catch (IOException ex) {
     Logger.getLogger(JanelaCalculoTensoes.class.getName()).log(Level.SEVERE, null, ex);
     }
        
     prop = new Properties();
     try {
     prop.load(new FileReader("C:\\temp\\arquivo.txt"));
     } catch (Exception ex) {
     Logger.getLogger(JanelaCalculoTensoes.class.getName()).log(Level.SEVERE, null, ex);
     }
        
     JOptionPane.showMessageDialog(null, "Properties = " + prop);
    
    
     
    private void btnToolbarCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToolbarCalcularActionPerformed
        atualizarValores();

    }//GEN-LAST:event_btnToolbarCalcularActionPerformed

    private void btnToolbarVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToolbarVoltarActionPerformed
        this.setVisible(false);
        janelaPrincipal.setVisible(true);
    }//GEN-LAST:event_btnToolbarVoltarActionPerformed

    private void comboTipoMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoMaterialActionPerformed
        TIPO_MATERIAL = comboTipoMaterial.getSelectedIndex();
    }//GEN-LAST:event_comboTipoMaterialActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnToolbarCalcular;
    private javax.swing.JButton btnToolbarVoltar;
    private javax.swing.JComboBox comboTipoMaterial;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelSemento1PontosDeRisco;
    private javax.swing.JLabel labelSemento1Tensao;
    private javax.swing.JLabel labelToolbarTipoDeMaterial;
    private javax.swing.JMenu menuAjuda;
    private javax.swing.JMenu menuArquivo;
    private javax.swing.JMenuItem menuItemAbrir;
    private javax.swing.JMenuItem menuItemImprimir;
    private javax.swing.JMenuItem menuItemNovo;
    private javax.swing.JMenuItem menuItemSair;
    private javax.swing.JMenuItem menuItemSalvar;
    private javax.swing.JMenuItem menuItemSalvarComo;
    private javax.swing.JMenuItem menuItemSobre;
    private javax.swing.JPanel panelGrafico;
    private javax.swing.JScrollPane scrollPageSegmento1TabelaPontosDeRisco;
    private javax.swing.JScrollPane scrollPageSegmento1TabelaTensao;
    private javax.swing.JTable tabelaPontosDeRisco;
    private javax.swing.JTable tabelaTensao;
    private javax.swing.JToolBar toolbarMain;
    // End of variables declaration//GEN-END:variables
}
*/