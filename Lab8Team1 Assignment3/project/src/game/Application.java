package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.Actor.Player;
import game.Actor.PrincessPeach;
import game.Actor.Toad;
import game.Enemies.Goomba;
import game.Ground.*;
import game.Item.FireFlower;
import game.Item.PowerStar;
import game.Item.SuperMushroom;


/**
 * The main class for the Mario World game.
 *
 */
public class Application {
	private boolean teleport = false;

	public Application(boolean teleport) {
		this.teleport = teleport;
	}

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Mature(),new Sapling(), new Sprout(), new WarpPipe());
			FancyGroundFactory groundFactory1 = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Mature(),new Sapling(), new Sprout(), new Lava(), new WarpPipe());

		List<String> map = Arrays.asList(
				"..........................................##..........+.........................",
				"............+............+..................#...................................",
				"............................................#...................................",
				".............................................##......................+..........",
				"...............................................#................................",
				"................................................#...............................",
				".................+................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				".........+..............................+#____####.................+............",
				".......................................+#_____###++.............................",
				".......................................+#______###..............................",
				"........................................+#_____###..............................",
				"........................+........................##.............+...............",
				"...................................................#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

		List<String> lavazone = Arrays.asList(
				"C.........................................##..........+.....",
				"............+............+..................#...............",
				"............................................#.........L.....",
				"....................L............L...........##.............",
				".........L.....................................#............",
				"............................L...................#...........",
				".................+..................L.............#.........",
				"........L........................................##.........",
				"................................................##..........",
				".........+....................L.........+#____####..........",
				".......................L...............+#_____###++.........",
				".........L.............................+#______###......L...",
				"........................................+#_____###..........",
				"........................+........................##.........",
				"...............L......................L............#........",
				"....................................................#.......");

			GameMap gameMap = new GameMap(groundFactory, map);
			GameMap gameMap1 = new GameMap(groundFactory1, lavazone);

			world.addGameMap(gameMap);
			world.addGameMap(gameMap1);


			Actor mario = new Player("Player", 'm', 100);

			world.addPlayer(mario, gameMap.at(42, 10));





			// FIXME: the Goomba should be generated from the Tree
			gameMap.at(35, 10).addActor(new Goomba());
			gameMap.at(61,10).addActor( new Goomba());
			gameMap.at(42,11).addActor(new Toad());
			gameMap.at(41,10).addItem(new SuperMushroom());
			gameMap.at(41,10).addItem(new PowerStar());
			gameMap.at(45,11).setGround(new HealthFountain());
			gameMap.at(44,10).setGround(new PowerFountain());
			gameMap.at(11, 10).setGround(new WarpPipe());
			gameMap.at(33, 15).setGround(new WarpPipe());
			gameMap.at(53, 12).setGround(new WarpPipe());




			Exit exit = new Exit("LavaZone", gameMap1.at(0,0),"t");
			gameMap.at(11,10).addExit(exit);
			gameMap.at(33,15).addExit(exit);
			gameMap.at(53,12).addExit(exit);


			Exit exit1 = new Exit("Original World", gameMap.at(11,10),"t");
			gameMap1.at(0,0).addExit(exit1);
			gameMap1.at(7,14).addActor(new PrincessPeach());


			world.run();


	}

}
