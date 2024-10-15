package com.telia.adventure_utilities.events;

import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import static com.telia.adventure_utilities.AdventureUtilities.THROWABLE_ADD_DAMAGE_BONUS;
import static com.telia.adventure_utilities.AdventureUtilities.THROWABLE_MULT_DAMAGE_BONUS;

public class OnLivingIncomingDamageEvent {
    public static void onIncomingDamage(LivingIncomingDamageEvent event) {
        float currentDamage = event.getAmount();

        if(!(event.getSource().getEntity() instanceof Player owner))
            return;

        if(owner.level().isClientSide())
            return;

        if(event.getSource().getDirectEntity() instanceof ThrowableItemProjectile projectile){
            AttributeInstance temp = owner.getAttribute(THROWABLE_MULT_DAMAGE_BONUS);
            if(temp != null)
                currentDamage *= (float) temp.getValue();

            temp = owner.getAttribute(THROWABLE_ADD_DAMAGE_BONUS);
            if(temp != null)
                currentDamage += (float) temp.getValue();
        }

        event.setAmount(currentDamage);
    }
}
