package rus.iglo.storycraft.script;

import rus.iglo.storycraft.story.gui.GuiMeneg;

import static rus.iglo.storycraft.StoryCraftTing.*;
import static rus.iglo.storycraft.StoryCraftTing.overlay;
import static rus.iglo.storycraft.story.overlay.ScOverlayManager.ScOverlay.*;

public class script {
    public static void y() {
        overlay.openOverlay();
        actions.addSceneKey(() -> {
            overlay.closeOverlay();
        });
    }
}
