import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import atores.PokedexActor;
import atores.PokemonActor;
import mensagens.Pokemon;

public class Start {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("SistemaPrincipal");

        Pokemon poke = new Pokemon(1,"Bulbasauro","mato");
        ActorRef pokedex = system.actorOf(Props.create(PokedexActor.class,poke),"PokedexActor");

        pokedex.tell(poke,ActorRef.noSender());
    }
}
