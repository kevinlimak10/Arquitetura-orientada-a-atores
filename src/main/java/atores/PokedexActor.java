package atores;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import mensagens.Pokemon;

public class PokedexActor extends AbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    Pokemon pokemon;

    public PokedexActor(Pokemon pokemon){
        this.pokemon = pokemon;
    }

    @Override
    public Receive createReceive() {
            return receiveBuilder().
                    matchEquals(Pokemon.class, pokemon -> {
                botarDescricaoPokemon();
            }).build();
    }

    private void botarDescricaoPokemon() {
//        log.info(pokemon.toString());
        System.out.println(pokemon);
    }
}
