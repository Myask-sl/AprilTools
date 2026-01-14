package invalid.myask.apriltools;

import cpw.mods.fml.common.registry.GameRegistry;
import invalid.myask.apriltools.block.BlockColoredFragile;
import invalid.myask.apriltools.block.BlockJokeUSBCharger;
import invalid.myask.apriltools.block.BlockTorchOut;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemCloth;

public class AprilToolsBlocks {
    public static final Block JOKE_USB_CHARGER = new BlockJokeUSBCharger().setBlockName("usb_charger").setHardness(3)
        .setResistance(30);
    public static final Block TINTED_GLASS_2_0 = new BlockColoredFragile(Material.glass).setStepSound(Block.soundTypeGlass)
        .setHardness(0.3F);
    public static final Block BURNT_TORCH = new BlockTorchOut().setHardness(0.0F).setLightLevel(0);

    public static final CreativeTabs TAB_APRIL_BLOCKS = new CreativeTabs("april_tools_blocks") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(BURNT_TORCH);
        }
    };

    private static void setNames(Block b, String name) {
        b.setBlockName(name);
        b.setBlockTextureName(AprilTools.MODID + ":" + name);
    }

    public static void register() {
        registerABlock(JOKE_USB_CHARGER, "usb_charger", CreativeTabs.tabRedstone);
            JOKE_USB_CHARGER.setHarvestLevel("pickaxe", 0);
        registerABlockTabbed(TINTED_GLASS_2_0, ItemCloth.class, "tinted_glass_2_0", CreativeTabs.tabDecorations, TAB_APRIL_BLOCKS);
        registerABlock(BURNT_TORCH, "torch_out", CreativeTabs.tabMisc);
    }

    static void registerABlock(Block applicant, String nom, CreativeTabs vanillaTab, Object... itemBlockCtorArgs) {
        registerABlockTabbed(applicant, ItemBlock.class, nom, vanillaTab, TAB_APRIL_BLOCKS, itemBlockCtorArgs);
    }

    static void registerABlockTabbed(Block applicant, Class<? extends ItemBlock> itemclass, String nom, CreativeTabs vanillaTab, CreativeTabs modTab, Object... itemBlockCtorArgs) {
        setNames(applicant, nom);
        GameRegistry.registerBlock(applicant, itemclass, nom, itemBlockCtorArgs);
        applicant.setCreativeTab(Config.use_vanilla_tabs ? vanillaTab : modTab);
    }
}
