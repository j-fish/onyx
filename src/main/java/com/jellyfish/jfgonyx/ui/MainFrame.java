/*******************************************************************************
 * Copyright (c) 2015, 2016, 2017, Thomas.H Warner.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this 
 * list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, 
 * this list of conditions and the following disclaimer in the documentation and/or 
 * other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors 
 * may be used to endorse or promote products derived from this software without 
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY 
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON 
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 *******************************************************************************/

package com.jellyfish.jfgonyx.ui;

import com.jellyfish.jfgonyx.onyx.interfaces.OnyxObserver;
import com.jellyfish.jfgonyx.onyx.vars.OnyxCommonVars;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;

/**
 * @author thw
 */
public class MainFrame extends javax.swing.JFrame implements OnyxObserver {

    private final OnyxPanel mainPanel;
    private OnyxBoard board;
    private final int initialWidth;
    private final int initialHeight;
    private final LinkedList<String> move_labels = new LinkedList<>();
    private final String label_format = "%d: %s\n";
    private static HTMLEditorKit htmlEditorKit = null;
    private static Document doc = null;
    
    /**
     * Creates new form MainFrame
     * @param panel
     * @param board
     */
    public MainFrame(final OnyxPanel panel, final OnyxBoard board) {
        
        initComponents();
        this.mainPanel = panel;
        this.board = board;
        this.initialHeight = board.getHeight();
        this.initialWidth = board.getWidth();
        initUI();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainSplitPane = new javax.swing.JSplitPane();
        mainScrollPane = new javax.swing.JScrollPane();
        textScrollPane = new javax.swing.JScrollPane();
        dataTextPane = new javax.swing.JTextPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        editMenu = new javax.swing.JMenu();
        gameMenu = new javax.swing.JMenu();
        restartGameMenuItem = new javax.swing.JMenuItem();
        gameSeparator1 = new javax.swing.JPopupMenu.Separator();
        restartUIBlackMenuItem = new javax.swing.JMenuItem();
        restartUIWhiteMenuItem = new javax.swing.JMenuItem();
        settingsMenu = new javax.swing.JMenu();
        colorSettingsMenu = new javax.swing.JMenu();
        changeBackgroundColorMenuItem = new javax.swing.JMenuItem();
        colorSettingsSeparator1 = new javax.swing.JPopupMenu.Separator();
        changeBoardBackgroundColorMenuItem = new javax.swing.JMenuItem();
        changeFourSideDiamondColorMenuItem = new javax.swing.JMenuItem();
        changeFivePositionsDiamondColorMenuItem = new javax.swing.JMenuItem();
        colorSettingsSeparator2 = new javax.swing.JPopupMenu.Separator();
        changeDataTextPaneBackgroundColorMenuItem = new javax.swing.JMenuItem();
        colorSettingsSeparator3 = new javax.swing.JPopupMenu.Separator();
        changeWhitePieceColorMenuItem = new javax.swing.JMenuItem();
        changeBlackPieceColorMenuItem = new javax.swing.JMenuItem();
        colorThemesMenu = new javax.swing.JMenu();
        settingsSeparator1 = new javax.swing.JPopupMenu.Separator();
        changeBoardSizeMenu = new javax.swing.JMenu();
        twelveMenuItem = new javax.swing.JMenuItem();
        fourteenMenuItem = new javax.swing.JMenuItem();
        sixteenMenuItem = new javax.swing.JMenuItem();
        eighteenMenuItem = new javax.swing.JMenuItem();
        twentyMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("mainFrame"); // NOI18N
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        mainSplitPane.setBorder(null);
        mainSplitPane.setDividerLocation(100);
        mainSplitPane.setDividerSize(14);
        mainSplitPane.setDoubleBuffered(true);
        mainSplitPane.setOneTouchExpandable(true);

        mainScrollPane.setBorder(null);
        mainScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mainScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        mainScrollPane.setDoubleBuffered(true);
        mainSplitPane.setRightComponent(mainScrollPane);

        textScrollPane.setBorder(null);
        textScrollPane.setToolTipText("");

        dataTextPane.setBackground(new java.awt.Color(250, 250, 250));
        dataTextPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 8, 4, 4));
        dataTextPane.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        textScrollPane.setViewportView(dataTextPane);

        mainSplitPane.setLeftComponent(textScrollPane);

        menuBar.setDoubleBuffered(true);

        fileMenu.setText("File");
        menuBar.add(fileMenu);

        editMenu.setText("Edit");
        menuBar.add(editMenu);

        gameMenu.setText("Game");

        restartGameMenuItem.setText("Start a new game");
        restartGameMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartGameMenuItemActionPerformed(evt);
            }
        });
        gameMenu.add(restartGameMenuItem);
        gameMenu.add(gameSeparator1);

        restartUIBlackMenuItem.setText("New game playing blacks    ");
        restartUIBlackMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartUIBlackMenuItemActionPerformed(evt);
            }
        });
        gameMenu.add(restartUIBlackMenuItem);

        restartUIWhiteMenuItem.setText("New game playing whites    ");
        restartUIWhiteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartUIWhiteMenuItemActionPerformed(evt);
            }
        });
        gameMenu.add(restartUIWhiteMenuItem);

        menuBar.add(gameMenu);

        settingsMenu.setText("Settings");

        colorSettingsMenu.setText("Color settings      ");

        changeBackgroundColorMenuItem.setText("Change background color");
        changeBackgroundColorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeBackgroundColorMenuItemActionPerformed(evt);
            }
        });
        colorSettingsMenu.add(changeBackgroundColorMenuItem);
        colorSettingsMenu.add(colorSettingsSeparator1);

        changeBoardBackgroundColorMenuItem.setText("Change Onyx board background color");
        changeBoardBackgroundColorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeBoardBackgroundColorMenuItemActionPerformed(evt);
            }
        });
        colorSettingsMenu.add(changeBoardBackgroundColorMenuItem);

        changeFourSideDiamondColorMenuItem.setText("Change 4 side diamond color");
        changeFourSideDiamondColorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeFourSideDiamondColorMenuItemActionPerformed(evt);
            }
        });
        colorSettingsMenu.add(changeFourSideDiamondColorMenuItem);

        changeFivePositionsDiamondColorMenuItem.setText("Change 5 positions diamond color");
        changeFivePositionsDiamondColorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeFivePositionsDiamondColorMenuItemActionPerformed(evt);
            }
        });
        colorSettingsMenu.add(changeFivePositionsDiamondColorMenuItem);
        colorSettingsMenu.add(colorSettingsSeparator2);

        changeDataTextPaneBackgroundColorMenuItem.setText("Change data output background color");
        changeDataTextPaneBackgroundColorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeDataTextPaneBackgroundColorMenuItemActionPerformed(evt);
            }
        });
        colorSettingsMenu.add(changeDataTextPaneBackgroundColorMenuItem);
        colorSettingsMenu.add(colorSettingsSeparator3);

        changeWhitePieceColorMenuItem.setText("Change white piece colors");
        changeWhitePieceColorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeWhitePieceColorMenuItemActionPerformed(evt);
            }
        });
        colorSettingsMenu.add(changeWhitePieceColorMenuItem);

        changeBlackPieceColorMenuItem.setText("Change black piece colors");
        changeBlackPieceColorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeBlackPieceColorMenuItemActionPerformed(evt);
            }
        });
        colorSettingsMenu.add(changeBlackPieceColorMenuItem);

        settingsMenu.add(colorSettingsMenu);

        colorThemesMenu.setText("Color themes");
        settingsMenu.add(colorThemesMenu);
        settingsMenu.add(settingsSeparator1);

        changeBoardSizeMenu.setText("Change board size    ");

        twelveMenuItem.setText("12x12 Onyx board    ");
        twelveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twelveMenuItemActionPerformed(evt);
            }
        });
        changeBoardSizeMenu.add(twelveMenuItem);

        fourteenMenuItem.setText("14x14 Onyx board    ");
        fourteenMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fourteenMenuItemActionPerformed(evt);
            }
        });
        changeBoardSizeMenu.add(fourteenMenuItem);

        sixteenMenuItem.setText("16x16 Onyx board");
        sixteenMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sixteenMenuItemActionPerformed(evt);
            }
        });
        changeBoardSizeMenu.add(sixteenMenuItem);

        eighteenMenuItem.setText("18x18 Onyx board");
        eighteenMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eighteenMenuItemActionPerformed(evt);
            }
        });
        changeBoardSizeMenu.add(eighteenMenuItem);

        twentyMenuItem.setText("20x20 Onyx board");
        twentyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twentyMenuItemActionPerformed(evt);
            }
        });
        changeBoardSizeMenu.add(twentyMenuItem);

        settingsMenu.add(changeBoardSizeMenu);

        menuBar.add(settingsMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="Main Frame & Menu events">           
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        if (this.getWidth() < this.initialWidth && this.getHeight() < this.initialHeight) {
            this.updateScrollPanePolicy();
        }
    }//GEN-LAST:event_formComponentResized
              
    private void restartGameMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartGameMenuItemActionPerformed

    }//GEN-LAST:event_restartGameMenuItemActionPerformed

    private void restartUIBlackMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartUIBlackMenuItemActionPerformed
              
    }//GEN-LAST:event_restartUIBlackMenuItemActionPerformed

    private void restartUIWhiteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartUIWhiteMenuItemActionPerformed
        
    }//GEN-LAST:event_restartUIWhiteMenuItemActionPerformed

    private void changeBackgroundColorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeBackgroundColorMenuItemActionPerformed
        OnyxCommonVars.getInstance().COMPONENTS_BACKGROUND_COLOR1 = JColorChooser.showDialog(this, 
            "Choose a new background color", OnyxCommonVars.getInstance().COMPONENTS_BACKGROUND_COLOR1);
        this.mainPanel.repaint();
    }//GEN-LAST:event_changeBackgroundColorMenuItemActionPerformed

    private void changeBoardBackgroundColorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeBoardBackgroundColorMenuItemActionPerformed
        OnyxCommonVars.getInstance().BACKGROUND = JColorChooser.showDialog(this, 
            "Choose a new Onyx board background color", OnyxCommonVars.getInstance().BACKGROUND);
        this.mainPanel.repaint();
    }//GEN-LAST:event_changeBoardBackgroundColorMenuItemActionPerformed

    private void changeFourSideDiamondColorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeFourSideDiamondColorMenuItemActionPerformed
        OnyxCommonVars.getInstance().DIAMOND = JColorChooser.showDialog(this, 
            "Choose a new color for 4 side diamonds", OnyxCommonVars.getInstance().DIAMOND);
        this.mainPanel.repaint();
    }//GEN-LAST:event_changeFourSideDiamondColorMenuItemActionPerformed

    private void changeFivePositionsDiamondColorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeFivePositionsDiamondColorMenuItemActionPerformed
        OnyxCommonVars.getInstance().FULL_DIAMOND = JColorChooser.showDialog(this, 
            "Choose a new color for full position diamonds", OnyxCommonVars.getInstance().FULL_DIAMOND);
        this.mainPanel.repaint();
    }//GEN-LAST:event_changeFivePositionsDiamondColorMenuItemActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    
    }//GEN-LAST:event_formWindowClosing

    private void changeDataTextPaneBackgroundColorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeDataTextPaneBackgroundColorMenuItemActionPerformed
        OnyxCommonVars.getInstance().COMPONENTS_BACKGROUND_COLOR2 = JColorChooser.showDialog(this, 
            "Change data output background color", OnyxCommonVars.getInstance().COMPONENTS_BACKGROUND_COLOR2);
        this.dataTextPane.setBackground(OnyxCommonVars.getInstance().COMPONENTS_BACKGROUND_COLOR2);
        this.dataTextPane.repaint();
    }//GEN-LAST:event_changeDataTextPaneBackgroundColorMenuItemActionPerformed

    private void changeWhitePieceColorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeWhitePieceColorMenuItemActionPerformed
        OnyxCommonVars.getInstance().WHITE_PIECE = JColorChooser.showDialog(this, 
            "Choose a new color for white pieces", OnyxCommonVars.getInstance().WHITE_PIECE);
        this.board.repaint();
    }//GEN-LAST:event_changeWhitePieceColorMenuItemActionPerformed

    private void changeBlackPieceColorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeBlackPieceColorMenuItemActionPerformed
        OnyxCommonVars.getInstance().BLACK_PIECE = JColorChooser.showDialog(this, 
            "Choose a new color for black pieces", OnyxCommonVars.getInstance().BLACK_PIECE);
        this.board.repaint();
    }//GEN-LAST:event_changeBlackPieceColorMenuItemActionPerformed

    private void twelveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twelveMenuItemActionPerformed

    }//GEN-LAST:event_twelveMenuItemActionPerformed

    private void fourteenMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fourteenMenuItemActionPerformed

    }//GEN-LAST:event_fourteenMenuItemActionPerformed

    private void sixteenMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sixteenMenuItemActionPerformed

    }//GEN-LAST:event_sixteenMenuItemActionPerformed

    private void eighteenMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eighteenMenuItemActionPerformed

    }//GEN-LAST:event_eighteenMenuItemActionPerformed

    private void twentyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twentyMenuItemActionPerformed

    }//GEN-LAST:event_twentyMenuItemActionPerformed
    // </editor-fold>     
    
    // <editor-fold defaultstate="collapsed" desc="Main frame vars">          
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem changeBackgroundColorMenuItem;
    private javax.swing.JMenuItem changeBlackPieceColorMenuItem;
    private javax.swing.JMenuItem changeBoardBackgroundColorMenuItem;
    private javax.swing.JMenu changeBoardSizeMenu;
    private javax.swing.JMenuItem changeDataTextPaneBackgroundColorMenuItem;
    private javax.swing.JMenuItem changeFivePositionsDiamondColorMenuItem;
    private javax.swing.JMenuItem changeFourSideDiamondColorMenuItem;
    private javax.swing.JMenuItem changeWhitePieceColorMenuItem;
    private javax.swing.JMenu colorSettingsMenu;
    private javax.swing.JPopupMenu.Separator colorSettingsSeparator1;
    private javax.swing.JPopupMenu.Separator colorSettingsSeparator2;
    private javax.swing.JPopupMenu.Separator colorSettingsSeparator3;
    private javax.swing.JMenu colorThemesMenu;
    private javax.swing.JTextPane dataTextPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem eighteenMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem fourteenMenuItem;
    private javax.swing.JMenu gameMenu;
    private javax.swing.JPopupMenu.Separator gameSeparator1;
    private javax.swing.JScrollPane mainScrollPane;
    private javax.swing.JSplitPane mainSplitPane;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem restartGameMenuItem;
    private javax.swing.JMenuItem restartUIBlackMenuItem;
    private javax.swing.JMenuItem restartUIWhiteMenuItem;
    private javax.swing.JMenu settingsMenu;
    private javax.swing.JPopupMenu.Separator settingsSeparator1;
    private javax.swing.JMenuItem sixteenMenuItem;
    private javax.swing.JScrollPane textScrollPane;
    private javax.swing.JMenuItem twelveMenuItem;
    private javax.swing.JMenuItem twentyMenuItem;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>   
    
    private void initUI() {
        
        this.mainPanel.add(this.board);
        this.mainScrollPane.add(mainPanel);
        this.mainScrollPane.setViewportView(mainPanel);
        this.mainSplitPane.setOneTouchExpandable(true);
        this.mainSplitPane.getLeftComponent().setMinimumSize(new Dimension());
        this.mainSplitPane.setDividerLocation(300);
        this.mainSplitPane.setBackground(OnyxCommonVars.getInstance().COMPONENTS_BACKGROUND_COLOR1);
        this.mainSplitPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                board.requestFocus();
            }
        });                
        this.dataTextPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                board.requestFocus();
            }
        });
        this.dataTextPane.setContentType("text/html");
        this.dataTextPane.setBackground(OnyxCommonVars.getInstance().COMPONENTS_BACKGROUND_COLOR2);
        this.dataTextPane.setSelectedTextColor(Color.WHITE);
        this.dataTextPane.setSelectionColor(OnyxCommonVars.getInstance().COMPONENTS_BACKGROUND_COLOR2);
        
        final HTMLEditorKit html = new HTMLEditorKit();
        this.dataTextPane.setEditorKit(html);
        htmlEditorKit = (HTMLEditorKit) this.dataTextPane.getEditorKit();
        htmlEditorKit.setLinkCursor(new Cursor(Cursor.HAND_CURSOR));
        doc = this.dataTextPane.getDocument();
        final DefaultCaret caret = (DefaultCaret) this.dataTextPane.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        this.board.requestFocus();
        this.pack();
        
        this.setSize(MainFrameVars.getInstance().width, MainFrameVars.getInstance().height);
        this.setLocation(MainFrameVars.getInstance().x, MainFrameVars.getInstance().y);
        this.mainSplitPane.setDividerLocation(MainFrameVars.getInstance().mainSplitPaneDividerLocation);
       
        final ImageIcon icn = new ImageIcon(getClass().getClassLoader().getResource("icons/icn.png"));
        this.setIconImage(icn.getImage());
        this.setTitle("Onyx");
        this.setVisible(true);
    }
    
    private void updateScrollPanePolicy() {
        mainScrollPane.setHorizontalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setVerticalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    }
        
    @Override
    public void notify(String data) {
    }

    @Override
    public void notifyMove(String m) {
    }

    @Override
    public void notifyGameStatus(String data) {
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getters & setters">          
    public javax.swing.JSplitPane getMainSplitPane() {
        return this.mainSplitPane;
    }
    
    public javax.swing.JTextPane getDataTextPane() {
        return this.dataTextPane;
    }
    
    public OnyxPanel getOnyxPanel() {
        return this.mainPanel;
    }
    
    public void setOnyxBoard(final OnyxBoard board) {
        this.board = board;
    }
    
    public LinkedList<String> getMoveLabels() {
        return this.move_labels;
    }
    // </editor-fold>          
    
}
