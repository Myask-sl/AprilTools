package invalid.myask.apriltools.block;

import invalid.myask.apriltools.AprilTools;
import net.minecraft.block.BlockCompressedPowered;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

public class BlockJokeUSBCharger extends BlockCompressedPowered {
    IIcon topIcon, bottomIcon;

    public BlockJokeUSBCharger() {
        super(MapColor.ironColor);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        blockIcon = reg.registerIcon(AprilTools.MODID + ":usb_charger_side");
        topIcon = reg.registerIcon(AprilTools.MODID + ":usb_charger_top");
        bottomIcon = reg.registerIcon(AprilTools.MODID + ":usb_charger_bottom");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return side == 1 ? topIcon : side == 0 ? bottomIcon : blockIcon;
    }
}
