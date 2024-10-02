package com.james.tinkerscalibration.modifiers;


import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

@Mod.EventBusSubscriber
public class ChampingModifier extends Modifier {
    @SubscribeEvent
    public static void onCriticalHit(CriticalHitEvent event) {
        Player player = event.getEntity();
        ToolStack tool = ToolStack.from(player.getMainHandItem());
        tool.getModifierList().forEach(modifierEntry -> {
            if (modifierEntry.getModifier() instanceof ChampingModifier) {
                if (RANDOM.nextFloat() <= 0.2f * modifierEntry.getLevel()) {
                    event.setResult(Event.Result.ALLOW);
                }
            }
        });
    }
}
