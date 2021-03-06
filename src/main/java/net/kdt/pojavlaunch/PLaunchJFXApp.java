package net.kdt.pojavlaunch;

import java.io.*;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import net.kdt.pojavlaunch.utils.*;
import net.kdt.pojavlaunch.value.*;

import org.lwjgl.glfw.CallbackBridge;

public class PLaunchJFXApp extends Application {
    public void start(Stage stage) {
        Rectangle2D bounds = Screen.getPrimary().getBounds();
        CallbackBridge.windowWidth = (int) bounds.getWidth();
        CallbackBridge.windowHeight = (int) bounds.getHeight();
        
        JREUtils.saveGLContext();
    
/*
        Circle circ = new Circle(40, 40, 30);
        Group root = new Group(circ);
        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("PojavLauncher");
        stage.setScene(scene);
        stage.show();
*/

        // Start Minecraft there!
        File file = new File(Tools.DIR_GAME_NEW);
        file.mkdirs();
        
        String mcver = "1.13";
        try {
            mcver = Tools.read(Tools.DIR_GAME_HOME + "/config_ver.txt");
        } catch (IOException e) {
            System.out.println("config_ver.txt not found, defaulting to Minecraft 1.13");
        }
        
        MinecraftAccount acc = new MinecraftAccount();
        acc.selectedVersion = mcver;
        JMinecraftVersionList.Version version = Tools.getVersionInfo(mcver);
        
        try {
            Tools.launchMinecraft(acc, version);
        } catch (Throwable th) {
            Tools.showError(th);
        }
        
/*
        net.minecraft.client.main.Main.main(new String[]{
            "--username", "test",
            "--version", "1.7.10",
            "--gameDir", file.getAbsolutePath(),
            "--assetsDir", file.getAbsolutePath() + "/assets",
            "--assetIndex", "1.7.10",
            "--uuid", "0", 
            "--accessToken", "0",
            "--userProperties", "{}",
            "--userType", "mojang",
            "--versionType", "release"
        });
*/
    }
}
