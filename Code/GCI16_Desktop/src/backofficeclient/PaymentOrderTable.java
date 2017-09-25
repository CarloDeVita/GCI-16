/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backofficeclient;

import backofficeclient.PaymentOrder.Status;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.glass.events.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import pdfgenerator.PDFGenerator;

/**
 *
 * @author carlo
 */
public class PaymentOrderTable extends javax.swing.JFrame {
    List<PaymentOrder> list;
    String session;
    public PaymentOrderTable(String session) {
        initComponents();
        this.session = session;
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
        poTable = new javax.swing.JTable();
        createButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        saveAsSuspendedButton = new javax.swing.JButton();
        saveAsPaidButton = new javax.swing.JButton();
        saveAsNotPertinentButton = new javax.swing.JButton();
        reissueButton = new javax.swing.JButton();
        issueButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        protocolText = new javax.swing.JTextField();
        debtorText = new javax.swing.JTextField();
        filterButton = new javax.swing.JButton();
        yearList = new javax.swing.JComboBox<>();
        trimesterList = new javax.swing.JComboBox<>();
        clearFilterButton = new javax.swing.JButton();
        statusList = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        poTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Protocol", "Debtor", "Year", "Trimester", "Amount", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        poTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                deleteButton.setEnabled(false);
                saveAsSuspendedButton.setEnabled(false);
                saveAsPaidButton.setEnabled(false);
                saveAsNotPertinentButton.setEnabled(false);
                reissueButton.setEnabled(false);
                issueButton.setEnabled(false);
                int rowSel = poTable.getSelectedRow();
                int row;
                if(rowSel != -1)
                row = poTable.convertRowIndexToModel(rowSel);
                else return;
                if(rowSel < poTable.getRowCount() && rowSel>=0){

                    Status status = list.get(row).getStatus();
                    if(status != null){
                        String stat = status.toString();

                        if(stat.equals("NOTIFIED")){
                            saveAsSuspendedButton.setEnabled(true);
                            saveAsPaidButton.setEnabled(true);
                        }
                        else if(stat.equals("NOTISSUED")){
                            deleteButton.setEnabled(true);
                            issueButton.setEnabled(true);
                        }
                        else if(stat.equals("SUSPENDED")){
                            reissueButton.setEnabled(true);
                            saveAsNotPertinentButton.setEnabled(true);
                        }
                    }
                }
            }
        });
        jScrollPane1.setViewportView(poTable);

        createButton.setText("New payment order");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        saveAsSuspendedButton.setText("Save as suspended");
        saveAsSuspendedButton.setEnabled(false);
        saveAsSuspendedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsSuspendedButtonActionPerformed(evt);
            }
        });

        saveAsPaidButton.setText("Save as paid");
        saveAsPaidButton.setEnabled(false);
        saveAsPaidButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsPaidButtonActionPerformed(evt);
            }
        });

        saveAsNotPertinentButton.setText("Save as not pertinent");
        saveAsNotPertinentButton.setEnabled(false);
        saveAsNotPertinentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsNotPertinentButtonActionPerformed(evt);
            }
        });

        reissueButton.setText("Reissue");
        reissueButton.setEnabled(false);
        reissueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reissueButtonActionPerformed(evt);
            }
        });

        issueButton.setText("Issue");
        issueButton.setEnabled(false);
        issueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issueButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Protocol");

        jLabel1.setText("Debtor");

        jLabel3.setText("Year");

        jLabel4.setText("Trimester");

        jLabel6.setText("Status");

        protocolText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                protocolTextActionPerformed(evt);
            }
        });
        protocolText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                protocolTextKeyTyped(evt);
            }
        });

        filterButton.setText("Filter");
        filterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterButtonActionPerformed(evt);
            }
        });

        yearList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "","2001", "2002","2003","2004","2005","2006","2007","2008","2009",
            "2010","2011","2012","2013","2014","2015","2016","2017","2018",}));

trimesterList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "1", "2", "3", "4" }));

