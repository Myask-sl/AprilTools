package invalid.myask.apriltools.block;

import net.minecraft.block.BlockColored;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class BlockColoredFragile extends BlockColored {
    public BlockColoredFragile(Material mat) {
        super(mat);
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    protected boolean canSilkHarvest() {
        return true;
    }
}
