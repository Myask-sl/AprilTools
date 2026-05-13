package invalid.myask.apriltools.mixins;

import net.minecraft.client.renderer.entity.RenderTNTPrimed;
import net.minecraft.entity.item.EntityTNTPrimed;

import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import invalid.myask.apriltools.ducks.IBlockFacaded;

@Mixin(RenderTNTPrimed.class)
public class MixinRenderTNTPrimed_AppearAsOtherBlocks {
    @ModifyArgs(method = "doRender(Lnet/minecraft/entity/item/EntityTNTPrimed;DDDFF)V",
    at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderBlocks;renderBlockAsItem(Lnet/minecraft/block/Block;IF)V"))
    private void reskinBlock(Args args, @Local(argsOnly = true) EntityTNTPrimed entityTNTPrimed) {
        args.set(0, ((IBlockFacaded) entityTNTPrimed).apriltools$getFacade());
        args.set(1, ((IBlockFacaded) entityTNTPrimed).apriltools$getFacadeMeta());
    }
}
