package Service;

import java.util.List;

import Model.Player;
import Repository.PlayerRepository;

public class PlayerService {
    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void addPlayer(int playerId, int score) {
        playerRepository.addPlayer(playerId, score);
    }

    public void updatePlayerScore(int playerId, int newScore) {
        playerRepository.updatePlayerScore(playerId, newScore);
    }



    public List<Player> getAllPlayersById(int playerId) {
        return playerRepository.getAllPlayersById(playerId);
    }
}

