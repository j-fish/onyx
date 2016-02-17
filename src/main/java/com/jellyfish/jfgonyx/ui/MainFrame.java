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

import com.jellyfish.jfgonyx.constants.GraphicsConst;
import com.jellyfish.jfgonyx.helpers.LogHelper;
import com.jellyfish.jfgonyx.helpers.MainFrameGHelper;
import com.jellyfish.jfgonyx.onyx.entities.OnyxMove;
import com.jellyfish.jfgonyx.onyx.interfaces.OnyxObserver;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author thw
 */
public class MainFrame extends javax.swing.JFrame implements OnyxObserver {

    private final OnyxPanel mainPanel;
    private final OnyxBoard board;
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("mainFrame"); // NOI18N
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        mainSplitPane.setBorder(null);
        mainSplitPane.setDividerLocation(230);
        mainSplitPane.setDividerSize(14);
        mainSplitPane.setDoubleBuffered(true);
        mainSplitPane.setOneTouchExpandable(true);

        mainScrollPane.setBorder(null);
        mainScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mainScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        mainScrollPane.setDoubleBuffered(true);
        mainSplitPane.setRightComponent(mainScrollPane);

        textScrollPane.setBorder(null);

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

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        if (this.getWidth() < this.initialWidth && this.getHeight() < this.initialHeight) {
            this.updateScrollPanePolicy();
        }
    }//GEN-LAST:event_formComponentResized

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane dataTextPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JScrollPane mainScrollPane;
    private javax.swing.JSplitPane mainSplitPane;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JScrollPane textScrollPane;
    // End of variables declaration//GEN-END:variables

    private void initUI() {
        
        this.mainPanel.add(this.board);
        this.mainScrollPane.add(mainPanel);
        this.mainScrollPane.setViewportView(mainPanel);
        this.mainSplitPane.setOneTouchExpandable(true);
        this.mainSplitPane.getLeftComponent().setMinimumSize(new Dimension());
        this.mainSplitPane.setDividerLocation(0d);
        this.mainSplitPane.setBackground(GraphicsConst.COMPONENTS_BACKGROUND_COLOR1);
        this.mainSplitPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                board.focus();
            }
        });
        this.dataTextPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                board.focus();
            }
        });
        this.dataTextPane.setContentType("text/html");
        final HTMLEditorKit html = new HTMLEditorKit();
        this.dataTextPane.setEditorKit(html);
        htmlEditorKit = (HTMLEditorKit) this.dataTextPane.getEditorKit();
        htmlEditorKit.setLinkCursor(new Cursor(Cursor.HAND_CURSOR));
        doc = this.dataTextPane.getDocument();
        final DefaultCaret caret = (DefaultCaret) this.dataTextPane.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        board.focus();
        this.pack();
        this.setSize(GraphicsConst.BOARD_WIDTH + 36, GraphicsConst.BOARD_WIDTH + 68);
        this.setLocation(((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2)) - 
                (this.getWidth() / 2), 20);
        this.setTitle("Onyx");
        this.setVisible(true);
    }
    
    private void updateScrollPanePolicy() {
        mainScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    @Override
    public final void notifyMove(final OnyxMove m) {
        
        this.move_labels.add(m.toString());
        /**
         * FIXME : UI must have control on full raw or HTML output.
         *
        this.dataTextPane.setText(StringUtils.EMPTY); 
        MainFrameGHelper.appendHTMLData(this.move_labels, this.board.getPosCollection(), 
                this.htmlEditorKit, this.doc);
         */
        MainFrameGHelper.appendRawData(LogHelper.getDTFullStamp() + 
                StringUtils.SPACE + m.toString(), htmlEditorKit, doc);
    }
    
    public static final void print(final String data) {
        MainFrameGHelper.appendRawData(LogHelper.getDTFullStamp() + 
                StringUtils.SPACE + data, htmlEditorKit, doc);
    }
    
}
