package invalid.myask.apriltools.mixins;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderTNTPrimed;
import net.minecraft.entity.item.EntityTNTPrimed;

import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import invalid.myask.apriltools.ducks.IBlockFacaded;

@Mixin(RenderTNTPrimed.class)
public class MixinRenderTNTPrimed_AppearAsOtherBlocks {
    @ModifyArg(method = "doRender(Lnet/minecraft/entity/item/EntityTNTPrimed;DDDFF)V",
    at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderBlocks;renderBlockAsItem(Lnet/minecraft/block/Block;IF)V"))
    private Block reskinBlock(Block original, @Local(argsOnly = true) EntityTNTPrimed entityTNTPrimed) {
        return ((IBlockFacaded) entityTNTPrimed).apriltools$getFacade();
    }
}
