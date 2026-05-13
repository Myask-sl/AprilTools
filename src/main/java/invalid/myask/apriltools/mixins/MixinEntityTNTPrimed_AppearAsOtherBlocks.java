package invalid.myask.apriltools.mixins;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import invalid.myask.apriltools.Config;
import invalid.myask.apriltools.ducks.IBlockFacaded;

@Mixin(EntityTNTPrimed.class)
public class MixinEntityTNTPrimed_AppearAsOtherBlocks implements IBlockFacaded {
    @Unique
    Block apriltools$Skin = Blocks.tnt;
    @Unique
    int apriltools$SkinMeta = 0;

    @Override
    public void apriltools$setFacade(Block newFace) {
        apriltools$Skin = newFace == null ? Blocks.tnt : newFace;
    }

    @Override
    public Block apriltools$getFacade() {
        return apriltools$Skin;
    }

    @Override
    public void apriltools$setFacadeMeta(int in) {
        if (in >= 0 && in <= Config.max_meta)
            apriltools$SkinMeta = in;
    }

    @Override
    public int apriltools$getFacadeMeta() {
        return apriltools$SkinMeta;
    }

    @Inject(method = "writeEntityToNBT",
    at = @At("TAIL"))
    private void apriltools$writeFacade(NBTTagCompound tagCompound, CallbackInfo ci) {
        tagCompound.setString("facade", apriltools$Skin.getUnlocalizedName().substring(5));
        tagCompound.setInteger("facadeMeta", apriltools$SkinMeta);
    }

    @Inject(method = "readEntityFromNBT",
        at = @At("TAIL"))
    private void apriltools$readFacade(NBTTagCompound tagCompound, CallbackInfo ci) {
        String blockName = tagCompound.getString("facade");
        apriltools$Skin = Block.getBlockFromName(blockName);
        if (apriltools$Skin == null) apriltools$Skin = Blocks.tnt;
        apriltools$SkinMeta = tagCompound.getInteger("facadeMeta");
    }
}
