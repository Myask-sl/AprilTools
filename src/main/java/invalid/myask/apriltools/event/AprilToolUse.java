package invalid.myask.apriltools.event;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import invalid.myask.apriltools.AprilToolsBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class AprilToolUse {
    public static AprilToolUse instance = new AprilToolUse();

    @SubscribeEvent
    public void useATool(PlayerInteractEvent event) {
        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            if (event.world.getBlock(event.x, event.y, event.z) == AprilToolsBlocks.BURNT_TORCH) {
                ItemStack stack = event.entityPlayer.getHeldItem();
                if (stack != null && stack.getItem() == Items.flint_and_steel) {
                    event.useItem = Event.Result.DENY;
                    event.useBlock = Event.Result.DENY;
                    event.entityPlayer.swingItem();
                    stack.attemptDamageItem(1, event.entityPlayer.getRNG());
                    event.entityPlayer.playSound("fire.ignite", 1.0F, event.entityPlayer.getRNG().nextFloat() * 0.4F + 0.8F);
                    event.world.setBlock(event.x, event.y, event.z, Blocks.torch,
                        event.world.getBlockMetadata(event.x, event.y, event.z), 3);
                }
            }
        }
    }
}
