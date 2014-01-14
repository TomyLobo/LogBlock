package de.diddiz.LogBlock;

import de.diddiz.LogBlock.QueryParams.SummarizationMode;
import de.diddiz.LogBlock.html.Parser;
import org.bukkit.Location;

import java.sql.ResultSet;
import java.sql.SQLException;

import static de.diddiz.util.MaterialName.materialName;
import static de.diddiz.util.Utils.spaces;

public class SummedBlockChanges extends AbstractLookupCacheElement {
    private final String group;
    private final int created, destroyed;
    private final float spaceFactor;
    private final Actor actor;
    private final boolean isPlayers;

    public SummedBlockChanges(ResultSet rs, QueryParams p, float spaceFactor) throws SQLException {
        // Actor currently useless here as we don't yet output UUID in results anywhere
        actor = p.sum == SummarizationMode.PLAYERS ? new Actor(rs) : null;
        isPlayers = p.sum == SummarizationMode.PLAYERS;
        group = actor == null ? materialName(rs.getInt("type")) : actor.getName();
        created = rs.getInt("created");
        destroyed = rs.getInt("destroyed");
        this.spaceFactor = spaceFactor;
    }

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public String getMessage() {
        return created + spaces((int) ((10 - String.valueOf(created).length()) / spaceFactor)) + destroyed + spaces((int) ((10 - String.valueOf(destroyed).length()) / spaceFactor)) + group;
    }

    @Override
    public String getXmlMessage() {
        String message = created + spaces((int) ((10 - String.valueOf(created).length()) / spaceFactor)) + destroyed + spaces((int) ((10 - String.valueOf(destroyed).length()) / spaceFactor));

        final String command = String.format("/lb last %s %s sum none coords", isPlayers ? "player" : "block", group.replace(' ', '_'));
        message = String.format("%1$s<span onClick=\"run_command('%2$s')\" onHover=\"show_text('Click to run\n&lt;color name=&quot;blue&quot;>%2$s&lt;/color>')\">%3$s</span>", message, command, group);
        if (created < destroyed) {
            message = String.format("<color name=\"red\">%1$s</color>", message);
        }

        return message;
    }
}
