package rus.iglo.storycraft;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import org.lwjgl.glfw.GLFW;
import rus.iglo.storycraft.story.action.Actions;
import rus.iglo.storycraft.story.ap.WorldAp;
import rus.iglo.storycraft.story.ap.playerallAp;
import rus.iglo.storycraft.story.camera.Camera;
import rus.iglo.storycraft.story.event.TickVen;
import rus.iglo.storycraft.story.dialog.DialogScreen;
import rus.iglo.storycraft.story.item.Itemes;
import rus.iglo.storycraft.story.item.ModItems;
import rus.iglo.storycraft.story.npc.ModEntityTypes;
import rus.iglo.storycraft.story.npcc.NPCEntityEntity;
import rus.iglo.storycraft.story.overlay.ScOverlay;
import software.bernie.geckolib3.GeckoLib;

import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BiConsumer;

@Mod(StoryCraftTing.MODID)
public class StoryCraftTing {

    public static final String MODID = "storycraftting";

    public static Player playerall = Minecraft.getInstance().player;

    public static Actions actions = new Actions();

    public static Camera camera = new Camera();

    public static playerallAp player = new playerallAp();

    public static WorldAp world = new WorldAp();

    public static Itemes items = new Itemes();

    public static ScOverlay overlay = new ScOverlay();

    public StoryCraftTing() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModEntityTypes.register(modEventBus);

        ModItems.register(modEventBus);

        DialogScreen.DialogMenu.REGISTRY.register(modEventBus);

        GeckoLib.initialize();

        MinecraftForge.EVENT_BUS.register(this);
    }
    private static final String SSSS = "1";
    public static final SimpleChannel SIMPLE_CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> SSSS, SSSS::equals, SSSS::equals);
    private static int ID = 0;

    public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
        SIMPLE_CHANNEL.registerMessage(ID, messageType, encoder, decoder, messageConsumer);
        ID++;
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public class TestrttModScreens {
        @SubscribeEvent
        public static void clientLoad(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                MenuScreens.register(DialogScreen.DialogMenu.DIALOG.get(), DialogScreen::new);
            });
        }
    }
    @Mod.EventBusSubscriber(modid = StoryCraftTing.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(DRINKING_KEY);
        }
    }
    public static final String KEY_CATEGORY_TUTORIAL = "StoryCraftTing";
    public static final String KEY_DRINK_WATER = "Продолжить сюжет";

    public static final KeyMapping DRINKING_KEY = new KeyMapping(KEY_DRINK_WATER, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_B, KEY_CATEGORY_TUTORIAL);
}