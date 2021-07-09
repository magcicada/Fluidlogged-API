package git.jbredwards.fluidlogged_api;

import git.jbredwards.fluidlogged_api.client.util.FluidloggedTESR;
import git.jbredwards.fluidlogged_api.common.block.BlockFluidloggedTE;
import git.jbredwards.fluidlogged_api.common.block.TileEntityFluidlogged;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraftforge.fluids.DispenseFluidContainer;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static git.jbredwards.fluidlogged_api.util.FluidloggedConstants.*;

/**
 *
 * @author jbred
 *
 */
@Mod(modid = MODID, name = NAME, version = VERSION)
public final class Fluidlogged
{
    //vanilla fluidlogged te's, the modded ones get registered automatically through FluidPlugin
    public static final BlockFluidloggedTE WATERLOGGED_TE = new BlockFluidloggedTE(FluidRegistry.WATER, Material.WATER);
    public static final BlockFluidloggedTE LAVALOGGED_TE = new BlockFluidloggedTE(FluidRegistry.LAVA, Material.LAVA);
    static {
        FLUIDLOGGED_TE_LOOKUP.put(FluidRegistry.WATER, WATERLOGGED_TE);
        FLUIDLOGGED_TE_LOOKUP.put(FluidRegistry.LAVA, LAVALOGGED_TE);
    }

    @SuppressWarnings("unused")
    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        //fixes the vanilla bucket dispenser action
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.WATER_BUCKET, DispenseFluidContainer.getInstance());
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.LAVA_BUCKET, DispenseFluidContainer.getInstance());
        //tile entity special renderer (does nothing by default, only for IFluidloggable blocks that choose to use it)
        if(event.getSide() == Side.CLIENT) clInit();
    }

    //separate method to fix serverside crash
    @SideOnly(Side.CLIENT)
    private static void clInit() { ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFluidlogged.class, new FluidloggedTESR()); }
}
