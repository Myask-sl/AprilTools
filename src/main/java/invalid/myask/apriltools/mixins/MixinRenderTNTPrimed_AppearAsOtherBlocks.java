package invalid.myask.apriltools.mixins;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderTNTPrimed;
import net.minecraft.entity.item.EntityTNTPrimed;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import invalid.myask.apriltools.block.IBlockBoundsByMeta;
import invalid.myask.apriltools.ducks.IBlockFacaded;

@Mixin(RenderTNTPrimed.class)
public class MixinRenderTNTPrimed_AppearAsOtherBlocks {
    @WrapOperation(method = "doRender(Lnet/minecraft/entity/item/EntityTNTPrimed;DDDFF)V",
    at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderBlocks;renderBlockAsItem(Lnet/minecraft/block/Block;IF)V"))
    private void reskinBlock(RenderBlocks instance, Block tnt_ignored, int zero_meta_ignored, float brightness,
                             Operation<Void> original, @Local(argsOnly = true) EntityTNTPrimed entityTNTPrimed) {
        Block facade = ((IBlockFacaded) entityTNTPrimed).apriltools$getFacade();
        int facadeMeta = ((IBlockFacaded) entityTNTPrimed).apriltools$getFacadeMeta();
        if (facade instanceof IBlockBoundsByMeta ibm)
            ibm.setBlockBoundsBasedOnMeta(facadeMeta); //TODO: write a whole damn ISBRH to make meta-sensitive things??
        original.call(instance, facade, facadeMeta, brightness);
    }
}
