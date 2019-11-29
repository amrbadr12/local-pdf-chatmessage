package tasrehmanualemail;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Amr Badr
 */
public class MainScreen extends javax.swing.JFrame {

    private ButtonColumn updateColumn;
    private Connection connection;
    private ArrayList<Message> registeredMessages;
    private int lastRecord;
    private int currentRegion;
    /*0:All
    1:Edara
    2:Cairo
    3:Alex
    4:Giza
    5:Mansoura
    6:Tanta
    7:Asyut
    8:Zagzaeg
    9:Qena
    10:Menya
     */
    private ArrayList<Integer> efaaList;

    private Timer timer;

    public MainScreen() {
        initComponents();
        getContentPane().setBackground(new java.awt.Color(218, 225, 233));
        registeredMessages = new ArrayList<Message>();
        lastRecord = 0;
        efaaList = new ArrayList<>();
        Action updateRow = new AbstractAction() {
            //column's done button action listener
            //set current isSent flag to 1 and delete row
            //TODO
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int modelRow = Integer.valueOf(e.getActionCommand());
                int idInDb = efaaList.get(modelRow);
                int updateMessageIsSentInDB = updateMessageIsSentInDB(connection, idInDb);
                switch (updateMessageIsSentInDB) {
                    case 0:
                        JOptionPane.showMessageDialog(MainScreen.this, "لم يتم تأكيد استلام الأوراق", "خطأ", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(MainScreen.this, "تم تأكيد استلام الأوراق");
                        populateTableRows(getRegionChatMessages(connection, currentRegion), model, efaaList);
                        break;

                    default:
                        break;
                }
            }
        ;

        };

        updateColumn = new ButtonColumn(efaaTable, updateRow, 2);
        setTimer(10000, 10000, new Task());
        setTimer(10000, 10000, new NotificationTask());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textfield = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        appNameLabel = new javax.swing.JLabel();
        natIDLabel = new javax.swing.JLabel();
        natIDTextField = new javax.swing.JTextField();
        natIDLabel1 = new javax.swing.JLabel();
        phoneNumTextField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        emailVerificationJButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        efaaTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        textfield.setText("jTextField1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("برنامج إرسال التصاريح يدوي");
        setBackground(new java.awt.Color(218, 225, 233));
        setExtendedState(1);
        setForeground(java.awt.Color.white);
        setPreferredSize(new java.awt.Dimension(570, 570));
        setSize(new java.awt.Dimension(570, 570));

        appNameLabel.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        appNameLabel.setText("  ارسال التصاريح يدوي    ");
        appNameLabel.setToolTipText("");

        natIDLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        natIDLabel.setText("الرقم القومي:");

        natIDLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        natIDLabel1.setText("رقم الواتس اب:");
        natIDLabel1.setDoubleBuffered(true);

        emailLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        emailLabel.setText("الايميل:");

        sendButton.setBackground(new java.awt.Color(255, 102, 102));
        sendButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sendButton.setForeground(new java.awt.Color(249, 249, 249));
        sendButton.setText("إرسال");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("استعلام عن إرسال التصريح على الإيميل اوتوماتيك:");

        emailVerificationJButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        emailVerificationJButton.setText("استعلام");
        emailVerificationJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailVerificationJButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("الطلبات المسجلة");

        jTable1.setBackground(new java.awt.Color(240, 240, 240));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "الرقم القومي", "الايميل", "رقم الواتس اب", "حالة الطلب"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(sendButton)
                .addGap(227, 227, 227))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(appNameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(natIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(natIDLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(phoneNumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(natIDLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(emailLabel))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(emailVerificationJButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(appNameLabel)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(natIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(natIDLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(natIDLabel1)
                    .addComponent(phoneNumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(sendButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailVerificationJButton)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ارسال التصاريح يدوى", jPanel2);

        efaaTable.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        efaaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "رقم الطلب", "ملاحظات", "تم إستلام المرفقات؟"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        efaaTable.setAlignmentX(1.0F);
        efaaTable.setAlignmentY(1.0F);
        efaaTable.setRowHeight(32);
        jScrollPane2.setViewportView(efaaTable);
        if (efaaTable.getColumnModel().getColumnCount() > 0) {
            efaaTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("منطقة:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "كول سنتر", "إدارة التجنيد", "القاهرة", "اسكندرية", "الجيزة", "المنصورة", "طنطا", "اسيوط", "زقازيق", "قنا", "المنيا" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("استعلام عن استلام المرفق:");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("استعلام");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("التحقق من حفظ المرفق بالرقم الثلاثي:");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("التحقق");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel6.setText("مثال:1990_123_123");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(32, 32, 32)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("استكمال مرفقات شهادات الإعفاء", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("برنامج ارسال التصاريح يدوي");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        JComboBox ourCombo = (JComboBox) evt.getSource();
        currentRegion = ourCombo.getSelectedIndex();
        ResultSet regionChatMessages = getRegionChatMessages(connection, ourCombo.getSelectedIndex());
        DefaultTableModel model = (DefaultTableModel) efaaTable.getModel();
        populateTableRows(regionChatMessages, model, efaaList);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        showQueryDialogue();
    }//GEN-LAST:event_jButton1ActionPerformed

    //verify if the email has been sent
    private void emailVerificationJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailVerificationJButtonActionPerformed
        showDialogue(true);
    }//GEN-LAST:event_emailVerificationJButtonActionPerformed

    //send message to gawazat button
    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        // TODO add your handling code here:
        String inputNatID = natIDTextField.getText();
        String inputEmail = emailTextField.getText();
        String inputPhoneNum = phoneNumTextField.getText();

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Message newMessage;
        if (!inputNatID.trim().isEmpty() && (!inputEmail.trim().isEmpty() || !inputPhoneNum.trim().isEmpty())) {
            //email and national id have to be valid to proceed..
            //phone number is not empty
            if (!inputPhoneNum.trim().isEmpty() && validatePhoneNumber(inputPhoneNum)) {

                //phone number and email exist
                if (!inputEmail.trim().isEmpty()) {
                    newMessage = new Message(inputEmail, inputNatID, inputPhoneNum);
                    if (!validateEmail(inputEmail) || !validateNatID(inputNatID)) {
                        JOptionPane.showMessageDialog(MainScreen.this, "الرقم القومي او الايميل غير صحيح", "خطأ", JOptionPane.ERROR_MESSAGE);
                        clearFields();
                        return;
                    }
                    clearFields();
                } //only phone number
                else {
                    newMessage = new Message("", inputNatID, inputPhoneNum);
                    clearFields();
                }
            } //phone number is empty
            else {
                if (!validateEmail(inputEmail) || !validateNatID(inputNatID)) {
                    JOptionPane.showMessageDialog(MainScreen.this, "الرقم القومي او الايميل غير صحيح", "خطأ", JOptionPane.ERROR_MESSAGE);
                    clearFields();
                    return;
                }
                newMessage = new Message(inputEmail, inputNatID, "");
            }

            if (connection != null && newMessage != null) {
                try {
                    int status;
                    PreparedStatement stmt = insertNewMessageInDB(connection, newMessage);
                    status = stmt.executeUpdate();
                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    String message = "";
                    if (status == 1 && generatedKeys.next()) {
                        message = "تم إرسال الرسالة";
                        natIDTextField.setText("");
                        emailTextField.setText("");
                        //new row added
                        Object[] row = new Object[]{newMessage.getNatID() + "\n", newMessage.getEmail(), newMessage.getPhoneNumber(), "لم يتم الإرسال"};
                        model.addRow(row);
                        Long key = generatedKeys.getLong(1);
                        newMessage.setId(key.intValue());
                        registeredMessages.add(newMessage);
                        JOptionPane.showMessageDialog(MainScreen.this, message, "معلومات", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        message = "لم يتم إرسال الرسالة";
                        JOptionPane.showMessageDialog(MainScreen.this, message, "خطأ", JOptionPane.ERROR);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(MainScreen.this, "الرقم القومي او الايميل غير صحيح", "خطأ", JOptionPane.ERROR_MESSAGE);
                clearFields();
            }
        } else {
            JOptionPane.showMessageDialog(MainScreen.this, "الرقم القومي او الايميل فارغ", "خطأ", JOptionPane.ERROR_MESSAGE);
            clearFields();
        }
    }//GEN-LAST:event_sendButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String baseDir = "\\\\WANHOST\\d$\\مرقفات المناطق\\2019\\";
        ArrayList<String> dirs = new ArrayList();
        String inputRequestID = JOptionPane.showInputDialog("الرقم الثلاثي:");
        if (inputRequestID != null) {
            File dir = new File(baseDir);
            String[] children = dir.list();
            if (children == null) {
                System.out.println("current file name" + baseDir);
                System.out.println("Either dir does not exist or is not a directory");
            } else {
                for (int i = 0; i < children.length; i++) {
                    String filename = children[i];
                    dirs.add(filename);
                    System.out.println(filename);
                }
            }
            for (String file : dirs) {
                String url = baseDir.concat(file).concat("\\" + inputRequestID);

                System.out.println("current file name" + url);
                File morfkat = new File(url);
                if (morfkat.exists()) {
                    try {
                        Desktop.getDesktop().open(morfkat);
                    } catch (IOException ex) {
                        Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(MainScreen.this, "المرفقات محفوظة.");
                    System.out.println("تم حفظ المرفقات");
                    return;
                }
            }
            JOptionPane.showMessageDialog(MainScreen.this, "المرفقات ليست محفوظة.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void clearFields() {
        natIDTextField.setText("");
        emailTextField.setText("");
        phoneNumTextField.setText("");

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainScreen mainScreen = new MainScreen();
                mainScreen.setVisible(true);
                mainScreen.setLocationRelativeTo(null);
                mainScreen.connection = startDBConnection("tasrehchatdemo");
                ResultSet regionChatMessages = getRegionChatMessages(mainScreen.connection, 0);
                DefaultTableModel model = (DefaultTableModel) mainScreen.efaaTable.getModel();
                populateTableRows(regionChatMessages, model, mainScreen.efaaList);
                if (mainScreen.connection == null) {
                    JOptionPane.showMessageDialog(mainScreen, "Connection is null");
                }
            }
        });
    }

    private static boolean validateEmail(String inputEmail) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";
        Pattern compile = Pattern.compile(emailRegex);
        return compile.matcher(inputEmail).matches();
    }

    private static boolean validateNatID(String id) {
        char firstChar = id.charAt(0);
        int compareValue = Character.getNumericValue(firstChar);
        if (compareValue == 2 || compareValue == 3) {
            if (id.toCharArray().length == 14) {
                return true;
            }
        }
        return false;
    }

    public static ResultSet getRegionChatMessages(Connection con, int region) {
        Statement statement;
        try {
            statement = con.createStatement();
            String query = "SELECT * FROM `chat` WHERE `region`=" + region + " AND is_sent=0";
            ResultSet messagesFromRegionSet = statement.executeQuery(query);
            return messagesFromRegionSet;
        } catch (SQLException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ResultSet getIsSentChatMessage(Connection con, int requestID) {
        Statement statement;
        try {
            statement = con.createStatement();
            String query = "SELECT * FROM `chat` WHERE `request_id`=" + requestID;
            ResultSet messagesFromRegionSet = statement.executeQuery(query);
            return messagesFromRegionSet;
        } catch (SQLException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static int updateMessageIsSentInDB(Connection con, int id) {
        int updateStatus = 0;
        try {
            String query = "UPDATE `chat` SET `is_sent` = '1' WHERE `chat`.`id` =?";
            if (con != null) {
                PreparedStatement prepareStatement = con.prepareStatement(query);
                prepareStatement.setInt(1, id);
                updateStatus = prepareStatement.executeUpdate();
            }
            System.out.println("update status after statement" + updateStatus);
        } catch (SQLException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updateStatus;
    }

    private static boolean validatePhoneNumber(String inputNumber) {
        if (inputNumber.matches("[0-9]+") && inputNumber.length() >= 11) {
            return true;
        }
        return false;
    }

    private static Connection startDBConnection(String db) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //working
            Connection con = DriverManager.getConnection("jdbc:mysql://200.200.200.141/"
                    + db + "?autoReconnect=true&useSSL=false&useUnicode=yes&characterEncoding=UTF-8", "secondacc", "password");
            return con;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    //dialogue to check if the email has been sent automatically
    private void showDialogue(boolean isEmail) {
        String inputNatID = JOptionPane.showInputDialog("الرقم القومي:");
        if (inputNatID != null) {
            if (validateNatID(inputNatID)) {
                //email input dialogue
                if (isEmail) {
                    Connection connectionToCitizenContacts = startDBConnection("citizens_contacts");
                    if (connectionToCitizenContacts != null) {
                        try {
                            String getIsSentFlagQuery = "SELECT * FROM `paymentnum_citizens` WHERE National_ID=" + inputNatID;
                            String getCitizenInfoQuery = "SELECT * FROM `citizens` WHERE `National_ID`=" + inputNatID;
                            Statement createStatement = connectionToCitizenContacts.createStatement();
                            ResultSet getIsSentFlagQueryResult = createStatement.executeQuery(getIsSentFlagQuery);
                            //citizen record exists in zyad's db
                            String citizenReport;
                            if (getIsSentFlagQueryResult.next()) {
                                int isSent = getIsSentFlagQueryResult.getInt("isSent");
                                ResultSet getCitizenInfoResult = createStatement.executeQuery(getCitizenInfoQuery);
                                getCitizenInfoResult.next();
                                String citizenName = getCitizenInfoResult.getString("Citizen_Name");
                                String citizenEmail = getCitizenInfoResult.getString("Email");
                                if (isSent == 1) {
                                    citizenReport = "تم إرسال التصريح إلى:\n"
                                            + citizenName + "\n" + "الايميل:" + citizenEmail;
                                } else {
                                    citizenReport = "لم يتم إرسال التصريح إلى :\n"
                                            + citizenName;
                                }
                            } //record wasn't found in db
                            else {
                                citizenReport = "لا يوجد بيانات عن هذا المواطن";
                            }
                            JOptionPane.showMessageDialog(null, citizenReport, "معلومات", JOptionPane.INFORMATION_MESSAGE);

                        } catch (SQLException ex) {
                            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } //TODO:whatsapp input dialogue
                else {

                }
            } //NAT ID is not valid
            else {
                JOptionPane.showMessageDialog(null, "الرقم القومي غير صحيح.");
            }
        }

    }

    private void showQueryDialogue() {
        String inputRequestID = JOptionPane.showInputDialog("رقم الطلب:");
        if (inputRequestID != null) {
            if (inputRequestID.matches("[0-9]+")) {
                ResultSet isSentFlagsResultSet = getIsSentChatMessage(connection, Integer.parseInt(inputRequestID));
                String report = "";
                try {
                    while (isSentFlagsResultSet.next()) {
                        int isSent = isSentFlagsResultSet.getInt("is_sent");
                        Timestamp timestamp = isSentFlagsResultSet.getTimestamp("date");
                        if (isSent == 0) {
                            report += "تاريخ: " + timestamp.toString() + "\n" + "لم يتم استلام المرفقات لهذا الطلب\n";
                        } else {
                            report += "تاريخ: " + timestamp.toString() + "\n" + "تم استلام المرفقات لهذا الطلب\n";
                        }
                    }
                    JOptionPane.showMessageDialog(this, report);
                } catch (SQLException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JOptionPane.showMessageDialog(this, "رقم الطلب خاطئ");
            }
        }

    }

    private void setTimer(long firstTime, long period, TimerTask task) {
        timer = new Timer();
        timer.schedule(task, firstTime, period);
        System.out.println("Timer scheduled.");
    }

    //new messages notification
    private class NotificationTask extends TimerTask {

        @Override
        public void run() {
            //excuete select query then update the table contents
            ResultSet messagesFromDB = getRegionChatMessages(connection, currentRegion);
            if (efaaList.isEmpty()) {
                try {
                    if (messagesFromDB.first()) {
                        messagesFromDB.previous();
                        System.out.println("first found");
                        DefaultTableModel model = (DefaultTableModel) efaaTable.getModel();
                        int cnt=populateTableRows(messagesFromDB, model, efaaList);
                        if(cnt>0)
                        {
                             JOptionPane.showMessageDialog(MainScreen.this, cnt + " تم إضافة مرفق جديد");
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                lastRecord = efaaList.get(efaaList.size() - 1);
                ArrayList<Object[]> newAddedMessages = new ArrayList<>();
                int lastDBRecord;
                boolean isThereNewMessages = false;
                try {
                    while (messagesFromDB.next()) {
                        lastDBRecord = messagesFromDB.getInt("id");

                        if (lastDBRecord > lastRecord) {
                            newAddedMessages.add(new Object[]{messagesFromDB.getString("request_id") + "\n", messagesFromDB.getString("custom_message") + "\n",
                                "تم إستلام الأوراق" + "\n", "التحقق \n"});
                            efaaList.add(messagesFromDB.getInt("id"));
                            System.out.println("id number " + messagesFromDB.getInt("id") + " added");
                            isThereNewMessages = true;
                        } //if there was no new messages added, refresh the current list
                        else {
                            isThereNewMessages = false;
                        }

                    }
                    if (!isThereNewMessages) {
                         System.out.println("No new messages added. refreshing the list");
                        populateTableRows(getRegionChatMessages(connection, currentRegion), (DefaultTableModel) efaaTable.getModel(), efaaList);
                    } else {
                        //new messages add them to the exisiting table.
                        if (!newAddedMessages.isEmpty()) {
                            int count = 0;
                            DefaultTableModel model = (DefaultTableModel) efaaTable.getModel();
                            for (Object[] row : newAddedMessages) {
                                model.addRow(row);
                                count++;
                                System.out.println("new message added." + row[0] + "\n " + row[1] + "\n");
                            }
                            JOptionPane.showMessageDialog(MainScreen.this, count + " تم إضافة مرفق جديد");

                        } else {
                            System.out.println("No new messages added.");
                        }
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

    }

    private class Task extends TimerTask {

        @Override
        public void run() {

            if (!registeredMessages.isEmpty()) {

                for (int i = 0; i < registeredMessages.size(); i++) {
                    Message message = registeredMessages.get(i);
                    if (message.getIsSent() != 1) {
                        try {
                            ResultSet result = getDataFromDBQuery(connection, getIsSentQueryBuilder(registeredMessages.get(i).getId()));
                            result.next();
                            int isSent = result.getInt("isSent");
                            message.setIsSent(isSent);
                            String isSentReport;
                            if (isSent == 0) {
                                isSentReport = "لم يتم الإرسال";
                                System.out.println("Message wasn't sent from gawazat yet");
                            } else {
                                //TODO change color from red to green
                                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                                isSentReport = "تم الإرسال";
                                System.out.println("Message was sent from gawazat successfully");
                                model.setValueAt(isSentReport, i, 3);
                                String messageDialogue = "تم إرسال التصريح" + "\n"
                                        + "رقم قومي: " + message.getNatID() + "\n";

                                if (message.getEmail().isEmpty()) {
                                    messageDialogue += "رقم الواتس اب: " + message.getPhoneNumber();
                                } else if (message.getPhoneNumber().isEmpty()) {
                                    messageDialogue += "ايميل: " + message.getEmail();
                                } else {
                                    messageDialogue += "رقم الواتس اب: " + message.getPhoneNumber() + "\n" + "ايميل: " + message.getEmail();
                                }

                                JOptionPane.showMessageDialog(MainScreen.this, messageDialogue);
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        continue;
                    }
                }
            }

        }

    }

    private void removeTimer() {
        if (timer != null) {
            timer.cancel();
            System.out.println("Timer removed.");
        }
    }

    private static int populateTableRows(ResultSet res, DefaultTableModel model, ArrayList<Integer> list) {
        list.clear();
        model.setRowCount(0);
        int count=0;
        try {
            while (res.next()) {
                Object[] row = new Object[]{res.getString("request_id") + "\n", res.getString("custom_message") + "\n",
                    "تم إستلام الأوراق"};
                list.add(res.getInt("id"));
                count++;
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    private static String getIsSentQueryBuilder(int id) {
        return "SELECT * FROM `messages` WHERE `id`=" + id;
    }

    private static ResultSet getDataFromDBQuery(Connection con, String query) {
        Statement statement;
        try {
            statement = con.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static PreparedStatement insertNewMessageInDB(Connection con, Message msg) {
        PreparedStatement prepareStatement = null;
        try {
            prepareStatement = con.prepareStatement("INSERT INTO messages(National_ID, Email, isSent, phone_number) values (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, msg.getNatID());
            prepareStatement.setString(2, msg.getEmail());
            prepareStatement.setInt(3, msg.getIsSent());
            prepareStatement.setString(4, msg.getPhoneNumber());;
        } catch (SQLException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prepareStatement;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel appNameLabel;
    private javax.swing.JTable efaaTable;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JButton emailVerificationJButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel natIDLabel;
    private javax.swing.JLabel natIDLabel1;
    private javax.swing.JTextField natIDTextField;
    private javax.swing.JTextField phoneNumTextField;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextField textfield;
    // End of variables declaration//GEN-END:variables
}
