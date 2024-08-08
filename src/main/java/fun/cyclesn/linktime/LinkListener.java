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
//            TODO 不能速度向量 还是要手动实现
            double dodgeDistance = 3;
            System.out.println(speedVector.clone().normalize());
            Vector dodgeVector = speedVector.clone().normalize().multiply(dodgeDistance);
            if (Double.isFinite(dodgeVector.getX()) &&
                    Double.isFinite(dodgeVector.getY()) &&
                    Double.isFinite(dodgeVector.getZ())) {
                player.setVelocity(dodgeVector);
            }
        }
    }
}
