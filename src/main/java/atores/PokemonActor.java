package atores;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import mensagens.Pokemon;

public class PokemonActor extends AbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef pokedexActor;

    private Pokemon pokemon;

    @Override
    public void preStart() {
        pokedexActor = getContext().actorOf(Props.create(PokedexActor.class,pokemon),"pokedexActor");
    }

    public PokemonActor(Pokemon pokemon){
        this.pokemon = pokemon;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().//
            matchEquals(Pokemon.class, pokemon ->{
                chamaPokedex();
        }).build();
    }

    public void chamaPokedex() throws Exception {
        log.info("Olá eu sou o ");
        pokedexActor.tell(pokemon,getSelf());
    }
}
