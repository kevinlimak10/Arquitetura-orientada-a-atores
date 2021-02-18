package atores;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.cluster.Cluster;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import mensagens.Pokemon;

public class PokemonActor extends AbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public Receive createReceive() {

        return receiveBuilder().//
        match(
                Pokemon.class,
                pokemon -> {
                    dizerQuemSou(pokemon);
                    if(pokemon.getEvolucao() != null) getSender().tell(pokemon.getEvolucao(),getSelf());
                }
        )
                    .matchAny(o -> log.info("received unknown message"))//
        .build();
    }

    public void dizerQuemSou(Pokemon pokemon) {
        try {
            Thread.sleep(pokemon.getNivel() * 1000);
        } catch (InterruptedException e) {
            log.info("Não consegui dormir");
        }
        System.out.println(getSelf().path() +":" +pokemon.toString());
    }
}
