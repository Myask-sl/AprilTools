package invalid.myask.apriltools;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class AprilToolsRecipes {
    public static void register() {
        if (Config.usb_charger_enable)
            GameRegistry.addRecipe(new ShapedOreRecipe(AprilToolsBlocks.JOKE_USB_CHARGER,
                "iri",
                "igi",
                    "iii",
                'i', "ingotIron",
                'r', "dustRedstone",
                'g', "ingotGold"
            ));
        if (Config.tinted_glass_2_enable && Config.tinted_glass_2_original_recipe) {
            for (int i = 0; i < 16; i++) {
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AprilToolsBlocks.TINTED_GLASS_2_0, 8, i),
                    "GGG",
                    "GdG",
                    "GGG",
                    'G', Blocks.glass,
                    'd', new ItemStack(Items.dye, 1, i)
                ));
            }
        }
    }
}
