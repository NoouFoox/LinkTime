package fun.cyclesn.linktime.fc;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class MoveFc {
    public static void movePlayerToLocation(Player player, Location targetLocation) {
        Location playerLocation = player.getLocation();
        Vector direction = targetLocation.toVector().subtract(playerLocation.toVector());
//        direction.normalize();
        double speed = 1;
        Vector velocity = direction.multiply(speed);
        player.setVelocity(velocity);
    }
}
