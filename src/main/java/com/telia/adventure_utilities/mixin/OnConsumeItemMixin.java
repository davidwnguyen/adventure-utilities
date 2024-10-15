package com.telia.adventure_utilities.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

import static com.telia.adventure_utilities.AdventureUtilities.THROWABLE_CONSUME_CHANCE;

@Mixin(value = ItemStack.class, remap = false)
public abstract class OnConsumeItemMixin {
    @Inject(method = "consume", at = @At("HEAD"), cancellable = true)
    public void adventure_ConsumeRecycle(int pAmount, LivingEntity pEntity, CallbackInfo ci){
        if(this.getItem() instanceof ProjectileItem)
        {
            AttributeInstance temp = pEntity.getAttribute(THROWABLE_CONSUME_CHANCE);
            if(temp == null)
                return;

            if(temp.getValue() >= new Random().nextDouble()) {
                ci.cancel();
            }
        }
    }

    @Shadow
    public abstract Item getItem();
}
