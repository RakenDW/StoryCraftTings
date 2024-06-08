package rus.iglo.storycraft.story.ap;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.item.Item;

import static rus.iglo.storycraft.StoryCraftTing.playerall;

public class playerallAp {
    public void sendMessage(String name) {
        playerall.sendSystemMessage(Component.literal(name));
    }

    public void sendMessage(String name, String text) {
        playerall.sendSystemMessage(Component.literal(ChatFormatting.GOLD + "[" + name + "] " + ChatFormatting.RESET + text));
    }

    public void sendMessageforplayerall(String name) {
        playerall.sendSystemMessage(Component.literal(ChatFormatting.GOLD + "[" + playerall.getName().getString() + "] " + ChatFormatting.RESET + name));
    }

    public static void sendMessagewhisper(String text) {
        playerall.sendSystemMessage(Component.nullToEmpty(ChatFormatting.GOLD + "[шёпотом] " + ChatFormatting.RESET + text));
    }

    public void teleport(double x, double y, double z) {
        playerall.teleportTo(x, y, z);
    }

    public void delete() {
        playerall.discard();
    }
    public void kill() {
        playerall.kill();
    }

    public void fireplayerall(int timer) {
        playerall.setSecondsOnFire(timer);
    }

    public void bolt(int x, int y, int z) {
        Entity bol = new LightningBolt(EntityType.LIGHTNING_BOLT, playerall.level);
        bol.moveTo(x, y, z);
        bol.level.addFreshEntity(bol);
    }

    static Item item;

    public void drop(Item item) {
        this.item = item;
        playerall.drop(item.getDefaultInstance(), true);
    }
}
