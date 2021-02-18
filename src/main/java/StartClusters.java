import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import atores.AvistarPokemonActor;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import mensagens.Pokemon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StartClusters {

    private static final boolean DEVE_USAR_CLUSTER_LOCAL = true;

        public static void main(String[] args) throws InterruptedException {
            if (args.length == 0) {
                startup(25251);
            } else {
                Arrays.stream(args).map(Integer::parseInt).forEach(StartClusters::startup);
            }
        }

    public static void startPokedex(ActorSystem system)  {
        ActorRef pokedex =  system.actorOf(Props.create(AvistarPokemonActor.class, DEVE_USAR_CLUSTER_LOCAL),"pokedex");
        esperarSubirOsClusters();
        simularChamadas(pokedex);
    }

    private static void simularChamadas(ActorRef pokedex) {

        Pokemon pokeVenusaur = new Pokemon(32,"Venusaur","mato",null);
        Pokemon pokeIvysaur = new Pokemon(16,"Ivysaur","mato",pokeVenusaur);
        Pokemon pokeBulba = new Pokemon(1,"bulbasaur","mato",pokeIvysaur);

        Pokemon pokePidgeotto = new Pokemon(36,"Pidgeotto","vento",null);
        Pokemon pokePidgeot = new Pokemon(1,"Pidgeot","vento",pokePidgeotto);

        Pokemon pokeCharmeleon  = new Pokemon(16,"Charmeleon","fogo",null);
        Pokemon pokeCharmander  = new Pokemon(1,"Charmander","fogo",pokeCharmeleon);


        pokedex.tell(pokePidgeot,ActorRef.noSender());
        pokedex.tell(pokeBulba,ActorRef.noSender());
        pokedex.tell(pokeCharmander,ActorRef.noSender());
    }

    private static void esperarSubirOsClusters() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void startup(int port) {
        Map<String, Object> overrides = new HashMap<>();
        overrides.put("akka.remote.artery.canonical.port", port);

        Config config = ConfigFactory
                    .parseMap(overrides)
                    .withFallback(ConfigFactory.parseString("akka.cluster.roles = [compute]"))
                    .withFallback(ConfigFactory.load("statusUm"));

        ActorSystem system = ActorSystem.create("ClusterSystem",config);
        startPokedex(system);
    }
}

