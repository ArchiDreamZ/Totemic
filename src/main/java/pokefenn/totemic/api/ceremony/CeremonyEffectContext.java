package pokefenn.totemic.api.ceremony;

import java.util.Optional;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

/**
 * This interface provides details about a Ceremony's state during the ceremony effect phase.
 * Instances of this are passed to {@link CeremonyInstance} methods.
 */
public interface CeremonyEffectContext {
    /**
     * Returns the time in ticks how long the ceremony effect lasted so far.
     * <p>
     * Note that the time is not necessarily synchronized between the server and the client, especially in case of server lag.
     * On the client side, the returned value may be greater than the ceremony's {@linkplain CeremonyInstance#getEffectTime() maximum effect time}.
     */
    int getTime();

    /**
     * If the ceremony was initiated by a player, returns that player. Otherwise, returns an empty Optional.
     * <p>
     * Currently, only players can initiate ceremonies, but this may change in the future.
     */
    Optional<Player> getInitiatingPlayer();

    /**
     * Returns the Entity that initiated the ceremony, if available.
     * Returns an empty Optional if the initiating entity is no longer available (e.g. when
     * the world has been saved and reloaded).
     */
    Optional<Entity> getInitiator();

    /**
     * Ends the ceremony effect and returns the Totem Base to its default state.
     */
    void endCeremony();
}
