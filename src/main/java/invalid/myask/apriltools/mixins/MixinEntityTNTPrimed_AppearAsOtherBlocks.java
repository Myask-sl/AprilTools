package invalid.myask.apriltools.mixins;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;

import io.netty.buffer.ByteBuf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import invalid.myask.apriltools.Config;
import invalid.myask.apriltools.ducks.IBlockFacaded;

@Mixin(EntityTNTPrimed.class)
public class MixinEntityTNTPrimed_AppearAsOtherBlocks implements IBlockFacaded, IEntityAdditionalSpawnData {
    @Unique
    Block apriltools$Skin = Blocks.tnt;
    @Unique
    long apriltools$SkinMeta = 0;

    @Shadow
    public int fuse;

    @Override
    public void apriltools$setFacade(Block newFace) {
        apriltools$Skin = newFace == null ? Blocks.tnt : newFace;
    }

    @Override
    public Block apriltools$getFacade() {
        return apriltools$Skin;
    }

    @Override
    public void apriltools$setFacadeMeta(long in) {
        if (in >= 0 && in <= Config.max_meta)
            apriltools$SkinMeta = in;
    }

    @Override
    public int apriltools$getFacadeMeta() {
        return (int) apriltools$SkinMeta;
    }

    @Override
    public long apriltools$getFacadeMetaL() {
        return apriltools$SkinMeta;
    }

    @Inject(method = "writeEntityToNBT",
    at = @At("TAIL"))
    private void apriltools$writeFacade(NBTTagCompound tagCompound, CallbackInfo ci) {
        tagCompound.setString("facade", apriltools$Skin.getUnlocalizedName().substring(5));
        tagCompound.setLong("facadeMetaL", apriltools$SkinMeta);
    }

    @Inject(method = "readEntityFromNBT",
        at = @At("TAIL"))
    private void apriltools$readFacade(NBTTagCompound tagCompound, CallbackInfo ci) {
        String blockName = tagCompound.getString("facade");
        apriltools$Skin = Block.getBlockFromName(blockName);
        if (apriltools$Skin == null) apriltools$Skin = Blocks.tnt;
        apriltools$SkinMeta = tagCompound.getLong("facadeMetaL");
        if (tagCompound.hasKey("facadeMeta")) apriltools$SkinMeta = tagCompound.getInteger("facadeMeta");
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeLong(Block.getIdFromBlock(apriltools$Skin));
        buffer.writeLong(apriltools$SkinMeta);
        buffer.writeInt(fuse);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        apriltools$Skin = Block.getBlockById((int) additionalData.readLong());
        apriltools$SkinMeta = additionalData.readLong();
        fuse = additionalData.readInt();
    }
}
