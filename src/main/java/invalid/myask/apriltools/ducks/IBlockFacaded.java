package invalid.myask.apriltools.ducks;

import net.minecraft.block.Block;

public interface IBlockFacaded { //no meta
    void apriltools$setFacade(Block newFace);
    Block apriltools$getFacade();
}
