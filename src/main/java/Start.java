import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import atores.PokemonActor;
import mensagens.Pokemon;

public class Start {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("SistemaPrincipal");

        Pokemon poke = new Pokemon(1,"Bulbasauro","mato");
        ActorRef pokemon = system.actorOf(Props.create(PokemonActor.class,poke),"PokemonActor");

        pokemon.tell(poke,ActorRef.noSender());
    }
}
