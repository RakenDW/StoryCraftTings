package rus.iglo.storycraft.story;

import com.mojang.math.Vector3d;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EventSun {

    @SubscribeEvent
    public void onRenderPlayer(RenderPlayerEvent event) {
        if (!event.getEntity().level.isDay() && event.getEntity().level.getRawBrightness(new BlockPos(0,-60,0),0) > 8) {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("привет"));
        }
    }
}