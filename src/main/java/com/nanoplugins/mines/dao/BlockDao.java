package com.nanoplugins.mines.dao;

import com.nanoplugins.mines.model.BlockModel;
import com.nanoplugins.mines.model.BlockType;

import java.util.HashMap;

public class BlockDao {

    private final HashMap<BlockType, BlockModel> blocks = new HashMap<>();

    public void add(BlockModel blockModel) {
        blocks.put(blockModel.getBlockType(), blockModel);
    }

    public BlockModel get(BlockType blockType) {
        return blocks.get(blockType);
    }

    public HashMap<BlockType, BlockModel> getBlocks() {
        return blocks;
    }

}
