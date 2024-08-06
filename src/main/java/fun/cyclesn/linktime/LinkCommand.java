package fun.cyclesn.linktime;

import fun.cyclesn.linktime.fc.MoveFc;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LinkCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        Player player = (Player) sender;
        Location location = player.getLocation();
        Location targetLocation = new Location(location.getWorld(), location.getX()+10, location.getY(), location.getZ()+10);
        MoveFc.movePlayerToLocation(player, targetLocation);
        player.sendMessage("测试消息");
        return false;
    }
}
