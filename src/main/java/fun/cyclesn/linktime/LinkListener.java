package fun.cyclesn.linktime;

import fun.cyclesn.linktime.info.MoveInfo;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LinkListener implements Listener {
    private final Map<UUID, MoveInfo> infoMap = new HashMap<>();
    //    点击防抖
    private final Map<UUID, Long> lastInteractTime = new HashMap<>();
    private final Map<UUID, Long> lastDodgeTime = new HashMap<>();
    private static final long DODGE_COOLDOWN = 1000; //
    @EventHandler
    public void onMove(PlayerMoveEvent moveEvent) {
        Player player = moveEvent.getPlayer();
        Location location = player.getLocation();
        MoveInfo playerInfo = new MoveInfo(location.getX(), location.getY(), location.getZ());
        MoveInfo mapInfo = infoMap.get(player.getUniqueId());
        if ((mapInfo != null) && !playerInfo.equals(mapInfo)) {
            infoMap.put(player.getUniqueId(), playerInfo);
            System.out.println(player.getName());
            System.out.println(
                    mapInfo.getX() + " " + mapInfo.getY() + " " + mapInfo.getZ()
            );
        }
    }

    private static final EnumSet<Action> RIGHT_CLICK_ACTIONS = EnumSet.of(
            Action.RIGHT_CLICK_AIR,
            Action.RIGHT_CLICK_BLOCK
    );

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();
        long currentTime = System.currentTimeMillis();
        UUID playerUUID = player.getUniqueId();
        if (lastInteractTime.containsKey(playerUUID)) {
            long lastTime = lastInteractTime.get(playerUUID);
            if (currentTime - lastTime < 200) {
                return;
            }
        }

        // 更新最后一次交互时间
        lastInteractTime.put(playerUUID, currentTime);
        if (RIGHT_CLICK_ACTIONS.contains(action) && !event.isBlockInHand()) {
            ItemStack handItem = player.getInventory().getItemInMainHand();
            Location location = player.getLocation();
            if (handItem.getType() == Material.DIAMOND_SWORD) {
                // TODO 现在是钻石剑
                player.sendMessage(
                        "我去 是有闪避标签的武器 可以触发闪避"
                );
            }
        }
    }
    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        if (!event.isSneaking()) {
            return; // 仅在玩家按下潜行键时触发
        }

        long currentTime = System.currentTimeMillis();

        // 检查冷却时间，防止连续闪避
        if (lastDodgeTime.containsKey(playerUUID)) {
            long lastTime = lastDodgeTime.get(playerUUID);
            if (currentTime - lastTime < DODGE_COOLDOWN) {
                return;
            }
        }


        lastDodgeTime.put(playerUUID, currentTime);

        // 获取玩家当前的视角方向
        Location loc = player.getLocation();
        Vector direction = loc.getDirection().normalize();

        // 计算闪避的目标位置
        Vector dodgeVector = direction.multiply(3);
        player.setVelocity(dodgeVector);
        player.sendMessage("触发闪避");

        // TODO 添加效果
    }
}
