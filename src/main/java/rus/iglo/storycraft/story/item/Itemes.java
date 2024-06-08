package rus.iglo.storycraft.story.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class Itemes {
    public static final CreativeModeTab StoryCraftTing = new CreativeModeTab("StoryCraftTing") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.RESTART_STORY.get());
        }
    };
}
