package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;

import java.util.Queue;

public interface DashboardService {
    void createInitialDashboard();

    void setPlayersIntoStartBox(Queue<AbstractPlayer> players);

    void setPlayersIntoTheirBoxes(Queue<AbstractPlayer> players);
}