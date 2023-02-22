package net.apacks;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class ApackScreen extends Screen {
    private final Screen parent;
    public final GameOptions settings;
    public ApackScreen(Screen parent, GameOptions gameOptions) {
        super(Text.literal("Apacks Mod"));
        this.parent = parent;
        this.settings = gameOptions;
    }
	public Text ToggleText(boolean value,String name) {
		if(value)
			return Text.of(name+" is enabled");
		else
			return Text.of(name+" is disabled");
	}
	public int currentButton = 0;
	public void addButton(boolean value,String name, ButtonWidget.PressAction onPress){
			this.addDrawableChild(ButtonWidget.builder(ToggleText(value,name),onPress).dimensions(120, 25*currentButton+30,100,20).build());
			currentButton +=1;
	}
	public void addButton(String name, ButtonWidget.PressAction onPress) {
		this.addDrawableChild(ButtonWidget.builder(Text.of(name), onPress).dimensions(120, 25 * currentButton + 30, 100, 20).build());
		currentButton += 1;
	}
	protected void init() {

		//Toggle LifeOverflow's Server Position bypass
		addButton(Main.loPosBypass,"LvOf Pos Bypass",(button -> {
			Main.loPosBypass = !Main.loPosBypass;
			button.setMessage(ToggleText(Main.noFallIsEnabled,"LvOf Pos Bypass"));
		}));

		//Toggle Fly button
		addButton(Main.flyIsEnabled,"Flight",(button -> {
			Main.flyIsEnabled = !Main.flyIsEnabled;
			assert client != null;
			assert client.player != null;
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
		this.addDrawableChild(ButtonWidget.builder(
				ScreenTexts.BACK, (button ->
				{
					assert this.client != null;
					this.client.setScreen(this.parent);
				}
				)).dimensions(10, 10,90,20).build());
	}
}
