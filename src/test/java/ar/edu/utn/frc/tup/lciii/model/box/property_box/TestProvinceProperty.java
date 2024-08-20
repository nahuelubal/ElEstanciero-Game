package ar.edu.utn.frc.tup.lciii.model.box.property_box;

import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.deeds.Province;
import ar.edu.utn.frc.tup.lciii.model.deeds.Zone;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import ar.edu.utn.frc.tup.lciii.model.player.ConservativeBotPlayer;
import ar.edu.utn.frc.tup.lciii.model.player.HumanPlayer;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Scanner;

import static org.mockito.Mockito.*;

class TestProvinceProperty {
    ProvinceProperty provinceBox;
    @Mock
    Province province;
    @Mock
    AbstractPlayer player;
    @Mock
    Game game;
    @Mock
    Scanner scanner;
    @Mock
    Optional<AbstractPlayer> owner;
    @Mock
    HumanPlayer humanPlayer;

    @BeforeEach
    void initialize() {
        provinceBox = new ProvinceProperty();
        player = mock(AbstractPlayer.class);
        province = mock(Province.class);
        game = mock(Game.class);
        when(province.getZone()).thenReturn(new Zone(1, ""));
        provinceBox.setDeed(province);
        humanPlayer = mock(HumanPlayer.class);
    }

    @Test
    void actionBoxPlayerIsHumanAndHasTheProperty(){
        when(humanPlayer.hasProperty(province)).thenReturn(true);

        provinceBox.actionBox(humanPlayer);

        verify(humanPlayer, times(0)).buyProperty(province);
    }


    @Test
    void actionBoxPlayerHasTheProperty() {
        when(player.hasProperty(province)).thenReturn(true);

        provinceBox.actionBox(player);

        verify(player, times(0)).buyProperty(province);
    }

    @Test
    void actionBoxPlayerIsHumanAndNobodyHasTheProperty() {
        when(humanPlayer.hasProperty(province)).thenReturn(false);

        when(province.getName()).thenReturn("CORDOBA");
        try (MockedStatic<InputValidator> utilities = Mockito.mockStatic(InputValidator.class)) {
            utilities.when(InputValidator::getYesNoAnswer).thenReturn(true);
            try (MockedStatic<Game> gameMockedStatic = Mockito.mockStatic(Game.class)) {
                gameMockedStatic.when(Game::getInstance).thenReturn(game);
                when(game.somePlayerHasProperty(province)).thenReturn(Optional.empty());

                provinceBox.actionBox(humanPlayer);
                verify(humanPlayer, times(1)).buyProperty(province);
            }
        }
    }

    @Test
    void actionBoxNobodyHasTheProperty() {
        when(player.hasProperty(province)).thenReturn(false);

        when(province.getName()).thenReturn("CORDOBA");
        try (MockedStatic<InputValidator> utilities = Mockito.mockStatic(InputValidator.class)) {
            utilities.when(InputValidator::getYesNoAnswer).thenReturn(true);
            try (MockedStatic<Game> gameMockedStatic = Mockito.mockStatic(Game.class)) {
                gameMockedStatic.when(Game::getInstance).thenReturn(game);
                when(game.somePlayerHasProperty(province)).thenReturn(Optional.empty());

                provinceBox.actionBox(player);
                verify(player, times(1)).buyProperty(province);
            }
        }
    }
    @Test
    void actionBoxSomeoneHasTheProperty() {
        owner = Optional.of(new ConservativeBotPlayer());
        when(player.hasProperty(province)).thenReturn(false);
        try (MockedStatic<Game> gameMockedStatic = Mockito.mockStatic(Game.class)) {
            gameMockedStatic.when(Game::getInstance).thenReturn(game);
            when(province.getZone()).thenReturn(new Zone(1, ""));
            when(game.somePlayerHasProperty(province)).thenReturn(owner);

            when(province.getRent(owner.get().hasEntireProvince(province))).thenReturn(10000);

            provinceBox.actionBox(player);
            verify(player, times(1)).pay(10000);
        }

    }
    @Test
    void actionBoxPlayerIsHumanAndSomeoneHasTheProperty() {
        owner = Optional.of(new ConservativeBotPlayer());
        when(humanPlayer.hasProperty(province)).thenReturn(false);
        try (MockedStatic<Game> gameMockedStatic = Mockito.mockStatic(Game.class)) {
            gameMockedStatic.when(Game::getInstance).thenReturn(game);

            when(game.somePlayerHasProperty(province)).thenReturn(owner);

            when(province.getRent(owner.get().hasEntireProvince(province))).thenReturn(10000);

            provinceBox.actionBox(humanPlayer);
            verify(humanPlayer, times(1)).pay(10000);
        }

    }
}