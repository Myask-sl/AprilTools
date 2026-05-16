package invalid.myask.apriltools;

import cpw.mods.fml.common.registry.EntityRegistry;

import invalid.myask.apriltools.entity.EntityBoobyTrapPrimed;

public class AprilToolsEntities {
    public static void register() {
        int id = 0;

        EntityRegistry.registerModEntity(EntityBoobyTrapPrimed.class, "booby_trap", id++, AprilTools.instance, 32, 10, true);

        EntityRegistry.instance().lookupModSpawn(EntityBoobyTrapPrimed.class, false).setCustomSpawning(null, false);
        //extenders of vanilla entities must use this to not be subsumed in packet

    }
}
