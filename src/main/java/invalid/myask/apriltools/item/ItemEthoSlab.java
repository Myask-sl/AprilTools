package invalid.myask.apriltools.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;

import invalid.myask.apriltools.block.BlockEthoSlab;

/**
 * Forge's mode of invoking ItemBlock ctors requires exact class match.
 */
public class ItemEthoSlab extends ItemSlab {
    public ItemEthoSlab(Block block, BlockEthoSlab single, BlockEthoSlab dSlab, Boolean isDoubleslab) {
        super(block, single, dSlab, isDoubleslab);
    }
}
