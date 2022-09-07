package oracle.takapc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class linkCommand implements CommandExecutor {

    private SQLBase MySQL;
    private SQLGetter data;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        this.MySQL = Main.getMain().getSQL();
        this.data = new SQLGetter(MySQL, player.getName());
        String pw = "";
        if(args.length >= 1) {
            pw = args[0];
            data.createPlayer(pw, player.getUniqueId().toString(), false);
            player.sendMessage("§e"+ args[0]);
        } else {
            player.sendMessage("§cPWを設定してください");
        }
        return true;
    }
}
