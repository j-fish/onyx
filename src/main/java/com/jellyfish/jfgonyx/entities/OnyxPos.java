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
package com.jellyfish.jfgonyx.entities;

import com.jellyfish.jfgonyx.constants.GraphicsConst;

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

    public OnyxPos(final float x, final float y, final OnyxDiamond d) {
        this.x = x;
        this.y = y;
        this.gX = ((int) x) * GraphicsConst.SQUARE_WIDTH;
        this.gY = ((int) y) * GraphicsConst.SQUARE_WIDTH;
        this.diamond = d;
    }
    
    public boolean isOccupied() {
        return !(this.piece == null);
    }
    
    boolean isOccupied(final int bitColor) {
        return this.isOccupied() && this.piece.color.bitColor == bitColor;
    }
    
    public boolean isVirtuallyOccupied() {
        return this.vPiece != null;
    }
    
    public boolean isDiamondCenter() {
        return this.x % 2 != 0 && this.y % 2 != 0;
    }
        
    public void addPiece(final OnyxPiece p) {
        this.piece = p;
    }
    
    public String getKey() {
        return String.format(OnyxPosCollection.KEY_FORMAT, this.x, this.y);
    }
    
    public OnyxPiece getPiece() {
        return this.piece;
    }

    public void setPiece(final OnyxPiece piece) {
        this.piece = piece;
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
    
}