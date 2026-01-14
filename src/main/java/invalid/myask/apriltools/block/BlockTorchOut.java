package invalid.myask.apriltools.block;

import net.minecraft.block.BlockTorch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockTorchOut extends BlockTorch {
    public BlockTorchOut() {
        super();
        setTickRandomly(false);
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return super.getLightValue(world, x, y, z);
    }

    @Override
    public void randomDisplayTick(World worldIn, int x, int y, int z, Random random) {}

    @Override
    public void onBlockClicked(World worldIn, int x, int y, int z, EntityPlayer player) {
        super.onBlockClicked(worldIn, x, y, z, player);
    }
}
