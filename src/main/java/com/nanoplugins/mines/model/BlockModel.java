package com.nanoplugins.mines.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BlockModel {

    private final BlockType blockType;
    private final String displayName;
    private final double money;

}