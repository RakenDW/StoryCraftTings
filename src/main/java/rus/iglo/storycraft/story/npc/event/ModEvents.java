package rus.iglo.storycraft.story.npc.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rus.iglo.storycraft.StoryCraftTing;
import rus.iglo.storycraft.story.npc.ModEntityTypes;
import rus.iglo.storycraft.story.npc.custom.NPCEntity;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = StoryCraftTing.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(ModEntityTypes.NPC.get(), NPCEntity.setAttributes());
        }
    }
}
