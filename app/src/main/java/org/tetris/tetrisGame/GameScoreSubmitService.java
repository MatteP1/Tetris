package org.tetris.tetrisGame;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import com.google.gson.Gson;

import org.tetris.Framework.Game;
import org.tetris.Framework.GameObserver;

public class GameScoreSubmitService implements GameObserver{

	private Game game;
	private String name;
	private HttpClient client;
	private HttpRequest.Builder builder;

	public GameScoreSubmitService(Game game, String name) {
		this.game = game;
		this.name = name;
		client = HttpClient.newHttpClient();
		builder = HttpRequest.newBuilder().uri(URI.create("http://192.168.49.2:32481/api/Game")).header("Content-Type", "application/json");
	}

	@Override
	public void playFieldChangedAt(List<GridElement> grids) {
		if (game.hasLost()) {
			GameScore gamescore = new GameScore(name, game.getScore());
			Gson gson = new Gson();
			String body = gson.toJson(gamescore);
			HttpRequest request = builder.copy().POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8)).build();
			try {
				client.send(request, HttpResponse.BodyHandlers.discarding());
			} catch (Exception e) {
				System.out.println("Couldn't submit score :(");
			}
		}
	}
	
}
