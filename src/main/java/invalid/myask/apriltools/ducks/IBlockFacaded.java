package invalid.myask.apriltools.ducks;

import net.minecraft.block.Block;

public interface IBlockFacaded {
    default void apriltools$setFacade(Block newFace, long meta) {
        apriltools$setFacade(newFace);
        apriltools$setFacadeMeta(meta);
    }
    void apriltools$setFacade(Block newFace);
    Block apriltools$getFacade();

    void apriltools$setFacadeMeta(long in);
    int apriltools$getFacadeMeta();
    long apriltools$getFacadeMetaL();
}
