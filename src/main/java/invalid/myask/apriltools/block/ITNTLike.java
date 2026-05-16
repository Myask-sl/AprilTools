package invalid.myask.apriltools.block;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

import invalid.myask.apriltools.ducks.IBlockFacaded;
import invalid.myask.apriltools.entity.EntityBoobyTrapPrimed;

/**
 * Only use on Blocks.
 */
public interface ITNTLike {
    //re-used code for TNT-like things
    default void explodeIfVolatile(World w, int x, int y, int z, int meta, EntityLivingBase theDynamiterByRobertLouisStevenson)
    {
        if ((meta & 1) == 1) explodeMe(w, x, y, z, theDynamiterByRobertLouisStevenson);
    }

    default void explodeMe(World w, int x, int y, int z, EntityLivingBase theDynamiterByRobertLouisStevenson) {
        explodeMe(w, x, y, z, theDynamiterByRobertLouisStevenson, false, true);
    }
    default void explodeMe(World w, int x, int y, int z, EntityLivingBase theDynamiterByRobertLouisStevenson, boolean shortenFuse, boolean sound) {
        if (!w.isRemote) {
            EntityBoobyTrapPrimed halfABombOrMore = new EntityBoobyTrapPrimed(w, ((double) x + 0.5), ((double) y + 0.5), ((double) z + 0.5), theDynamiterByRobertLouisStevenson);
            ((IBlockFacaded)halfABombOrMore).apriltools$setFacade((Block)this, w.getBlockMetadata(x, y, z));
            halfABombOrMore.setToon(amToony());
            if (shortenFuse)
                halfABombOrMore.fuse = w.rand.nextInt(halfABombOrMore.fuse / 4) + halfABombOrMore.fuse / 8;
            w.spawnEntityInWorld(halfABombOrMore);
            if (sound)
                w.playSoundAtEntity(halfABombOrMore, "game.tnt.primed", 1, 1);
        }
    }

    default void reactToPower(World w, int x, int y, int z)
    {
        if (w.isBlockIndirectlyGettingPowered(x, y, z))
        {
            explodeMe(w, x, y, z, null);
            w.setBlockToAir(x, y, z);
        }
    }

    default boolean amToony() {return false;}
}
