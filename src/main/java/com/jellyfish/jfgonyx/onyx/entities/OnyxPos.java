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
package com.jellyfish.jfgonyx.onyx.entities;

import com.jellyfish.jfgonyx.onyx.entities.collections.OnyxPosCollection;
import com.jellyfish.jfgonyx.constants.GraphicsConst;
import com.jellyfish.jfgonyx.constants.OnyxConst;
import com.jellyfish.jfgonyx.onyx.exceptions.InvalidOnyxPositionException;
import com.jellyfish.jfgonyx.onyx.search.subroutines.positionsearch.OnyxPosStateSubroutine;
import java.util.List;

/**
 * Onyx position definition.
 * @author thw
 */
public class OnyxPos {
    
    public final float x, y;
    public final int gX, gY;
    private OnyxPiece piece;
    private OnyxVirtualPiece vPiece;
    public final OnyxDiamond diamond;
    public String[] connections;
    public OnyxPosStateSubroutine posHelper = null;
    public final OnyxPosRectangle rectangle;

    public OnyxPos(final float x, final float y, final OnyxDiamond d) {
        this.x = x;
        this.y = y;
        this.gX = ((int) x) * GraphicsConst.SQUARE_WIDTH;
        this.gY = ((int) y) * GraphicsConst.SQUARE_WIDTH;
        this.diamond = d;
        this.init();
        this.rectangle = this.initRectangle(this.x, this.y);
    }
    
    private void init() {
        this.posHelper = new OnyxPosStateSubroutine(this);
    }
    
    public boolean isOccupied() {
        return !(this.piece == null);
    }
    
    public boolean isOccupied(final int bit) {
        return this.isOccupied() && this.piece.color.bit == bit;
    }
    
    public boolean isVirtuallyOccupied() {
        return this.vPiece != null;
    }
    
    public boolean isDiamondCenter() {
        
        try {
            final String k = this.diamond.getCenterPos().getKey();
            return k.equals(this.getKey());
        } catch (final InvalidOnyxPositionException Iopex) {
            return false;
        }
    }
        
    public boolean isLowXBorder() {
        return this.x < 1.1f;
    }

    public boolean isHighXBorder() {
        return this.x > ((float) (OnyxConst.BOARD_SIDE_SQUARE_COUNT + 1)) -.1f;
    }
    
    public boolean isLowYBorder() {
        return this.y > 0f && this.y < 2f;
    }
    
    public boolean isHighYBorder() {
        return this.y > ((float) (OnyxConst.BOARD_SIDE_SQUARE_COUNT + 1)) - .1f;
    }

    public int occursCount(final OnyxPos p, final List<OnyxPos> posSet) {
        
        int count = 0;
        for (OnyxPos pos : posSet) {
            if (p.equals(pos)) ++count;
        }
        
        return count;
    }
    
    public void addPiece(final OnyxPiece p) {
        this.piece = p;
    }
    
    public String getKey() {
        return String.format(OnyxPosCollection.KEY_FORMAT, this.x, this.y);
    }
    
    @Override
    public String toString() {
        return String.format(OnyxPosCollection.KEY_FORMAT, x, y);
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Float.floatToIntBits(this.x);
        hash = 23 * hash + Float.floatToIntBits(this.y);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OnyxPos other = (OnyxPos) obj;
        if (Float.floatToIntBits(this.x) != Float.floatToIntBits(other.x)) {
            return false;
        }
        return Float.floatToIntBits(this.y) == Float.floatToIntBits(other.y);
    } 
    
    public OnyxVirtualPiece getVirtualPiece() {
        return vPiece;
    }

    public void setVirtualPiece(OnyxVirtualPiece vPiece) {
        this.vPiece = vPiece;
    }
        
    public OnyxPiece getPiece() {
        return this.piece;
    }

    public void setPiece(final OnyxPiece piece) {
        this.piece = piece;
    }

    private OnyxPosRectangle initRectangle(final float paramX, final float paramY) {
        
        /**
         * FIXME : improve accuracy.
         */
        
        float pX = paramX, pY = paramY;

        if (!this.diamond.isFivePosDiamond() && !this.isDiamondCenter()) {
            
            if (pX % 2 == 0 && pY % 2 == 0) {
                pX  += (2f / GraphicsConst.SQUARE_WIDTH);
                pY += (2f / GraphicsConst.SQUARE_WIDTH);
            } else {
                if (pX % 2 == 0) {
                    pY += (8f / GraphicsConst.SQUARE_WIDTH);
                } else {
                    pX  += (2f / GraphicsConst.SQUARE_WIDTH);
                }

                if (pY % 2 == 0) {
                    pX += (8f / GraphicsConst.SQUARE_WIDTH);
                } else {
                    pY += (2f / GraphicsConst.SQUARE_WIDTH);
                }
            }
            
        } else {
            pY += (4f / GraphicsConst.SQUARE_WIDTH);
            pX += (4f / GraphicsConst.SQUARE_WIDTH);
        }
        
        return  new OnyxPosRectangle(
            (pX * GraphicsConst.SQUARE_WIDTH) - 16f, 
            (pY * GraphicsConst.SQUARE_WIDTH) - 16f, 32f, 32f
        );
    }
    
}
