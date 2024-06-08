package rus.iglo.storycraft.story.event;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rus.iglo.storycraft.StoryCraftTing;
import rus.iglo.storycraft.script.script;

import static rus.iglo.storycraft.StoryCraftTing.playerall;

@Mod.EventBusSubscriber
public class event {
    @SubscribeEvent
    public static void e(PlayerEvent.PlayerLoggedInEvent event) {
        playerall = event.getEntity();
        script.y();
    }
}
