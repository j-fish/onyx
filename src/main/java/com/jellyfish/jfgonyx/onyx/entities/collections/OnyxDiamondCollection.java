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
package com.jellyfish.jfgonyx.onyx.entities.collections;

import com.jellyfish.jfgonyx.onyx.entities.OnyxDiamond;
import com.jellyfish.jfgonyx.onyx.exceptions.InvalidOnyxPositionException;
import com.jellyfish.jfgonyx.onyx.vars.GraphicsVars;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author thw
 */
public class OnyxDiamondCollection {
    
    private final HashMap<Point, OnyxDiamond> diamonds = new HashMap<>();
    
    public final OnyxDiamondCollection build() {
        
        boolean mod = false;
        int k = 0;
        
        for (int i = 0; i < GraphicsVars.getInstance().BOARD_SIDE_SQUARE_COUNT; ++i) {
            for (int j = 0; j < GraphicsVars.getInstance().BOARD_SIDE_SQUARE_COUNT; j++) {
                if ((j % 2 == 0) == mod) {
                    diamonds.put(new Point(j, i), new OnyxDiamond(j, i, true));
                } else {
                    diamonds.put(new Point(j, i), new OnyxDiamond(j, i, false));
                }
            }
            mod = !mod;
        }
        
        return this;
    }
    
    public List<OnyxDiamond> getDiamondsByPosKey(final String key) {
        
        List<OnyxDiamond> dList = new ArrayList<>();
        for (OnyxDiamond d : this.diamonds.values()) {

            for (String k : d.getCornerKeys()) {
                if (key.equals(k) && !dList.contains(d)) dList.add(d);
            }
        }
        
        return dList;
    }
    
    public HashMap<Point, OnyxDiamond> getDiamonds() {
        return diamonds;
    }
      
    public boolean isDiamondCenter(final String k) {
        
        for (OnyxDiamond d : diamonds.values()) {
            try {
                if (d.isFivePosDiamond() && d.getCenterPos().getKey().equals(k)) return true;
            } catch (final InvalidOnyxPositionException IOPEx) { 
                Logger.getLogger(OnyxDiamondCollection.class.getName()).log(Level.SEVERE, IOPEx.getMessage());
                return false;
            }
        }
        
        return false;
    }
    
}
