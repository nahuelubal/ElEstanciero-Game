package ar.edu.utn.frc.tup.lciii.model.box;

import ar.edu.utn.frc.tup.lciii.model.config.Dashboard;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GoToJailBoxTest {
	GoToJailBox goToJail;
	AbstractPlayer player;
	JailBox jailBox;
	Dashboard dashboard;

	@BeforeEach
	void setUp() {
		goToJail = new GoToJailBox();
		player = mock(AbstractPlayer.class);
		dashboard = mock(Dashboard.class);
		jailBox = mock(JailBox.class);
	}

	@Test
	void findAll() {
		try (MockedStatic<Dashboard> mockedDashboard = mockStatic(Dashboard.class)) {
			mockedDashboard.when(Dashboard::getInstance).thenReturn(dashboard);
			when(dashboard.findBoxByType(JailBox.class)).thenReturn(jailBox);

			goToJail.actionBox(player);

			verify(dashboard, times(1)).findBoxByType(JailBox.class);
			verify(dashboard, times(1)).movePlayerByBox(player, jailBox.getBoxNumber());
		}
	}

	@Test
	void notFindBoxType() {
		try (MockedStatic<Dashboard> mockedDashboard = mockStatic(Dashboard.class)) {
			mockedDashboard.when(Dashboard::getInstance).thenReturn(dashboard);
			when(dashboard.findBoxByType(JailBox.class)).thenReturn(null);

			goToJail.actionBox(player);

			verify(dashboard, times(1)).findBoxByType(JailBox.class);
			verify(dashboard, never()).movePlayerByBox(player, jailBox.getBoxNumber());
		}
	}

	@Test
	void dashboardNotInstanced() {
		try (MockedStatic<Dashboard> mockedDashboard = mockStatic(Dashboard.class)) {
			mockedDashboard.when(Dashboard::getInstance).thenReturn(null);

			goToJail.actionBox(player);

			verify(dashboard, never()).findBoxByType(JailBox.class);
			verify(dashboard, never()).movePlayerByBox(player, jailBox.getBoxNumber());
		}
	}
}