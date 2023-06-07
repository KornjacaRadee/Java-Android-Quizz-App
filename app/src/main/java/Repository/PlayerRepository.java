package Repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projekat.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import Model.Player;

public class PlayerRepository {
    private DatabaseHelper databaseHelper;

    public PlayerRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void addPlayer(int playerId, int score) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", playerId);
        values.put("score", score);

        db.insert("players", null, values);
        db.close();
    }

    public void updatePlayerScore(int playerId, int newScore) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("score", newScore);

        db.update("players", values, "id=?", new String[]{String.valueOf(playerId)});
        db.close();
    }

    public List<Player> getAllPlayersById(int playerId) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        List<Player> players = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM players WHERE id=?", new String[]{String.valueOf(playerId)});
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") int score = cursor.getInt(cursor.getColumnIndex("score"));

                Player player = new Player(id, score);
                players.add(player);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return players;
    }


}
