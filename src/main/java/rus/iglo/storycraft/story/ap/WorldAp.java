package rus.iglo.storycraft.story.ap;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;

import static rus.iglo.storycraft.StoryCraftTing.playerall;


public class WorldAp {
    public void onfly() {
        playerall.getAbilities().mayfly = true;
        playerall.onUpdateAbilities();
    }
    public void oddfly() {
        playerall.getAbilities().mayfly = false;
        playerall.onUpdateAbilities();
    }
    public void setBlock(BlockPos blockPos, Block block) {
        playerall.level.setBlock(blockPos,block.defaultBlockState(),3);
    }
    public void executecommand(String command) {
        playerall.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, playerall.position(), playerall.getRotationVector(), playerall.level instanceof ServerLevel ? (ServerLevel) playerall.level : null, 4, playerall.getName().getString(), playerall.getDisplayName(), playerall.level.getServer(), playerall), command);
    }
}
