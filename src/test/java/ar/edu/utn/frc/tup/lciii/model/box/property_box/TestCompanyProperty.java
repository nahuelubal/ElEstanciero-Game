package ar.edu.utn.frc.tup.lciii.model.box.property_box;

import ar.edu.utn.frc.tup.lciii.model.config.Dice;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import ar.edu.utn.frc.tup.lciii.model.deeds.Company;
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

import static org.mockito.Mockito.*;


class TestCompanyProperty {

    CompanyProperty companyProperty;

    @Mock
    Company company;

    @Mock
    AbstractPlayer player;
    @Mock
    Game game;
    @Mock
    Scanner scanner;
    @Mock
    Optional<AbstractPlayer> owner;
    @Mock
    Dice dice;

    @BeforeEach
    void initialize() {
        companyProperty = new CompanyProperty();
        player = mock(HumanPlayer.class);
        company = mock(Company.class);
        game = mock(Game.class);
        dice = mock(Dice.class);
        companyProperty.setDeed(company);

    }

    @Test
    void actionBoxPlayerHasTheProperty() {
        when(player.hasProperty(company)).thenReturn(true);

        companyProperty.actionBox(player);

        verify(player, times(0)).buyProperty(company);
    }

    @Test
    void actionBoxNobodyHasTheProperty() {
        when(player.hasProperty(company)).thenReturn(false);

        when(company.getName()).thenReturn("COMPANY");
        try (MockedStatic<InputValidator> utilities = Mockito.mockStatic(InputValidator.class)) {
            utilities.when(InputValidator::getYesNoAnswer).thenReturn(true);
            try (MockedStatic<Game> gameMockedStatic = Mockito.mockStatic(Game.class)) {
                gameMockedStatic.when(Game::getInstance).thenReturn(game);
                when(game.somePlayerHasProperty(company)).thenReturn(Optional.empty());

                companyProperty.actionBox(player);
                verify(player, times(1)).buyProperty(company);
            }
        }
    }
    @Test
    void actionBoxSomeoneHasTheProperty() {

        HumanPlayer user = new HumanPlayer();
        List<AbstractDeed> deeds = new ArrayList<>();
        deeds.add(company);
        user.setDeeds(deeds);
        owner = Optional.of(user);
        when(player.hasProperty(company)).thenReturn(false);
        try (MockedStatic<Game> gameMockedStatic = Mockito.mockStatic(Game.class)) {
            gameMockedStatic.when(Game::getInstance).thenReturn(game);
            when(game.somePlayerHasProperty(company)).thenReturn(owner);
            when(game.getDice()).thenReturn(dice);
            when(dice.getAddition()).thenReturn(10);
            int quantity = owner.get().getPropertyTypeQuantity(company);

            when(company.getRent(quantity, 10)).thenReturn(10000);
            companyProperty.actionBox(player);
            verify(player, times(1)).pay(10000);
        }

    }
}