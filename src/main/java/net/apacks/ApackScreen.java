package net.apacks;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class ApackScreen extends Screen {
    private final Screen parent;
    public final GameOptions settings;
    public ApackScreen(Screen parent, GameOptions gameOptions) {
        super(Text.literal("Apacks Mod"));
        this.parent = parent;
        this.settings = gameOptions;
    }
	public MutableText ToggleText(boolean value,String name) {
		if(value)
			return Text.literal(name+" is enabled");
		else
			return Text.literal(name+" is disabled");
	}
	public int currentButton = 0;
	public void addButton(boolean value,String name, ButtonWidget.PressAction onPress){
			this.addDrawableChild(new ButtonWidget(this.width / 4 - 30, 25*currentButton+30,100,20,ToggleText(value,name),onPress));
			currentButton +=1;
	}
	public void addButton(String name, ButtonWidget.PressAction onPress){
		this.addDrawableChild(new ButtonWidget(this.width / 4 - 30, 25*currentButton+30,100,20,Text.literal(name),onPress));
		currentButton +=1;
	}

	protected void init() {

		//Toggle Fly button
		addButton(Main.flyIsEnabled,"Flight",(button -> {
			Main.flyIsEnabled = !Main.flyIsEnabled;
			client.player.getAbilities().allowFlying=Main.flyIsEnabled;
			client.player.getAbilities().flying=false;
			Main.LOGGER.info("Set flyIsEnabled to "+Main.flyIsEnabled);
			button.setMessage(ToggleText(Main.flyIsEnabled,"Flight"));
		}));

		//Toggle NoFall button
		addButton(Main.noFallIsEnabled,"NoFall",(button -> {
			Main.noFallIsEnabled = !Main.noFallIsEnabled;
			button.setMessage(ToggleText(Main.noFallIsEnabled,"NoFall"));
		}));

		//Fix Position button
		addButton("Fix Position",(button -> FixPosition.Fix()));



		//Back button
		this.addDrawableChild(new ButtonWidget(10, 10,90,20,
			ScreenTexts.BACK,(button ->
				this.client.setScreen(this.parent)
		)));
	}
}
