package codes.revqz.photogram.platform;

import net.kyori.adventure.text.Component;

import java.util.Optional;

public interface ServerPlatform {

    Optional<Component> motd();
}
