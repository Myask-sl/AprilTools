package invalid.myask.apriltools.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockEthoSlab extends BlockSlab implements ITNTLike {
    public BlockEthoSlab(boolean isDouble) {
        super(isDouble, Material.tnt);
        useNeighborBrightness = !isDouble;
    }

    @Override
    public String func_150002_b(int stackDamage) {
        return (stackDamage & 1) == 0 ? "tnt_slab" : "volatile_tnt_slab";
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return Blocks.tnt.getBlockTextureFromSide(side);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {}

    public boolean shouldSideBeRendered(IBlockAccess worldIn, int x, int y, int z, int side)
    {
        if (this.field_150004_a) //am double slab
            return super.shouldSideBeRendered(worldIn, x, y, z, side);
        else if (side != 1 && side != 0 && !super.shouldSideBeRendered(worldIn, x, y, z, side))
            return false;
        else
        {
            int myX = x + Facing.offsetsXForSide[Facing.oppositeSide[side]],
                myY = y + Facing.offsetsYForSide[Facing.oppositeSide[side]],
                myZ = z + Facing.offsetsZForSide[Facing.oppositeSide[side]];
            boolean amTopSlab = (worldIn.getBlockMetadata(myX, myY, myZ) & 8) != 0,
                itIsASlab = !(worldIn.getBlock(x, y, z) instanceof BlockSlab),
                itIsATopSlab = (worldIn.getBlockMetadata(x, y, z) & 8) != 0;
            return amTopSlab ? (side == 0 || (side == 1 && super.shouldSideBeRendered(worldIn, x, y, z, side) || itIsASlab || !itIsATopSlab))
                : (side == 1 || (side == 0 && super.shouldSideBeRendered(worldIn, x, y, z, side) || itIsASlab || itIsATopSlab));
        }
    }

    @Override
    public boolean amToony() {
        return true;
    }

    //Begin the TNT block-like functions, because you can't multiply-inherit in Java...
    public void onBlockAdded(World worldIn, int x, int y, int z)
    {
        super.onBlockAdded(worldIn, x, y, z);
        reactToPower(worldIn, x, y, z);
    }

    @Override
    public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block neighbor) {
        reactToPower(worldIn, x, y, z);
    }

    @Override
    public void onBlockDestroyedByExplosion(World worldIn, int x, int y, int z, Explosion explosionIn) {
        explodeMe(worldIn, x, y, z, explosionIn.getExplosivePlacedBy(), true, false);
    }

    @Override
    public void onBlockDestroyedByPlayer(World worldIn, int x, int y, int z, int meta) {
        explodeIfVolatile(worldIn, x, y, z, meta, null);
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
        if (player.getCurrentEquippedItem() != null
            && player.getCurrentEquippedItem().getItem() == Items.flint_and_steel) {
            explodeMe(worldIn, x, y, z, player);
            worldIn.setBlockToAir(x, y, z);
            return true;
        }
        return super.onBlockActivated(worldIn, x, y, z, player, side, subX, subY, subZ);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, int x, int y, int z, Entity entityIn) {
        if (!worldIn.isRemote && entityIn.isBurning()) { //Anything on fire will set these off, not just arrows.
            Entity e = entityIn;
            if (entityIn instanceof EntityArrow arrow) e = arrow.shootingEntity;
            else if (entityIn instanceof EntityFireball ball) e = ball.shootingEntity;
            explodeMe(worldIn, x, y, z, e instanceof EntityLivingBase elb ? elb : null);
            worldIn.setBlockToAir(x, y, z);
        }
    }

    @Override
    public boolean canDropFromExplosion(Explosion explosionIn) {
        return false;
    }
}
