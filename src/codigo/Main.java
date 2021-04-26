/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author aloso
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    
    public static String cadena;
    File archivo;
    FileInputStream input;
    FileOutputStream output;
    Boolean error;
    TablaSemantico t = new TablaSemantico();
    
    public Main() {
        archivo = new File("Codigo.txt");
        initComponents();
        setLocationRelativeTo(null);
        lexico.setEditable(false);
        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        }; 
        
        t.setVisible(true);
        
        // Columnas de la tabla
//        modelo.addColumn("Tipo");
//        modelo.addColumn("Variable");
//        modelo.addColumn("Valor");
//        modelo.addColumn("Unica");
//        modelo.addColumn("Inicializada");
//        variables.setModel(modelo);
//        JTableHeader header = variables.getTableHeader(); 
//        header.setDefaultRenderer(new Encabezado());
//        variables.setRowHeight(26);
//        variables.setTableHeader(header);
        NumeroLinea linea = new NumeroLinea(fuente);
        jScrollPane2.setRowHeaderView(linea);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        fuente = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        lexico = new javax.swing.JTextArea();
        btnCarga = new javax.swing.JButton();
        btnLexico = new javax.swing.JButton();
        btnGuarda = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        sintactico = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnLimpia = new javax.swing.JButton();
        btnSintactico = new javax.swing.JButton();
        btnAnaliza1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        semantico = new javax.swing.JTextArea();
        btnAnaliza2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        objeto = new javax.swing.JTextArea();
        btnAnaliza3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(162, 217, 245));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fuente.setColumns(20);
        fuente.setFont(new java.awt.Font("SansSerif", 0, 17)); // NOI18N
        fuente.setRows(5);
        jScrollPane2.setViewportView(fuente);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 570, 550));

        lexico.setColumns(20);
        lexico.setFont(new java.awt.Font("SansSerif", 0, 17)); // NOI18N
        lexico.setRows(5);
        jScrollPane3.setViewportView(lexico);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 570, 570, 150));

        btnCarga.setBackground(new java.awt.Color(255, 255, 255));
        btnCarga.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        btnCarga.setForeground(new java.awt.Color(0, 51, 102));
        btnCarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/2302314-32.png"))); // NOI18N
        btnCarga.setToolTipText("Cargar");
        btnCarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargaActionPerformed(evt);
            }
        });
        jPanel1.add(btnCarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        btnLexico.setBackground(new java.awt.Color(255, 255, 255));
        btnLexico.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        btnLexico.setForeground(new java.awt.Color(0, 51, 102));
        btnLexico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1312838-32 (1).png"))); // NOI18N
        btnLexico.setToolTipText("Léxico");
        btnLexico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLexicoActionPerformed(evt);
            }
        });
        jPanel1.add(btnLexico, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 610, 60, -1));

        btnGuarda.setBackground(new java.awt.Color(255, 255, 255));
        btnGuarda.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        btnGuarda.setForeground(new java.awt.Color(0, 51, 102));
        btnGuarda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/2258465-32.png"))); // NOI18N
        btnGuarda.setToolTipText("Guardar");
        btnGuarda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardaActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuarda, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        sintactico.setColumns(20);
        sintactico.setFont(new java.awt.Font("SansSerif", 0, 17)); // NOI18N
        sintactico.setRows(5);
        jScrollPane4.setViewportView(sintactico);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 0, 570, 230));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 102));
        jLabel5.setText("Léxico");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 570, 80, -1));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 102));
        jLabel8.setText("Sintáctico");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 120, -1));

        btnLimpia.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpia.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        btnLimpia.setForeground(new java.awt.Color(0, 51, 102));
        btnLimpia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/6973355-32.png"))); // NOI18N
        btnLimpia.setToolTipText("Limpiar");
        btnLimpia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiaActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        btnSintactico.setBackground(new java.awt.Color(255, 255, 255));
        btnSintactico.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        btnSintactico.setForeground(new java.awt.Color(0, 51, 102));
        btnSintactico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/3209355-32.png"))); // NOI18N
        btnSintactico.setToolTipText("Sintáctico");
        btnSintactico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSintacticoActionPerformed(evt);
            }
        });
        jPanel1.add(btnSintactico, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, -1, -1));

        btnAnaliza1.setBackground(new java.awt.Color(255, 255, 255));
        btnAnaliza1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        btnAnaliza1.setForeground(new java.awt.Color(0, 51, 102));
        btnAnaliza1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sem.png"))); // NOI18N
        btnAnaliza1.setToolTipText("Semantico");
        btnAnaliza1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnaliza1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnAnaliza1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 280, -1, -1));

        semantico.setColumns(20);
        semantico.setFont(new java.awt.Font("SansSerif", 0, 17)); // NOI18N
        semantico.setRows(5);
        jScrollPane5.setViewportView(semantico);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 240, 570, 240));

        btnAnaliza2.setBackground(new java.awt.Color(255, 255, 255));
        btnAnaliza2.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        btnAnaliza2.setForeground(new java.awt.Color(0, 51, 102));
        btnAnaliza2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/run.png"))); // NOI18N
        btnAnaliza2.setToolTipText("Ejecuta");
        btnAnaliza2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnaliza2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnAnaliza2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 102));
        jLabel10.setText("Intermedio");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 490, 130, -1));

        objeto.setColumns(20);
        objeto.setFont(new java.awt.Font("SansSerif", 0, 17)); // NOI18N
        objeto.setRows(5);
        jScrollPane6.setViewportView(objeto);

        jPanel1.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 490, 570, 240));

        btnAnaliza3.setBackground(new java.awt.Color(255, 255, 255));
        btnAnaliza3.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        btnAnaliza3.setForeground(new java.awt.Color(0, 51, 102));
        btnAnaliza3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sem.png"))); // NOI18N
        btnAnaliza3.setToolTipText("Semantico");
        btnAnaliza3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnaliza3ActionPerformed(evt);
            }
        });
        jPanel1.add(btnAnaliza3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 520, -1, 50));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 102));
        jLabel11.setText("Semántico");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 240, 130, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1360, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 733, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargaActionPerformed
        String a = "";
        try {
            input = new FileInputStream(archivo);
            int letra;
            while((letra = input.read()) != -1){
                char caracter = (char) letra;
                a += caracter;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (a.length() == 0) {
            JOptionPane.showMessageDialog(null, "El archivo de texto se encuentra vacío", "Inténtelo de nuevo", JOptionPane.INFORMATION_MESSAGE);
            lexico.setText("");
        } else {
            fuente.setText(a);            
        }
    }//GEN-LAST:event_btnCargaActionPerformed
    
    public static ArrayList<TablaSimbolos> lexema = new ArrayList<TablaSimbolos>();
    
    private void btnLexicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLexicoActionPerformed
        lexico();
    }//GEN-LAST:event_btnLexicoActionPerformed

    private void btnGuardaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardaActionPerformed
        try {
            output  = new FileOutputStream(archivo);
            byte[] txt = fuente.getText().getBytes();
            output.write(txt);
            JOptionPane.showMessageDialog(null, "Se ha guardado el programa fuente", "Escritura exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardaActionPerformed

    private void btnLimpiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiaActionPerformed
        fuente.setText(null);
        lexico.setText(null);
        sintactico.setText(null);

    }//GEN-LAST:event_btnLimpiaActionPerformed

    public static void notificar_er(String cad){
        sintactico.append(cad+"\n\n");
    }
    
    private void btnSintacticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSintacticoActionPerformed
        sintactico();
    }//GEN-LAST:event_btnSintacticoActionPerformed

    private void btnAnaliza1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnaliza1ActionPerformed
        // TODO add your handling code here:
        sintactico();
        semantico();
    }//GEN-LAST:event_btnAnaliza1ActionPerformed
    
    boolean l = true;
    
    private void btnAnaliza2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnaliza2ActionPerformed
        // TODO add your handling code here:
        lexico();
        Intermedio.operaciones = "";
        sintactico();
        semantico();
        Intermedio.temp  = 0;
        
        
    }//GEN-LAST:event_btnAnaliza2ActionPerformed

    private void btnAnaliza3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnaliza3ActionPerformed
        objeto.setText("");
