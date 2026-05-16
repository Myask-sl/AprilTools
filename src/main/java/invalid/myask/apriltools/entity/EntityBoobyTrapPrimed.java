package invalid.myask.apriltools.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import invalid.myask.apriltools.Config;

public class EntityBoobyTrapPrimed extends EntityTNTPrimed {
    boolean toon = false;
    public EntityBoobyTrapPrimed(World worldIn) {
        super(worldIn);
    }

    public EntityBoobyTrapPrimed(World world, double x, double y, double z, EntityLivingBase blame) {
        super(world, x, y, z, blame);
    }

    public void setToon(boolean in) {
        toon = in;
    }

    public boolean isToon() {
        return toon;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (isToon() && fuse == 0 && rand.nextFloat() < Config.anvil_drop_chance) {
            List<EntityPlayer> list = worldObj.selectEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(20,20,20), (Entity e)->!e.isEntityInvulnerable());
            if (!list.isEmpty()) {
                EntityPlayer cartoon_victim = list.get(0);
                for (int i = 1; i < list.size(); i++) //faster than a sort
                    if (getDistanceSqToEntity(list.get(i)) < getDistanceSqToEntity(cartoon_victim)) cartoon_victim = list.get(i);
                if (!worldObj.isRemote && worldObj.isAirBlock((int)cartoon_victim.posX, (int)cartoon_victim.posY + 2, (int)cartoon_victim.posZ)) {
                    worldObj.spawnEntityInWorld(new EntityFallingBlock(worldObj,
                        (int)cartoon_victim.posX + 0.5F, cartoon_victim.posY + cartoon_victim.height + 1, ((int)cartoon_victim.posZ) + 0.5F,
                        Blocks.anvil));
                }
            }
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setBoolean("toon", toon);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tagCompund) {
        super.readEntityFromNBT(tagCompund);
        toon = tagCompund.getBoolean("toon");
    }
}
