package rus.iglo.storycraft.story.event;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rus.iglo.storycraft.StoryCraftTing;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = StoryCraftTing.MODID)
public class ClickEventBlock {
    public static List<Runnable> test = new ArrayList<>();
    public static int blockss = 0;
    public static Block blocks;
    @SubscribeEvent
    public static void block(PlayerInteractEvent.RightClickBlock event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            if (event.getLevel().getBlockState(event.getPos()).getBlock() == blocks) {
                if (blockss == 1) {
                    for (Runnable runnable : test) {
                        runnable.run();
                        test.clear();
                    }
                }
                blockss = 0;
            }
        }
    }
    public static synchronized void setBlock(Runnable runnable,Block block) {
        ClickEventBlock.blocks = block;
        blockss = 1;
        synchronized (test) {
            test.add(runnable);
        }
    }
}
