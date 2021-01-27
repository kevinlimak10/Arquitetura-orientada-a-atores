package atores;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import mensagens.Pokemon;

public class PokedexActor extends AbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef pokedexActor;
    private Pokemon pokemon;

    public PokedexActor(Pokemon pokemon){
        this.pokemon = pokemon;
    }


    @Override
    public void preStart() {
        pokedexActor = getContext().actorOf(Props.create(PokemonActor.class,pokemon),"pokemonActor");
    }

    @Override
    public Receive createReceive() {
            return receiveBuilder().
                    match(Pokemon.class, pokemon -> botarDescricaoPokemon()).build();
    }

    private void botarDescricaoPokemon() {
        log.info("Quem é esse pokemon?");
        pokedexActor.tell(pokemon,getSelf());
    }
}
