package atores;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.cluster.routing.ClusterRouterPool;
import akka.cluster.routing.ClusterRouterPoolSettings;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.RoundRobinPool;
import mensagens.Pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PokedexActor extends AbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public Receive createReceive() {
            return receiveBuilder().
                    match(Pokemon.class, pokemon ->
                            botarDescricaoPokemon(pokemon)).
                    matchAny(o ->
                            log.info("Acabou a listagem")).
                    build();
    }

    private void botarDescricaoPokemon(Pokemon pokemon) {
        log(": Quem é esse pokemon?" + " e quem me chamou foi: " + getSender().path());
        ActorRef pokemonActor = getContext().actorOf(Props.create(PokemonActor.class),pokemon.getNome());
        pokemonActor.tell(pokemon,getSelf());
    }

    @Override
    public void preStart() throws Exception {
        super.preStart();
        log("Starting Pokemon: " + getSelf().path().toString());
    }

    private void log(String log) {
        System.out.println(getSelf().path() + " -> " + log);
    }
}
