package fun.cyclesn.linktime;

import fun.cyclesn.linktime.info.MoveInfo;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LinkListener implements Listener {
    private final Map<UUID, MoveInfo> infoMap = new HashMap<>();

    @EventHandler
    public void onMove(PlayerMoveEvent moveEvent) {
        Player player = moveEvent.getPlayer();
        Location location = player.getLocation();
        MoveInfo moveInfo = new MoveInfo(location.getX(), location.getY(), location.getZ());
        infoMap.put(player.getUniqueId(), moveInfo);
        System.out.println(player.getName());
        MoveInfo info = infoMap.get(player.getUniqueId());
        System.out.println(
                info.getX() + " " + info.getY() + " " + info.getZ()
        );
    }
}
