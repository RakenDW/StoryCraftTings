package rus.iglo.storycraft.story.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import rus.iglo.storycraft.script.script;

public class RestartStoryItems extends Item {
    public RestartStoryItems(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41428_, Player p_41429_, InteractionHand p_41430_) {
        if(!p_41428_.isClientSide() && p_41430_ == InteractionHand.MAIN_HAND) {
            script.y();
        }
        return super.use(p_41428_, p_41429_, p_41430_);
    }
}