package ar.edu.utn.frc.tup.lciii.model.box.property_box;

import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import ar.edu.utn.frc.tup.lciii.model.deeds.Railway;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import ar.edu.utn.frc.tup.lciii.model.player.HumanPlayer;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class TestRailwayProperty {
    RailwayProperty railwayPropertyBox;

    @Mock
    Railway railway;

    @Mock
    AbstractPlayer player;
    @Mock
    Game game;
    @Mock
    Scanner scanner;
    @Mock
    Optional<AbstractPlayer> owner;

    @BeforeEach
    void initialize() {
        railwayPropertyBox = new RailwayProperty();
        player = mock(HumanPlayer.class);
        railway = mock(Railway.class);
        game = mock(Game.class);
        railwayPropertyBox.setDeed(railway);

    }

    @Test
    void actionBoxPlayerHasTheProperty() {
        when(player.hasProperty(railway)).thenReturn(true);

        railwayPropertyBox.actionBox(player);

        verify(player, times(0)).buyProperty(railway);
    }

    @Test
    void actionBoxNobodyHasTheProperty() {
        when(player.hasProperty(railway)).thenReturn(false);

        when(railway.getName()).thenReturn("FERROCARRIL");
        try (MockedStatic<InputValidator> utilities = Mockito.mockStatic(InputValidator.class)) {
            utilities.when(InputValidator::getYesNoAnswer).thenReturn(true);
            try (MockedStatic<Game> gameMockedStatic = Mockito.mockStatic(Game.class)) {
                gameMockedStatic.when(Game::getInstance).thenReturn(game);
                when(game.somePlayerHasProperty(railway)).thenReturn(Optional.empty());

                railwayPropertyBox.actionBox(player);
                verify(player, times(1)).buyProperty(railway);
            }
        }
    }
    @Test
    void actionBoxSomeoneHasTheProperty() {

        HumanPlayer user = new HumanPlayer();
        List<AbstractDeed> deeds = new ArrayList<>();
        deeds.add(railway);
        user.setDeeds(deeds);
        owner = Optional.of(user);
        when(player.hasProperty(railway)).thenReturn(false);
        try (MockedStatic<Game> gameMockedStatic = Mockito.mockStatic(Game.class)) {
            gameMockedStatic.when(Game::getInstance).thenReturn(game);
            when(game.somePlayerHasProperty(railway)).thenReturn(owner);
            when(railway.getRent(owner.get().getPropertyTypeQuantity(railway))).thenReturn(10000);
            railwayPropertyBox.actionBox(player);
            verify(player, times(1)).pay(10000);
        }

    }
}