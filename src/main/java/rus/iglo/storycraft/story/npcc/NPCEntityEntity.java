package rus.iglo.storycraft.story.npcc;

import net.minecraft.ChatFormatting;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rus.iglo.storycraft.StoryCraftTing;
import rus.iglo.storycraft.script.script;
import rus.iglo.storycraft.story.event.TickVen;
import rus.iglo.storycraft.story.item.ModItems;
import rus.iglo.storycraft.story.npc.ModEntityTypes;
import rus.iglo.storycraft.story.npc.custom.NPCEntity;

import java.util.ArrayList;
import java.util.List;

import static rus.iglo.storycraft.StoryCraftTing.playerall;

@Mod.EventBusSubscriber(modid = StoryCraftTing.MODID)
public class NPCEntityEntity {
    static NPCEntity npcEntity;
    static String name = "";

    public void setNpcEntity(int x,int y,int z, String name, String texures) {
        npcEntity = new NPCEntity(ModEntityTypes.NPC.get(), playerall.level);
        npcEntity.moveTo(x,y,z);
        playerall.level.addFreshEntity(npcEntity);
        NPCEntityEntity.name = name;
        npcEntity.addTag(name);
        npcEntity.setCustomName(Component.nullToEmpty(name));
        npcEntity.setTexture(texures);
        try {
            if (name == "") {
                playerall.sendSystemMessage(Component.nullToEmpty(ChatFormatting.RED + "[ОШИБКА] ИМЯ '' НЕЛЬЗЯ"));
                npcEntity.discard();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void throwItem(ItemStack itemStack) {
        Vec3 direction = npcEntity.getLookAngle();
        Vec3 startPos = npcEntity.position().add(0, npcEntity.getBbHeight() / 2, 0);
        npcEntity.level.addFreshEntity(new net.minecraft.world.entity.item.ItemEntity(npcEntity.level, startPos.x, startPos.y, startPos.z, itemStack));
    }
    public void setItemInHand(ItemStack itemStack) {
        npcEntity.setItemSlot(EquipmentSlot.MAINHAND, itemStack);
    }

    public void playAnim(String anim) {
        npcEntity.setPlayAnim(anim);
        try {
            if (anim == "") {
                StoryCraftTing.player.sendMessage("ОШИБКА", "НЕЛЬЗЯ ВОСПРОИЗВЕСТИ ПУСТУЮ АНИМАЦИЮ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    static int sm = 0;
    @SubscribeEvent
    public static void tick(TickEvent.ServerTickEvent serverTickEvent) {
        if (serverTickEvent.phase == TickEvent.Phase.END) {
            if (sm == 1) {
                npcEntity.goalSelector.addGoal(1, new LookAtPlayerGoal(npcEntity, Player.class, 20));
            }
        }
    }
    public void smoff() {
        sm = 0;
    }
    public void smon() {
        sm = 1;
    }
    public void setBlockPosYes(int x, int y, int z) {
        TickVen tickVen = new TickVen(() -> {
            npcEntity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(x, y, z));
        });
    }

    public void setName(String name) {
        npcEntity.setCustomName(Component.nullToEmpty(name));
    }

    public void stopAnim() {
        npcEntity.setPlayAnim("animation.npc.idle");
    }

    public void setGeo(String geo) {
        npcEntity.setGeo(geo);
    }

    public void setAnim(String anim) {
        npcEntity.setAnim(anim);
    }

    public double setX() {
        return npcEntity.getX();
    }
    public double setY() {
        return npcEntity.getY();
    }
    public double setZ() {
        return npcEntity.getZ();
    }

    public void setAnimIdle(String animIdle) {
        npcEntity.setAnimIdle(animIdle);
    }

    public void setAnimWalk(String animWalk) {
        npcEntity.setAnimWalk(animWalk);
    }

    public void kill() {
        npcEntity.kill();
    }

    public void delete() {
        npcEntity.discard();
    }

    public void teleport(int x, int y, int z) {
        npcEntity.teleportTo(x, y, z);
    }

    public void moveTo(int x, int y, int z) {
        npcEntity.moveTo(x, y, z);
    }

    public void sendMessage(String text) {
        playerall.sendSystemMessage(Component.nullToEmpty(ChatFormatting.RED + "[" + name + "] " + ChatFormatting.RESET + text));
        try {
            if (text == "") {
                playerall.sendSystemMessage(Component.nullToEmpty(ChatFormatting.RED + "[ОШИБКА] ТЕКСТ НЕ МОЖЕТ ХРАНИТЬ '' "));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    static Item items;
    public void drop(Item items) {
        this.items = items;
        npcEntity.spawnAtLocation(new ItemStack(items), 1);
    }

    private static double x;
    private static double y;
    private static double z;
    private static double speed;
    public void moveToPosition(double x, double y, double z, double speed) {
        NPCEntityEntity.x = x;
        NPCEntityEntity.y = y;
        NPCEntityEntity.z = z;
        NPCEntityEntity.speed = speed;
        TickVen tickVen = new TickVen(() -> {
            npcEntity.getNavigation().moveTo(x, y, z, speed);
        });
        try {
            if (speed == 0) {
                playerall.sendSystemMessage(Component.nullToEmpty(ChatFormatting.RED + "[ОШИБКА] СКОРОСТЬ НЕ МОЖЕТ БЫТЬ 0"));
                npcEntity.discard();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void moveToPositionforplayerall(double speed) {
        NPCEntityEntity.speed = speed;
        TickVen tickVen = new TickVen(() -> {
            npcEntity.getNavigation().moveTo(playerall.getX(), playerall.getY(), playerall.getZ(), speed);
        });
        try {
            if (speed == 0) {
                playerall.sendSystemMessage(Component.nullToEmpty(ChatFormatting.RED + "[ОШИБКА] СКОРОСТЬ НЕ МОЖЕТ БЫТЬ 0"));
                npcEntity.discard();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Mod.EventBusSubscriber(modid = StoryCraftTing.MODID)
    public class EventStory {
        @SubscribeEvent
        public static void register(RegisterCommandsEvent event) {
            event.getDispatcher().register(Commands.literal("storycraftting")
                    .then(Commands.literal("play").executes(arguments -> {
                        System.out.println("");
                        script.y();
                        System.out.println("");
                        return 0;
                    })));
            event.getDispatcher().register(Commands.literal("storycraftting")
                    .then(Commands.literal("restart").executes(arguments -> {
                        System.out.println("");
                        npcEntity.discard();
                        System.out.println("");
                        script.y();
                        System.out.println("");
                        return 0;
                    })));
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(PlayerInteractEvent.EntityInteractSpecific event) {
        if (event.getEntity() instanceof Player) {
            if (event.getTarget() instanceof Mob) {
                if (event.getTarget() instanceof NPCEntity npcEntity) {
                    if (event.getItemStack().getItem() == ModItems.DELETE_NPC.get()) {
                        npcEntity.discard();
                    }
                }
            }
        }
    }

    public static List<Runnable> runnablestclic = new ArrayList<>();
    public static int clickentity = 0;

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteractSpecific event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            if (event.getTarget() == npcEntity) {
                if (clickentity == 1) {
                    for (Runnable runnable : runnablestclic) {
                        runnable.run();
                        runnablestclic.clear();
                    }
                    clickentity = 0;
                }
            }
        }
    }

    public void clicEntity(Runnable runnable) {
        clickentity = 1;
        runnablestclic.add(runnable);
    }

    public static List<Runnable> dealnpc = new ArrayList<>();
    public static int dealsnpc = 0;

    @SubscribeEvent
    public static void killdelaentityV(AttackEntityEvent event) {
        if (event.getTarget() == npcEntity) {
            if (dealsnpc == 1) {
                synchronized (dealnpc) {
                    for (Runnable runnable : dealnpc) {
                        runnable.run();
                        dealnpc.clear();
                    }
                }
            }
            dealsnpc = 0;
        }
    }
    public void dealNpc(Runnable runnable) {
        dealsnpc = 1;
        synchronized (dealnpc) {
            dealnpc.add(runnable);
        }
    }
}