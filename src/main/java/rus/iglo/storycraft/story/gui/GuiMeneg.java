package rus.iglo.storycraft.story.gui;

import rus.iglo.storycraft.story.dialog.DialogScreen;
import rus.iglo.storycraft.story.npcc.NPCEntityEntity;

import static rus.iglo.storycraft.story.dialog.DialogScreen.entityss;

public class GuiMeneg {
    public GuiMeneg(String n) {
        DialogScreen.setNameVopros(n);
    }
    public void open() {
        DialogScreen.OpenDialog.execute();
    }
    public void addButton1(String name,Runnable runnable) {
        DialogScreen.addButtonOtvet1(name,() -> {
            runnable.run();
        });
    }
    public void addButton2(String name,Runnable runnable) {
        DialogScreen.addButtonOtvet2(name,() -> {
            runnable.run();
        });
    }
    public void addButton3(String name,Runnable runnable) {
        DialogScreen.addButtonOtvet3(name,() -> {
            runnable.run();
        });
    }
    public void addButton4(String name,Runnable runnable) {
        DialogScreen.addButtonOtvet4(name,() -> {
            runnable.run();
        });
    }
    public void reset() {
        DialogScreen.reset();
    }
    public void setNameVopros(String name) {
        DialogScreen.setNameVopros(name);
    }
    public void setEntity(NPCEntityEntity entity) {
        entityss = entity;
    }
}