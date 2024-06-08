package rus.iglo.storycraft.story.event;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rus.iglo.storycraft.StoryCraftTing;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = StoryCraftTing.MODID)
public class TickVen {
    private static final List<Runnable> tickTasks = new ArrayList<>();

    public TickVen(Runnable runnable) {
        tickTasks.add(runnable);
    }
    public void removeTick() {
        tickTasks.remove(tickTasks);
    }

    @SubscribeEvent
    public static void tick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            for (Runnable task : tickTasks) {
                task.run();
            }
        }
    }
}