package com.telia.adventure_utilities.events;

import com.telia.adventure_utilities.AdventureUtilities;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.HashMap;

import static com.telia.adventure_utilities.AdventureUtilities.THROWABLE_ADD_DAMAGE_BONUS;
import static com.telia.adventure_utilities.AdventureUtilities.THROWABLE_MULT_DAMAGE_BONUS;

public class OnLivingIncomingDamageEvent {
    private static final HashMap<Entity, HashMap<Entity, Long>> HurtMap = new HashMap<>();
    private static final HashMap<Entity, Long> EnvironmentalHurtMap = new HashMap<>();

    public static void onIncomingDamage(LivingIncomingDamageEvent event) {
        if(event.getEntity().level().isClientSide())
            return;

        float currentDamage = event.getAmount();

        DamageSource source = event.getSource();
        Long currentTime = event.getEntity().level().getLevelData().getGameTime();

        if(source.getDirectEntity() == null){
            if(EnvironmentalHurtMap.getOrDefault(event.getEntity(), 0L) >= currentTime) {
                event.setCanceled(true);
                return;
            }
            EnvironmentalHurtMap.put(event.getEntity(), currentTime+10);
        }else {
            HashMap<Entity,Long> EntityInvulnerability = HurtMap.get(event.getEntity());
            if(EntityInvulnerability != null){
                if(EntityInvulnerability.getOrDefault(source.getDirectEntity(), 0L) >= currentTime) {
                    event.setCanceled(true);
                    return;
                }
                EntityInvulnerability.put(source.getDirectEntity(), currentTime+5);
            }else{
                EntityInvulnerability = new HashMap<>();
                EntityInvulnerability.put(source.getDirectEntity(), currentTime+5);
                HurtMap.put(event.getEntity(), EntityInvulnerability);
            }
        }

        event.getEntity().invulnerableTime = 0;

        if(!(event.getSource().getEntity() instanceof Player owner))
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
