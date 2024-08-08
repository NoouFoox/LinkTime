package fun.cyclesn.linktime;

import fun.cyclesn.linktime.fc.MoveFc;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LinkCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        Player player = (Player) sender;
        Location location = player.getLocation();
        Location targetLocation = new Location(location.getWorld(), location.getX()+10, location.getY(), location.getZ()+10);
        MoveFc.movePlayerToLocation(player, targetLocation);
        player.sendMessage("测试消息");
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        List<String> completions = new ArrayList<>();
        if (command.getName().equalsIgnoreCase("mycommand")) {
            if (args.length == 1) {
                completions.add("option1");
                completions.add("option2");
                completions.add("option3");
            } else if (args.length == 2) { // 第二个参数
                completions.add("suboption1");
                completions.add("suboption2");
            }
        }

        return completions;
    }
}
