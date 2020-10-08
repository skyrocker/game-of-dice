package com.game.dice;

import com.game.dice.model.Player;
import com.game.dice.model.PlayerService;
import com.game.dice.model.PlayerServiceImpl;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(Arrays.asList(args).toString());
        Scanner scan = new Scanner(System.in);
        int N = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);
        PlayerService service = new PlayerServiceImpl();
        LinkedList<Player> winnerList = new LinkedList<>();
        LinkedList<Player> playerQueue = new LinkedList<>();
        PriorityQueue<Player> scoresBoard = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.getScore(), o1.getScore()));
        for (int i = 0; i<N; i++) {
            Player player = new Player(i+1, M, service);
            playerQueue.add(player);
            scoresBoard.add(player);
        }
        Collections.shuffle(playerQueue);
        System.out.println("Shuffled play order");
        System.out.println(String.join(",", playerQueue.toString()));
        while (!playerQueue.isEmpty()) {
            Player curPlayer = playerQueue.peekFirst();

            int state = -1;
            while (!curPlayer.isSkipNext()) {
                System.out.println(curPlayer+" it's your turn (enter 'r' to roll the dice)");
                String cmd = scan.nextLine().trim();
                if (cmd.equals("r")) {
                    state = curPlayer.rollDice();
                    if (state > 0) {
                        System.out.println("Ranking");
                        for (Player player : winnerList) System.out.println(player+" ("+player.getScore()+")");
                        scoresBoard.remove(curPlayer);
                        scoresBoard.add(curPlayer);
                        for (Player player : scoresBoard) System.out.println(player+" ("+player.getScore()+")");
                    }
                    if (state != 6) break;
                } else System.out.println("Invalid command!");

            }
            playerQueue.pop();
            if (state == 0) {
                winnerList.add(curPlayer);
                scoresBoard.remove(curPlayer);
                System.out.println("Ranking");
                for (Player player : winnerList) System.out.println(player+" ("+player.getScore()+")");
                for (Player player : scoresBoard) System.out.println(player+" ("+player.getScore()+")");
            } else playerQueue.addLast(curPlayer);
        }

    }
}
