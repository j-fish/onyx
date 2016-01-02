/**
 * *****************************************************************************
 * Copyright (c) 2015, 2016, 2017, Thomas.H Warner. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. 
 ******************************************************************************
 */
package com.jellyfish.jfgonyx.io;

import com.jellyfish.jfgonyx.constants.GraphicsConst;
import com.jellyfish.jfgonyx.entities.OnyxPiece;
import com.jellyfish.jfgonyx.exceptions.NoValidOnysPositionsFound;
import com.jellyfish.jfgonyx.io.events.MoveVirutalPiece;
import com.jellyfish.jfgonyx.onyx.Onyx;
import com.jellyfish.jfgonyx.onyx.interfaces.OnyxExecutable;
import com.jellyfish.jfgonyx.ui.OnyxBoard;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author thw
 */
public class KeyInput implements KeyListener {

    private OnyxBoard board = null;
    private final HashMap<KeyInput.EVENT, OnyxExecutable> ops = new HashMap<>();
    
    public static enum EVENT {
        VIRTUAL_P_MOVE
    }
    
    public void init(final OnyxBoard board) {
        this.board = board;
        this.ops.put(KeyInput.EVENT.VIRTUAL_P_MOVE, new MoveVirutalPiece());
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
        if (this.ops.get(KeyInput.EVENT.VIRTUAL_P_MOVE).exec(e.getKeyCode(), board)) {
            
            /**
             * FIXME : refactor...
             */
            try {
                final String k = Onyx.SEARCH.get(Onyx.SEARCH_TYPE.RANDOM).search(
                        board.getPosCollection(), GraphicsConst.COLOR.WHITE);
                System.out.println("key=" + k);
                board.getPosCollection().getPosition(k).setPiece(
                        new OnyxPiece(GraphicsConst.COLOR.WHITE)
                );
            } catch (final NoValidOnysPositionsFound ex) {
                Logger.getLogger(KeyInput.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) { }
    
}