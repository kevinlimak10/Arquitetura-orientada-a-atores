package atores;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.cluster.routing.ClusterRouterPool;
import akka.cluster.routing.ClusterRouterPoolSettings;
import akka.routing.RoundRobinPool;
import mensagens.Pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AvistarPokemonActor extends AbstractActor {

    private ActorRef pokedex;


    public AvistarPokemonActor(boolean cluster) {
        this.pokedex = cluster ? criandoBuildLocal() :criandoClusterRoteador();
    }


    private ActorRef criandoBuildLocal() {
        return getContext().actorOf(new RoundRobinPool(2).props(Props.create(PokedexActor.class)), "pokedexActor");
    }

    private ActorRef criandoClusterRoteador() {
        int totalInstances = 2;
        int maxInstancesPerNode = 1;
        boolean allowLocalRoutees = false;
        Set<String> useRoles = new HashSet<>(Arrays.asList("compute"));
        return getContext().actorOf(new ClusterRouterPool(new RoundRobinPool(2),
                        new ClusterRouterPoolSettings(totalInstances, maxInstancesPerNode, allowLocalRoutees, useRoles))
                        .props(Props.create(PokemonActor.class)),
                "pokedexRouter");

    }


    @Override
    public Receive createReceive() {
        return receiveBuilder().
                match(Pokemon.class, pokemon ->
                        chamarPokedex(pokemon)).
                build();
    }

    private void chamarPokedex(Pokemon pokemon) {
        pokedex.tell(pokemon,getSelf());
    }

}