//        Intermedio.intermedio(lexema);
        objeto.setText(Intermedio.operaciones);
    }//GEN-LAST:event_btnAnaliza3ActionPerformed
        
    private void lexico(){
        l= true;
        String expresion = fuente.getText() + " ";
        if (expresion.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "No se ha escrito el codigo fuente", "Lectura exitosa", JOptionPane.INFORMATION_MESSAGE);
            lexico.setText("");
        } else {
            cadena = "";
            if (AnalisisLexico.separa("", expresion)) {
                System.out.println("si");
                lexico.setText(cadena);
                lexico.setForeground(new Color(25, 111, 61));
            }else{
                System.out.println("no");
                lexico.setText(cadena);
                lexico.setForeground(new Color(25, 111, 61));
            }
        }
    }
    private void sintactico(){
        String expresion = fuente.getText() + " ";
        sintactico.setText("");
        lexema.clear();
        if (expresion.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "No se ha escrito el codigo fuente", "Lectura exitosa", JOptionPane.INFORMATION_MESSAGE);
            lexico.setText("");
        } else {
            String ST = fuente.getText();
            Sintax s = new Sintax(new codigo.LexerCup(new StringReader(ST)));
            try {
                s.parse();
                if (sintactico.getText().equals("")){
                    sintactico.append("Análisis realizado correctamente");
                    sintactico.setForeground(new Color(25, 111, 61));
                } else {
                    sintactico.append("Análisis realizado correctamente");
                    sintactico.setForeground(Color.RED);
                }
            } catch (Exception ex) {
//                Symbol sym = s.getS();
//                sintactico.setText("Error de sintaxis, en la línea: " + (sym.right + 1)+  "\n\nPalabra no esperada: \"" + sym.value + "\"");
//                sintactico.setForeground(Color.red);
            }
        }
    }
    private void semantico(){
        semantico.setText("");
        String expresion = fuente.getText() + " ";
        if (expresion.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "No se ha escrito el codigo fuente", "Lectura exitosa", JOptionPane.INFORMATION_MESSAGE);
            semantico.setText("");
        } else {
            AnalisisSemantico sem = new AnalisisSemantico();
            sem.mensajesError = "";
            sem.analizar(lexema);
            if (sem.mensajesError.equals("")) {
                semantico.append("Análisis realizado correctamente");
                semantico.setForeground(new Color(25, 111, 61));
            } else{
                semantico.setForeground(Color.RED);
                semantico.setText(sem.mensajesError);
            }
            DefaultTableModel modelo = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            }; 

            // Columnas de la tabla
            modelo.addColumn("Tipo");
            modelo.addColumn("Variable");
            modelo.addColumn("Valor");
            modelo.addColumn("Unica");
            modelo.addColumn("Inicializada");
            String unica, inicializada;
            for (int i = 0; i < sem.tablaSimbolos.size(); i++) {
                Object fila [] = new Object[5];
                fila[0] = sem.tablaSimbolos.get(i).getTipo();
                fila[1] = sem.tablaSimbolos.get(i).getVariable();
                fila[2] = sem.tablaSimbolos.get(i).getValor();
                if (sem.tablaSimbolos.get(i).isUnica()) {
                    unica = "Si";
                } else {
                    unica = "No";
                }
                fila[3] = unica;
                if (sem.tablaSimbolos.get(i).isInicializada()) {
                    inicializada = "Si";
                } else {
                    inicializada = "No";
                }
                fila[4] = inicializada;
                modelo.addRow(fila);
            }
            t.variables.setModel(modelo);
        }   
    }
    
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnaliza1;
    private javax.swing.JButton btnAnaliza2;
    private javax.swing.JButton btnAnaliza3;
    private javax.swing.JButton btnCarga;
    private javax.swing.JButton btnGuarda;
    private javax.swing.JButton btnLexico;
    private javax.swing.JButton btnLimpia;
    private javax.swing.JButton btnSintactico;
    private javax.swing.JTextArea fuente;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea lexico;
    public static javax.swing.JTextArea objeto;
    public static javax.swing.JTextArea semantico;
    private static javax.swing.JTextArea sintactico;
    // End of variables declaration//GEN-END:variables
}
