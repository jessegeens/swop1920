package utilities;

import model.blocks.ModelBlock;

public class BlockAction {

    private final ProgramLocation select;
    private final ProgramLocation release;
    private final ModelBlock block;

    public BlockAction(ProgramLocation select, ProgramLocation release, ModelBlock block) {
        this.select = select;
        this.release = release;
        this.block = block;
    }

    public ProgramLocation getSelect() {
        return select;
    }

    public ProgramLocation getRelease() {
        return release;
    }

    public ModelBlock getBlock() {
        return block;
    }
}

