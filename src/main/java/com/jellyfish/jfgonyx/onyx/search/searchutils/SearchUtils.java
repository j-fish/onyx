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
package com.jellyfish.jfgonyx.onyx.search.searchutils;

import com.jellyfish.jfgonyx.onyx.constants.OnyxConst;
import com.jellyfish.jfgonyx.onyx.OnyxGame;
import com.jellyfish.jfgonyx.onyx.entities.OnyxMove;
import com.jellyfish.jfgonyx.onyx.exceptions.NoValidOnyxPositionsFoundException;

/**
 * @author thw
 */
public class SearchUtils {
    
    public static OnyxMove assertByScore(final OnyxMove ... moves) throws NoValidOnyxPositionsFoundException {

        int r = -1;
        float score = -1f;
        for (int i = 0; i < moves.length; i++) {
            if ((OnyxMoveUtils.isMove(moves[i])) && (r < 0 || moves[i].getScore() > score)) {
                r = i;
                score = moves[i].getScore();
            }
        }
        
        if (r >= 0 && score > 0) return moves[r];
        
        throw new NoValidOnyxPositionsFoundException();
    }
    
    @Deprecated
    public static float calibrateCenterMoves(final OnyxGame game, final float score) {
        return game.getMoveCount() < 12 ? OnyxConst.SCORE.OVERRIDE.getValue() + score : score;
    }
    
    @Deprecated
    public static float calibrateTailMoves(final OnyxGame game, final float score) {
        return (game.getMoveCount() < 14) ?
            OnyxConst.SCORE.OVERRIDE.getValue() + score : score;              
    }
    
}
