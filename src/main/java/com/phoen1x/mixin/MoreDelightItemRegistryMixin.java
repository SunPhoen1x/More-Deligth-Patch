package com.phoen1x.mixin;

import com.axperty.moredelight.registry.ItemRegistry;
import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.core.api.other.PolymerStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.entry.RegistryEntry;
import com.phoen1x.impl.item.PolyBaseItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemRegistry.class)
public class MoreDelightItemRegistryMixin {
    @Inject(method = "knife(Ljava/lang/String;Lnet/minecraft/item/ToolMaterial;FF)Lnet/minecraft/item/Item;", at = @At("TAIL"))
    private static void polymerifyKnife(String name, ToolMaterial material, float attackDamage, float attackSpeed, CallbackInfoReturnable<Item> cir) {
        Item item = cir.getReturnValue();
        PolymerItem polymerItem = new PolyBaseItem(item);
        PolymerItem.registerOverlay(item, polymerItem);
    }

    @Inject(method = "consumable(Ljava/lang/String;IFILnet/minecraft/item/Item;)Lnet/minecraft/item/Item;", at = @At("TAIL"))
    private static void polymerifyConsumable(String name, int nutrition, float saturation, int maxCount, Item remainder, CallbackInfoReturnable<Item> cir) {
        Item item = cir.getReturnValue();
        PolymerItem polymerItem = new PolyBaseItem(item);
        PolymerItem.registerOverlay(item, polymerItem);
    }

    @Inject(method = "consumableEffect(Ljava/lang/String;IFLnet/minecraft/registry/entry/RegistryEntry;IIILnet/minecraft/item/Item;)Lnet/minecraft/item/Item;", at = @At("TAIL"))
    private static void polymerifyConsumableEffect(String name, int nutrition, float saturation, RegistryEntry<StatusEffect> effect, int duration, int amplifier, int maxCount, Item remainder, CallbackInfoReturnable<Item> cir) {
        Item item = cir.getReturnValue();
        PolymerItem polymerItem = new PolyBaseItem(item);
        PolymerItem.registerOverlay(item, polymerItem);
        PolymerStatusEffect.registerOverlay(effect.value());
    }
}