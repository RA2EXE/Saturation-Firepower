package SFire.content;

import arc.Core;
import arc.assets.AssetDescriptor;
import arc.assets.loaders.SoundLoader;
import arc.audio.Sound;
import arc.util.Log;
import mindustry.Vars;

import java.lang.reflect.Field;

public class SFSounds {
        public static Sound
                flying, hugeExplosion, missileX, lancer
                ;

        public static void load() {
            try {
                for (Field field : SFSounds.class.getFields()) {
                    if (field.getType().equals(Sound.class)) {
                        field.set(null, loadSound(field.getName()));
                    }
                }
            } catch (IllegalAccessException e) {
                Log.err(e);
            }
        }

        private static Sound loadSound(String soundName) {
            if (Vars.headless) {
                return new Sound();
            }

            String path = "sounds/" + soundName;
            String filePath = Vars.tree.get(path + ".ogg").exists() ? path + ".ogg" : path + ".mp3";

            Sound sound = new Sound();
            AssetDescriptor<?> desc = Core.assets.load(filePath, Sound.class, new SoundLoader.SoundParameter(sound));
            desc.errored = Throwable::printStackTrace;
            return sound;
        }
}