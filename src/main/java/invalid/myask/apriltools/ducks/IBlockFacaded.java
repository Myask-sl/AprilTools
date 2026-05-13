package invalid.myask.apriltools.ducks;

import net.minecraft.block.Block;

public interface IBlockFacaded {
    default void apriltools$setFacade(Block newFace, int meta) {
        apriltools$setFacade(newFace);
        apriltools$setFacadeMeta(meta);
    }
    void apriltools$setFacade(Block newFace);
    Block apriltools$getFacade();

    void apriltools$setFacadeMeta(int in);
    int apriltools$getFacadeMeta();
}
