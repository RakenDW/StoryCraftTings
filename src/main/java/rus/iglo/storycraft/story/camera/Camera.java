package rus.iglo.storycraft.story.camera;

import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rus.iglo.storycraft.StoryCraftTing;

import static rus.iglo.storycraft.StoryCraftTing.*;

@Mod.EventBusSubscriber(modid = StoryCraftTing.MODID)
public class Camera {
    private double originalX;
    private double originalY;
    private double originalZ;
    private double targetX;
    private double targetY;
    private double targetZ;
    private double currentX;
    private double currentY;
    private double currentZ;
    private double currentxx;
    private double currentyy;
    private double currentzz;
    private double getPlayerX;
    private double getPlayery;
    private double getPlayerz;
    private double speed;

    @SubscribeEvent
    public static void tickevent(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (camera.stack == 1) {
                camera.updateCamera();
                world.executecommand("gamemode spectator @a");
            }
        }
    }

    public void setCamera(double startX, double startY, double startZ, double endX, double endY, double endZ,double currentxx,double currentyy,double currentzz,double speed) {
        this.currentxx = currentxx;
        this.currentyy = currentyy;
        this.currentzz = currentzz;
        this.getPlayerX = playerall.getX();
        this.getPlayery = playerall.getY();
        this.getPlayerz = playerall.getZ();
        this.originalX = startX;
        this.originalY = startY;
        this.originalZ = startZ;
        this.targetX = endX;
        this.targetY = endY;
        this.targetZ = endZ;
        this.currentX = startX;
        this.currentY = startY;
        this.currentZ = startZ;
        this.speed = speed;
        this.stack = 1;
    }
    public void setCamera(double startX, double startY, double startZ, double endX, double endY, double endZ,double speed) {
        this.getPlayerX = playerall.getX();
        this.getPlayery = playerall.getY();
        this.getPlayerz = playerall.getZ();
        this.originalX = startX;
        this.originalY = startY;
        this.originalZ = startZ;
        this.targetX = endX;
        this.targetY = endY;
        this.targetZ = endZ;
        this.currentX = startX;
        this.currentY = startY;
        this.currentZ = startZ;
        this.speed = speed;
        this.stack = 1;
    }

    public void updateCamera() {
        if (stack == 1) {
            currentX += (targetX - currentX) * speed;
            currentY += (targetY - currentY) * speed;
            currentZ += (targetZ - currentZ) * speed;

            playerall.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(currentxx, currentyy, currentzz));
            playerall.teleportTo(currentX, currentY, currentZ);

            if (Math.abs(currentX - targetX) < speed && Math.abs(currentY - targetY) < speed && Math.abs(currentZ - targetZ) < speed) {
                stack = 0;
            }
        }
    }

    public void resetCamera() {
        stack = 0;
        world.executecommand("gamemode survival @a");
        playerall.teleportTo(getPlayerX,getPlayery,getPlayerz);
    }

    public static double stack = 0;
}
