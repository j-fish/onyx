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
package com.jellyfish.jfgonyx.onyx.search.subroutines.connectionsearch;

import com.jellyfish.jfgonyx.onyx.abstractions.AbstractSubroutine;
import com.jellyfish.jfgonyx.onyx.constants.OnyxConst;
import com.jellyfish.jfgonyx.onyx.entities.OnyxPos;
import com.jellyfish.jfgonyx.onyx.entities.OnyxTail;
import com.jellyfish.jfgonyx.onyx.entities.collections.OnyxPosCollection;
import com.jellyfish.jfgonyx.ui.OnyxBoard;
import java.util.ArrayList;
import java.util.List;

/**
 * @author thw
 * @deprecated FIXME figure this out...
 */
public class VirtualConnectionSubroutine extends AbstractSubroutine {
    
    protected final AbstractSubroutine.SUBROUTINE_TYPE type;
    protected final OnyxPosCollection c;
    protected final OnyxBoard board;
    protected final OnyxConst.COLOR color;
    private final List<OnyxTail> tails = new ArrayList<>();
    
    public VirtualConnectionSubroutine(final OnyxPosCollection c, final OnyxConst.COLOR color, 
            final OnyxBoard board) {
        this.c = c;
        this.color = color;
        this.board = board;
        this.type = AbstractSubroutine.SUBROUTINE_TYPE.VCNX;
    }
    
    /**
     * @param sPoss start positions - low borders of color to search for.
     */
    public void seekTails(final OnyxPos ... sPoss) {
        
        OnyxTail t = null;
        OnyxPos n = null;
        
        for (OnyxPos p : sPoss) {
            
            t = new OnyxTail();
            do {
                n = next(n == null ? p : n);
                if (n != null) t.append(n);
            } while (n != null);
            
            this.tails.add(t);
        }
    }
    
    private OnyxPos next(final OnyxPos p) {
        return null;
    }
    
}
