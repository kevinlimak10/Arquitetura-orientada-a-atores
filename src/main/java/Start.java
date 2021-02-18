import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import atores.PokedexActor;
import atores.PokemonActor;
import mensagens.Pokemon;

public class Start {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("SistemaPrincipal");

        Pokemon pokeVenusaur = new Pokemon(32,"Venusaur","mato",null);
        Pokemon pokeIvysaur = new Pokemon(16,"Ivysaur","mato",pokeVenusaur);

        Pokemon pokeBulba = new Pokemon(1,"bulbasaur","mato",pokeIvysaur);
        Pokemon pokePidgeot = new Pokemon(20,"Pidgeot","vento",null);

        ActorRef pokedex = system.actorOf(Props.create(PokedexActor.class),"PokedexActor");

        pokedex.tell(pokePidgeot,ActorRef.noSender());
        pokedex.tell(pokeBulba,ActorRef.noSender());
    }
}
