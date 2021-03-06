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
package com.jellyfish.jfgonyx.onyx.abstractions;

import com.jellyfish.jfgonyx.onyx.constants.OnyxConst;
import com.jellyfish.jfgonyx.onyx.entities.OnyxMove;
import com.jellyfish.jfgonyx.onyx.entities.OnyxPos;
import com.jellyfish.jfgonyx.onyx.entities.collections.OnyxPosCollection;
import com.jellyfish.jfgonyx.onyx.exceptions.InvalidOnyxPositionException;
import com.jellyfish.jfgonyx.onyx.search.searchutils.OnyxMoveUtils;
import com.jellyfish.jfgonyx.onyx.search.subroutines.positionsearch.OnyxPosStateSubroutine;
import com.jellyfish.jfgonyx.onyx.search.subroutines.positionsearch.TakePositionSubroutine;
import com.jellyfish.jfgonyx.ui.OnyxBoard;
import java.util.ArrayList;
import java.util.List;

/**
 * @author thw
 */
public class AbstractOnyxSearch {
    
    /**
     * Basic trim - discards NULL & OnyxPos NULL moves.
     * @param moves moves to trim.
     * @return move with highest scoring.
     */
    protected final OnyxMove trim(final List<OnyxMove> moves) {
        
        OnyxMove tmp = null;
        for (OnyxMove m : moves) {
            
            if (OnyxMoveUtils.isNotMove(m)) continue;
            
            /**
             * If win move then override return.
             */
            if (m.isWin()) return m;  
            
            /**
             * [!] Only & only if tmp is not yet set with m (the current
             * OnyxMove iterated on).
             */
            if (OnyxMoveUtils.isNotMove(tmp)) tmp = m;
            else if (m.getScore() >= tmp.getScore()) tmp = m;
        }
        
        return tmp;
    }
    
    /**
     * Trim overload - discards NULL, OnyxPos NULL & moves that will result
     * in take opotunities for color oponent.
     * @param moves moves to trim.
     * @param b onyx board instance.
     * @param c onyx position collection instance.
     * @param color depending on color, if move will enable a take for oponent color
     * then discard that move.
     * @return move with highest scoring.
     * @throws com.jellyfish.jfgonyx.onyx.exceptions.InvalidOnyxPositionException
     */
    protected final OnyxMove trim(final List<OnyxMove> moves, final OnyxBoard b, 
            final OnyxPosCollection c, final OnyxConst.COLOR color) throws InvalidOnyxPositionException {
        
        int count = 0;
        OnyxMove tmp = null;
        
        for (OnyxMove m : moves) {
            
            if (OnyxMoveUtils.isNotMove(m)) continue;
            
            /**
             * If win move then override return.
             */
            if (m.isWin()) return m;  
            
            /**
             * [!] Only & only if tmp is not yet set with m (the current
             * OnyxMove iterated on).
             */
            if (OnyxMoveUtils.isNotMove(tmp)) tmp = m; 
            
            /**
             * If score is > COUNTER_WIN_LINK - 1f then Override previous conditions.
             * @see OnyxConst
             */
            if (((tmp.isCapture() || !(new OnyxPosStateSubroutine(m.getPos()).willEnableTake(b, c, color))) &&
                    m.getScore() >= tmp.getScore()) || 
                    m.getScore() > OnyxConst.SCORE.COUNTER_WIN_LINK.getValue() - 1f) {
                tmp = m;
                ++count;
            }
        }
        
        /**
         * As tmp = m if tmp is = NULL, tmp will = m even if tmp 
         * willEnableTake TRUE - therefor if count = 1 check for 
         * willEnableTake once again - score will be equal to first 
         * non NULL move and coparing score will be irrelevant.
         */
        if (count == 1 && ((tmp.isCapture() || 
            !(new OnyxPosStateSubroutine(tmp.getPos()).willEnableTake(b, c, color))) ||
            tmp.getScore() > OnyxConst.SCORE.COUNTER_WIN_LINK.getValue() - 1f)) {
            return OnyxMoveUtils.isMove(tmp) ? tmp : null;
        }
        
        return count > 1 ? tmp : null;
    }
    
    /**
     * Trim overload - discards NULL, OnyxPos NULL & moves that will result
     * in take opotunities for color oponent.
     * @param moves moves to trim.
     * @param b onyx board instance.
     * @param c onyx position collection instance.
     * @param color depending on color, if move will enable a take for oponent color
     * @param minScore minimum score to qualify.
     * then discard that move.
     * @return move with highest scoring.
     * @throws com.jellyfish.jfgonyx.onyx.exceptions.InvalidOnyxPositionException
     */
    protected final OnyxMove trim(final List<OnyxMove> moves, final OnyxBoard b, 
            final OnyxPosCollection c, final OnyxConst.COLOR color, final float minScore) throws InvalidOnyxPositionException {
        
        final List<OnyxMove> scoredMoves = new ArrayList<>();
        for (OnyxMove m : moves) {
            if (OnyxMoveUtils.isMove(m) && m.getScore() > minScore) scoredMoves.add(m);
        }
        
        return this.trim(scoredMoves, b, c, color);
    }
    
    protected final OnyxMove initCaptures(final OnyxMove tmp, final OnyxBoard b, 
            final OnyxPosCollection c, final OnyxConst.COLOR color) throws InvalidOnyxPositionException {
        
        List<OnyxPos> posSet = null;
        
        final OnyxMove capture = new TakePositionSubroutine().getTakePos(c, b, color.bit);
        if (OnyxMoveUtils.isMove(capture)) posSet = c.getTakePositions(capture.getPos().getKey(), color.bit, b);
        if (OnyxMoveUtils.isMove(capture, tmp) && posSet != null && tmp.getPos().equals(capture.getPos())) {
            tmp.setCaptured(new ArrayList<OnyxPos>());
            tmp.getCaptured().addAll(posSet);
        }
        
        return tmp;
    }
    
}