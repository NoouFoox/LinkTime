package fun.cyclesn.linktime;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.EnumSet;


public class LinkListener implements Listener {
    private static final EnumSet<Action> RIGHT_CLICK_ACTIONS = EnumSet.of(
            Action.RIGHT_CLICK_AIR,
            Action.RIGHT_CLICK_BLOCK
    );

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();
        Vector speedVector = player.getVelocity();
        if (RIGHT_CLICK_ACTIONS.contains(action) && !event.isBlockInHand() && speedVector.length() != 0) {
            speedVector.setY(0);
            ItemStack handItem = player.getInventory().getItemInMainHand();
            player.sendMessage("You right clicked with " + handItem.getType());
            double dodgeDistance = 5;
            Vector dodgeVector = speedVector.clone().normalize().multiply(dodgeDistance);
            player.setVelocity(dodgeVector);
        }
    }
}
