package com.telia.adventure_utilities.events;

import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;

import static com.telia.adventure_utilities.events.OnLivingIncomingDamageEvent.EnvironmentalHurtMap;
import static com.telia.adventure_utilities.events.OnLivingIncomingDamageEvent.HurtMap;

public class OnEntityLeaveLevelEvent {
    public static void onEntityRemoved(EntityLeaveLevelEvent event) {
        if(event.getLevel().isClientSide())
            return;

        if(!(event.getEntity() instanceof LivingEntity ent))
            return;

        EnvironmentalHurtMap.remove(ent);
        HurtMap.remove(ent);
    }
}
