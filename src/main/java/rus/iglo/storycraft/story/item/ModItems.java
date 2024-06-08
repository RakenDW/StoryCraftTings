package rus.iglo.storycraft.story.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import rus.iglo.storycraft.StoryCraftTing;
import rus.iglo.storycraft.story.npc.ModEntityTypes;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, StoryCraftTing.MODID);

    public static final RegistryObject<Item> DELETE_NPC = ITEMS.register("delete_npc", () -> new Item(new Item.Properties().tab(Itemes.StoryCraftTing).stacksTo(1)));
    public static final RegistryObject<Item> RESTART_STORY = ITEMS.register("restart_story", () -> new RestartStoryItems(new Item.Properties().tab(Itemes.StoryCraftTing).stacksTo(1)));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
