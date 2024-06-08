package rus.iglo.storycraft.story.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import rus.iglo.storycraft.StoryCraftTing;

public class ScOverlay {
    public static int overlay = 0;
    static int xx = 1000;
    static int yy = 1000;
    static int xxx = 10000;
    static int yyy = 10000;
    static int zzz = 10000;
    static int spp = 10000;
    static float alpha = 0.0f; // Начальная прозрачность

    private static final ResourceLocation EMPTY_THIRST = new ResourceLocation(StoryCraftTing.MODID,
            "textures/overlay/sc.png");

    public static final IGuiOverlay HUD_THIRST = ((gui, poseStack, partialTick, width, height) -> {
        int x = width / 2; // Фиксированное положение оверлея
        int y = height;
        if (overlay == 1) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            // Изменяем цвет с учетом прозрачности
            RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, alpha); // Устанавливаем черный цвет
            RenderSystem.setShaderTexture(0, EMPTY_THIRST);
            GuiComponent.blit(poseStack, x - xx, y - yy, 0, 0, xxx, yyy, zzz, spp);
            alpha += 0.01f;
        }
    });
public void openOverlay() {
        overlay = 1;
        alpha = 0.0f;
    }

    public void closeOverlay() {
        overlay = 0;
    }
}