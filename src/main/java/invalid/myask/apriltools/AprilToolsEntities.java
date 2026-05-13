package invalid.myask.apriltools;

import cpw.mods.fml.common.registry.EntityRegistry;

import invalid.myask.apriltools.entity.EntityBoobyTrapPrimed;

public class AprilToolsEntities {
    public static void register() {
        int id = 0;

        EntityRegistry.registerModEntity(EntityBoobyTrapPrimed.class, "boobyTrap", id++, AprilTools.instance, 30, 1, true);

        EntityRegistry.EntityRegistration er = EntityRegistry.instance().lookupModSpawn(EntityBoobyTrapPrimed.class, false);
        er.setCustomSpawning(null, false);
        //extenders of vanilla entities must use this to not be subsumed in packet

    }
}
