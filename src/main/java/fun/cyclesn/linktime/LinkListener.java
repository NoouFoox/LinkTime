package fun.cyclesn.linktime;

import fun.cyclesn.linktime.fc.MoveFc;
import fun.cyclesn.linktime.info.MoveInfo;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LinkListener implements Listener {
    private final Map<UUID, MoveInfo> infoMap = new HashMap<>();
    private final Map<UUID,Long> playerMoveTimestamps = new HashMap<>();

    @EventHandler
    public void onMove(PlayerMoveEvent moveEvent) {
        Player player = moveEvent.getPlayer();
        Location location = player.getLocation();
        MoveInfo playerInfo = new MoveInfo(location.getX(), location.getY(), location.getZ());
        MoveInfo mapInfo = infoMap.get(player.getUniqueId());
        if(!playerInfo.equals(mapInfo)){
            infoMap.put(player.getUniqueId(), playerInfo);
            System.out.println(player.getName());
            System.out.println(
                    mapInfo.getX() + " " + mapInfo.getY() + " " + mapInfo.getZ()
            );
        }
    }
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        Location targetLocation = new Location(location.getWorld(), location.getX()+3, location.getY(), location.getZ()+3);
        MoveFc.movePlayerToLocation(player, targetLocation);
        player.sendMessage("测试消息");
    }
}
