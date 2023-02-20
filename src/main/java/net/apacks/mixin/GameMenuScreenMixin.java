package net.apacks.mixin;

import net.apacks.ApackScreen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {

    protected GameMenuScreenMixin(Text title) {
        super(title);
    }

    @Inject(at=@At("HEAD"),method="initWidgets")
    private void initWidgets(CallbackInfo ci) {
        assert this.client != null;
        this.addDrawableChild(ButtonWidget.builder(Text.of("ApacksMenu"),(button ->
            this.client.setScreen(new ApackScreen(this,this.client.options)))
        ).dimensions(10,10,90,20).build());
    }
}