clearFilterButton.setText("Clear");
clearFilterButton.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        clearFilterButtonActionPerformed(evt);
    }
    });

    statusList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "NOT ISSUED", "ISSUED", "NOTIFIED","SUSPENDED" }));

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(20, 20, 20))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel1))
                            .addGap(18, 18, 18)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(debtorText)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(protocolText, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(yearList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(clearFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(18, 18, Short.MAX_VALUE)
                    .addComponent(filterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4)
                        .addComponent(jLabel6))
                    .addGap(27, 27, 27)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(statusList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(trimesterList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(55, 55, 55)))))
            .addGap(76, 76, 76))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(15, 15, 15)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(protocolText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2))
            .addGap(18, 18, 18)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(debtorText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(yearList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(filterButton)
                        .addComponent(clearFilterButton)))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(statusList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(trimesterList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
        .addGroup(layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                    .addComponent(saveAsPaidButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saveAsSuspendedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(issueButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(reissueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saveAsNotPertinentButton))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(84, 84, 84))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(createButton)
                        .addComponent(deleteButton))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(saveAsPaidButton)
                        .addComponent(saveAsSuspendedButton))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(saveAsNotPertinentButton)
                        .addComponent(reissueButton))
                    .addGap(18, 18, 18)
                    .addComponent(issueButton)))
            .addGap(33, 33, 33)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        BillTable billTable = new BillTable(this,session);
        if(billTable.setTable())
            billTable.setVisible(true);
    }//GEN-LAST:event_createButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
         int rowSel = poTable.getSelectedRow();
        int row = poTable.convertRowIndexToModel(rowSel);
        try {
            URL url = new URL("http://localhost:8081/GCI16/PaymentOrder?action=delete&paymentOrder="+list.get(row).getId());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Cookie", session);
            connection.connect();
            //HttpURLConnection conn = ServerConnection.executeGet("http://localhost:8081/GCI16/PaymentOrder?action=delete&paymentOrder="+list.get(row).getId());
            int resCode = connection.getResponseCode();
            if(resCode == 200){
                System.out.println("Righe: "+poTable.getModel().getRowCount()+ "oppure: "+ poTable.getRowCount());
                ((DefaultTableModel)poTable.getModel()).removeRow(row);
                System.out.println("Ciao");
                System.out.println("Righe: "+poTable.getModel().getRowCount());
                list.remove(row);
            }else if (resCode == 462){
              JOptionPane.showMessageDialog(this,"Server not available"); 
            }
            
        } catch (IOException ex) {
            Logger.getLogger(BillTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void saveAsSuspendedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsSuspendedButtonActionPerformed
        
        int rowSel = poTable.getSelectedRow();
        int row = poTable.convertRowIndexToModel(rowSel);  
        try{
            URL url = new URL("http://localhost:8081/GCI16/PaymentOrder?action=saveAsSuspended&paymentOrder="+list.get(row).getId());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Cookie", session);
            connection.connect();
 
            int resCode = connection.getResponseCode();
            if(resCode == 200){
                poTable.setValueAt("SUSPENDED", rowSel, 5); /*Modifico la colonna relativa allo stato.*/
                PaymentOrder paymOrd = list.get(row);
                paymOrd.setStatus(Status.SUSPENDED);
            }else if (resCode == 462){
              JOptionPane.showMessageDialog(this,"Server not available"); 
            }
            poTable.getSelectionModel().clearSelection();
        } catch (IOException ex) {
            Logger.getLogger(PaymentOrderTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_saveAsSuspendedButtonActionPerformed

    private void saveAsPaidButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsPaidButtonActionPerformed
        int rowSel = poTable.getSelectedRow();
        int row = poTable.convertRowIndexToModel(rowSel);
        try{
            URL url = new URL("http://localhost:8081/GCI16/PaymentOrder?action=saveAsPaid&paymentOrder="+list.get(row).getId());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Cookie", session);
            connection.connect();    

            int resCode = connection.getResponseCode();
            if(resCode == 200){
                poTable.setValueAt("PAID", rowSel, 5); /*Modifico la colonna relativa allo stato.*/
                PaymentOrder paymOrd = list.get(row);
                paymOrd.setStatus(Status.PAID);
            }else if (resCode == 462){
              JOptionPane.showMessageDialog(this,"Server not available"); 
            }
            poTable.getSelectionModel().clearSelection();
        } catch (IOException ex) {
            Logger.getLogger(PaymentOrderTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_saveAsPaidButtonActionPerformed

    private void saveAsNotPertinentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsNotPertinentButtonActionPerformed
        int rowSel = poTable.getSelectedRow();
        int row = poTable.convertRowIndexToModel(rowSel);
        
        try {
            URL url = new URL("http://localhost:8081/GCI16/PaymentOrder?action=saveAsNotPertinent&paymentOrder="+list.get(row).getId());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Cookie", session);
            connection.connect(); 
            int resCode = connection.getResponseCode();
            if(resCode == 200){
                ((DefaultTableModel)poTable.getModel()).removeRow(row);
                PaymentOrder paymOrd = list.get(row);
                paymOrd.setStatus(Status.NOTPERTINENT);
            }else if (resCode == 462){
              JOptionPane.showMessageDialog(this,"Server not available"); 
            }
            poTable.getSelectionModel().clearSelection();
        } catch (IOException ex) {
            Logger.getLogger(PaymentOrderTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_saveAsNotPertinentButtonActionPerformed

    private void reissueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reissueButtonActionPerformed
        int rowSel = poTable.getSelectedRow();
        int row = poTable.convertRowIndexToModel(rowSel);
        try{
            URL url = new URL("http://localhost:8081/GCI16/PaymentOrder?action=reissue&paymentOrder="+list.get(row).getId());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Cookie", session);
            connection.connect();    

            int resCode = connection.getResponseCode();
            if(resCode == 200){
                poTable.setValueAt("ISSUED", rowSel, 5); /*Modifico la colonna relativa allo stato.*/
                PaymentOrder paymOrd = list.get(row);
                paymOrd.setStatus(Status.ISSUED);
            }else if (resCode == 462){
              JOptionPane.showMessageDialog(this,"Server not available"); 
            }
            poTable.getSelectionModel().clearSelection();
        } catch (IOException ex) {
            Logger.getLogger(PaymentOrderTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_reissueButtonActionPerformed

    private void issueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issueButtonActionPerformed
        int rowSel = poTable.getSelectedRow();
        int row = poTable.convertRowIndexToModel(rowSel);
        //HttpURLConnection conn = ServerConnection.executeGet("http://localhost:8081/GCI16/PaymentOrder?action=issue&paymentOrder="+list.get(row).getId());
        
        try {
            URL url = new URL("http://localhost:8081/GCI16/PaymentOrder?action=issue&paymentOrder="+list.get(row).getId());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Cookie", session);
            connection.connect(); 
            int resCode = connection.getResponseCode();
            if(resCode == 200){
                poTable.setValueAt("ISSUED", rowSel, 5); /*Modifico la colonna relativa allo stato.*/
                PaymentOrder paymOrd = list.get(row);
                paymOrd.setStatus(Status.ISSUED);
                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                line = rd.readLine();
                rd.close();
                Gson gson = new Gson();
                int protocol = gson.fromJson(line, Integer.class);
                paymOrd.setProtocol(protocol);
                poTable.setValueAt(protocol, rowSel, 0);
                /* Genera PDF */
                PDFGenerator.generate(paymOrd);
                JOptionPane.showMessageDialog(null, "Payment order with protocol " + paymOrd.getProtocol() + " has been issued.\nA PDF, with all the information, was created correctly");
                
                poTable.getSelectionModel().clearSelection();
            }else if (resCode == 462){
              JOptionPane.showMessageDialog(this,"Server not available"); 
            }
        } catch (IOException ex) {
            Logger.getLogger(PaymentOrderTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_issueButtonActionPerformed

    private void filterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterButtonActionPerformed
        List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>();
        String protocol = protocolText.getText();
        if(protocol.length() > 0 )
            filters.add( RowFilter.numberFilter(ComparisonType.EQUAL,Integer.parseInt(protocol), 0));
        String debtor = debtorText.getText();
        if(debtor.length() > 0)
            filters.add(RowFilter.regexFilter("(?i)"+debtor, 1));
        int i;
        if( (i = yearList.getSelectedIndex())!=0)
            filters.add( RowFilter.numberFilter(ComparisonType.EQUAL,Integer.parseInt(yearList.getItemAt(i)), 2));
        if( (i = trimesterList.getSelectedIndex())!=0)
            filters.add( RowFilter.numberFilter(ComparisonType.EQUAL,Integer.parseInt(trimesterList.getItemAt(i)), 3));
        if ((i = statusList.getSelectedIndex() ) != 0)
            filters.add( RowFilter.regexFilter( "^"+statusList.getItemAt(i)+"$",5));
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(poTable.getModel()); 
        poTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.andFilter( filters ));
        
       
    }//GEN-LAST:event_filterButtonActionPerformed

    private void protocolTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_protocolTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_protocolTextActionPerformed

    private void protocolTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_protocolTextKeyTyped
       int c = evt.getKeyChar();
       if( (c < '0' || c > '9') && c!=KeyEvent.VK_BACKSPACE )
           evt.consume();
    }//GEN-LAST:event_protocolTextKeyTyped

    private void clearFilterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearFilterButtonActionPerformed
        protocolText.setText("");
        debtorText.setText("");
        yearList.setSelectedIndex(0);
        trimesterList.setSelectedIndex(0);
        statusList.setSelectedIndex(0);
    }//GEN-LAST:event_clearFilterButtonActionPerformed

 
    
    private void addPaymentOrderTable(PaymentOrder p){
        Object[] values = new Object[6];
        values[0] = p.getProtocol();
        values[1] = p.getDebtor();
        values[2] = p.getYear();
        values[3] = p.getTrimester();
        values[4] = p.getAmount();
        String status = p.getStatus().toString();
        if(status.equals("NOTISSUED"))
            status = "NOT ISSUED";
        values[5] = status;
        ((DefaultTableModel)poTable.getModel()).addRow(values);
    }
    
    public void addPaymentOrder(PaymentOrder p){
        list.add(p);
        this.addPaymentOrderTable(p);
        
    }
    
    public void setTable(){
        
        try {
            URL url = new URL("http://localhost:8081/GCI16/PaymentOrder?action=show");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Cookie", session);
            connection.connect();
            int resCode = connection.getResponseCode();
            if(resCode == 200){
                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                line = rd.readLine();
                rd.close();

                Gson gson = new Gson();
                java.lang.reflect.Type POListType = new TypeToken<Collection< PaymentOrder> >(){}.getType();
                list = gson.fromJson(line, POListType);

                Object[] values = new Object[6];
                for(PaymentOrder p : list){
                    this.addPaymentOrderTable(p);
                }
            }else if (resCode == 462){
              JOptionPane.showMessageDialog(this,"Server not available"); 
            }
           
        } catch (IOException ex) {
            Logger.getLogger(PaymentOrderTable.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(PaymentOrderTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaymentOrderTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaymentOrderTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaymentOrderTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaymentOrderTable(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearFilterButton;
    private javax.swing.JButton createButton;
    private javax.swing.JTextField debtorText;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton filterButton;
    private javax.swing.JButton issueButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable poTable;
    private javax.swing.JTextField protocolText;
    private javax.swing.JButton reissueButton;
    private javax.swing.JButton saveAsNotPertinentButton;
    private javax.swing.JButton saveAsPaidButton;
    private javax.swing.JButton saveAsSuspendedButton;
    private javax.swing.JComboBox<String> statusList;
    private javax.swing.JComboBox<String> trimesterList;
    private javax.swing.JComboBox<String> yearList;
    // End of variables declaration//GEN-END:variables
}
