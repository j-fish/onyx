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
package com.jellyfish.jfgonyx.onyx.search.subroutines.positionsearch;

import com.jellyfish.jfgonyx.onyx.constants.OnyxConst;
import com.jellyfish.jfgonyx.onyx.entities.OnyxDiamond;
import com.jellyfish.jfgonyx.onyx.entities.OnyxMove;
import com.jellyfish.jfgonyx.onyx.entities.OnyxPos;
import com.jellyfish.jfgonyx.onyx.entities.collections.OnyxDiamondCollection;
import com.jellyfish.jfgonyx.onyx.entities.collections.OnyxPosCollection;
import com.jellyfish.jfgonyx.onyx.exceptions.InvalidOnyxPositionException;
import com.jellyfish.jfgonyx.onyx.search.searchutils.OnyxMoveUtils;
import com.jellyfish.jfgonyx.onyx.abstractions.AbstractSubroutine;
import com.jellyfish.jfgonyx.onyx.utils.RandomUtils;
import com.jellyfish.jfgonyx.ui.OnyxBoard;
import com.jellyfish.jfgonyx.vars.GraphicsVars;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author thw
 */
public class CenterPositionSubroutine extends AbstractSubroutine {
    
    /**
     * Get playable diamond center position nearest to board center.
     * @param c position collection.
     * @param d diamond collection.
     * @param color search color.
     * @return Best OnyxMove instance or null.
     * @throws com.jellyfish.jfgonyx.onyx.exceptions.InvalidOnyxPositionException if a position is invalid.
     */
    public final OnyxMove getCenterPos(final OnyxPosCollection c, final OnyxDiamondCollection d, 
            final OnyxConst.COLOR color) throws InvalidOnyxPositionException {

        boolean found = false;
        float side = 3f;
        int counter = 0;
        final float minS = 1.5f -.01f;
        float s = ((GraphicsVars.getInstance().BOARD_SIDE_SQUARE_COUNT + 1) / 2) - .5f;
        final List<OnyxPos> pos = new ArrayList<>();
        OnyxPos tmp = null;
        
        while (!found || s > minS) { 
            
            for (String k : getKeys(c, s, side)) {
                
                tmp = c.getPosition(k);
                if (tmp != null && !tmp.isOccupied() && tmp.isDiamondCenter()) {
                    
                    for (String nK : tmp.connections) {
                        if (c.getPosition(nK) != null && !c.getPosition(nK).isOccupied()) {
                            ++counter;
                        }
                    }
                    
                    if (counter == 4) { 
                        found = true;
                        pos.add(c.getPosition(k));
                    }
                    counter = 0;
                }
            }
            
            if (pos.size() > 0) {
                move = new OnyxMove(pos.get(pos.size() == 1 ? 0 : RandomUtils.randInt(0, pos.size() - 1)), 
                    OnyxConst.SCORE.CENTER.getValue() - side);
                break;
            }
            
            s -= 1f;
            ++side;
        }
        
        if (OnyxMoveUtils.isMove(move)) {
            print(AbstractSubroutine.BEST_CANDIDATE, AbstractSubroutine.SUBROUTINE_TYPE.CENTER_POS, 
                color, move.getPos().getKey());
        }

        return move;
    }
    
    /**
     * @param c position collection.
     * @param s start coordinate.
     * @param side square side length.
     * @return all keys for perimeter of square defined by square side lenght.
     */
    private Set<String> getKeys(final OnyxPosCollection c, final float s, final float side) {
        
        final Set<String> keys = new HashSet<>();
        OnyxPos tmp = null;
        
        for (float i = 0f; i < side; i += 1f) {
            tmp = c.getPosition(String.format(OnyxPosCollection.KEY_FORMAT, s + i, s));
            if (tmp != null) keys.add(tmp.getKey());
        }
             
        for (float i = 0f; i < side; i += 1f) {
            tmp = c.getPosition(String.format(OnyxPosCollection.KEY_FORMAT, s + side - 1, s + i));
            if (tmp != null) keys.add(tmp.getKey());
        }
        
        for (float i = 0f; i < side; i += 1f) {
            tmp = c.getPosition(String.format(OnyxPosCollection.KEY_FORMAT, s + i, s + side - 1));
            if (tmp != null) keys.add(tmp.getKey());
        }
        
        for (float i = 0f; i < side; i += 1f) {
            tmp = c.getPosition(String.format(OnyxPosCollection.KEY_FORMAT, s, s + i));
            if (tmp != null) keys.add(tmp.getKey());
        }
        
        return keys;
    }
    
    /**
     * Get playable diamond center position nearest to board center.
     * @param c position collection.
     * @param b onyx board.
     * @return Best OnyxMove instance or null.
     * @throws com.jellyfish.jfgonyx.onyx.exceptions.InvalidOnyxPositionException if a position is invalid.
     */
    @Deprecated
    public final OnyxMove getCenterPos(final OnyxPosCollection c, final OnyxBoard b) throws InvalidOnyxPositionException {

        final Set<OnyxDiamond> d = new HashSet<>();
        int i = 0;
        
        for (OnyxDiamond dmd : b.getDiamondCollection().getDiamonds().values()) {
            
            if (dmd.isFivePosDiamond()) {
                i = 0;
                for (String k : dmd.getAllKeys()) {
                    if (!c.getPosition(k).isOccupied()) ++i;
                }
                if (i == 5) d.add(dmd);
            }
        }

        final OnyxDiamond[] r = sortByCenterPosValue(d);
        
        if (r == null || r.length == 0) return null;
        i = r.length / 2;
        
        move = new OnyxMove(r[i].getCenterPos(), OnyxConst.SCORE.CENTER.getValue());
        
        return move;
    }
    
    @Deprecated
    public final OnyxDiamond[] sortByCenterPosValue(final Set<OnyxDiamond> set) throws InvalidOnyxPositionException {
       
        int i = -1, j = 0;
        OnyxDiamond tmp = null;
        final OnyxDiamond[] r = new OnyxDiamond[set.size()];
        for (OnyxDiamond d : set) r[++i] = d;
        
        for (i = 0; i < r.length; ++i) {
            j = getLowValue(r, i);
            tmp = r[i];
            r[i] = r[j];
            r[j] = tmp;
        }
        
        return r;
    }
    
    @Deprecated
    private int getLowValue(final OnyxDiamond[] a, final int sI) throws InvalidOnyxPositionException {
        
        int index = sI;
        float f = -1f;
        float v = (float) ((GraphicsVars.getInstance().BOARD_SIDE_SQUARE_COUNT + 1) * 2);
        for (int i = sI; i < a.length; ++i) {
            f = a[i].getCenterPos().x + a[i].getCenterPos().y;
            if (f < v) {
                index = i;
                v = f;
            }
        }
        
        return index;
    }
    
}
