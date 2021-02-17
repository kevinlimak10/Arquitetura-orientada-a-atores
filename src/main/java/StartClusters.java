

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import atores.PokedexActor;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import mensagens.Pokemon;

public class StartClusters {

    private static final boolean DEVE_USAR_CLUSTER_LOCAL = true;

        public static void main(String[] args) throws InterruptedException {
            if (args.length == 0) {
                startPokedex(args);
            } else {
                startup(args);
            }
        }

    public static void startPokedex(String[] ports) throws InterruptedException {
        ActorSystem system = ActorSystem.create("ClusterSystem", ConfigFactory.load("statusUm"));
        ActorRef pokedex = system.actorOf(Props.create(PokedexActor.class, DEVE_USAR_CLUSTER_LOCAL), "pokedex");
        Pokemon pokeBulba = new Pokemon(10,"Bulbasauro","mato",null);
        Pokemon pokePiggeoto = new Pokemon(20,"Piggeoto","vento",null);
        pokedex.tell(pokePiggeoto,ActorRef.noSender());
        pokedex.tell(pokeBulba,ActorRef.noSender());
    }


    public static void startup(String[] ports) {
        for (String port : ports) {
            Config config = ConfigFactory
                    .parseString(
                            "akka.remote.netty.tcp.port=" + port + "\n" + "akka.remote.artery.canonical.port=" + port)
                    .withFallback(ConfigFactory.parseString("akka.cluster.roles = [compute]"))
                    .withFallback(ConfigFactory.load("statusUm"));

            ActorSystem.create("ClusterSystem", config);
        }
    }
}
