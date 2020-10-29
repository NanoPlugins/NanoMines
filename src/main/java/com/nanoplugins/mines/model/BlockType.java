package com.nanoplugins.mines.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Material;

@AllArgsConstructor
@Data
public class BlockType implements Comparable<BlockType>{

    private final Material material;
    private final byte data;

    @Override
    public int compareTo(BlockType o) {
        if (material != o.getMaterial() || data != o.getData()) return -1;
        return 0;
    }

}
