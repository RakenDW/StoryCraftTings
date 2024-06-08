package rus.iglo.storycraft.story.event;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rus.iglo.storycraft.StoryCraftTing;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = StoryCraftTing.MODID)
public class BreakEvent {
    public static List<Runnable> test = new ArrayList<>();
    public static int block = 0;
    public static Item item;

    @SubscribeEvent
    public static synchronized void block(BlockEvent.BreakEvent event) {
        if (event.getPlayer() instanceof ServerPlayer player) {
            if(player.getMainHandItem().getItem() == item) {
                if (block == 1) {
                    synchronized (test) {
                        for (Runnable runnable : test) {
                            runnable.run();
                            test.clear();
                        }
                    }
                    block = 0;
                }
            }
        }
    }

    public static void setBlock(Runnable runnable,Item item) {
        BreakEvent.item = item;
        block = 1;
        synchronized (test) {
            test.add(runnable);
        }
    }
}
