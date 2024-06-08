package rus.iglo.storycraft.story.npc.client;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import rus.iglo.storycraft.StoryCraftTing;
import rus.iglo.storycraft.story.npc.custom.NPCEntity;

public class NPCModel extends AnimatedGeoModel<NPCEntity> {
    @Override
    public ResourceLocation getModelResource(NPCEntity entity) {
        return new ResourceLocation(StoryCraftTing.MODID, "geo/" + entity.getGeo());
    }
    @Override
    public ResourceLocation getTextureResource(NPCEntity entity) {
        return new ResourceLocation(StoryCraftTing.MODID, "textures/entity/" + entity.getTexture());
    }
    @Override
    public ResourceLocation getAnimationResource(NPCEntity entity) {
        return new ResourceLocation(StoryCraftTing.MODID, "animations/" + entity.getAnimAnim());
    }
}