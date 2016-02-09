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
package com.jellyfish.jfgonyx.onyx;

import com.jellyfish.jfgonyx.constants.GraphicsConst;
import com.jellyfish.jfgonyx.onyx.entities.OnyxMove;
import com.jellyfish.jfgonyx.onyx.entities.collections.OnyxPosCollection;
import com.jellyfish.jfgonyx.onyx.exceptions.InvalidOnyxPositionException;
import com.jellyfish.jfgonyx.onyx.exceptions.NoValidOnyxPositionsFoundException;
import com.jellyfish.jfgonyx.onyx.interfaces.search.OnyxAbstractSearchable;
import com.jellyfish.jfgonyx.onyx.interfaces.search.OnyxConnectionSearchable;
import com.jellyfish.jfgonyx.onyx.search.*;
import com.jellyfish.jfgonyx.ui.OnyxBoard;
import java.util.HashMap;

/**
 * @author thw
 */
class Onyx {
    
    private final static HashMap<SEARCH_TYPE, OnyxAbstractSearchable> SEARCH = new HashMap<>();
    static {
        SEARCH.put(SEARCH_TYPE.ONYXPOSCOL, new PositionSearch());
        SEARCH.put(SEARCH_TYPE.RANDOM, new RandomSearch());
        SEARCH.put(SEARCH_TYPE.INTMAP, new IntmapSearch());
        SEARCH.put(SEARCH_TYPE.CNX, new ConnectionSearch());
    }
    
    static enum SEARCH_TYPE {
        
        RANDOM("Random dumb search :X"), 
        ONYXPOSCOL("Use onyx position collection for take or counter position searches."),
        INTMAP("Integer map search."), 
        CNX("Connection search style building & taking advantage of position trees.");
        
        private final String desc;
        
        SEARCH_TYPE(final String desc) {
            this.desc = desc;
        }   
    }
       
    public static OnyxMove search(final OnyxPosCollection c, final OnyxBoard board, final GraphicsConst.COLOR color) 
            throws NoValidOnyxPositionsFoundException, InvalidOnyxPositionException {
        
        final OnyxMove m = SEARCH.get(SEARCH_TYPE.ONYXPOSCOL).search(c, board, color);
        System.out.println(
            ((OnyxConnectionSearchable) SEARCH.get(SEARCH_TYPE.CNX)).isWin(c, GraphicsConst.COLOR.getOposite(color.boolColor)) ? 
            "WIN ! WuHu !!!" : "Not a win yet :C ...");
        return m;
    }
        
}
