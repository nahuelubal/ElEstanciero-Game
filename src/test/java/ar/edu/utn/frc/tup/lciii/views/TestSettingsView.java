package ar.edu.utn.frc.tup.lciii.views;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestSettingsView {
	@Mock
	private GameRulesView gameRulesView;

	@Mock
	private ChangePasswordView changePasswordView;

	private SettingsView settingsView;

	@Mock
	Scanner scanner;

	@Test
	void testRender1() {
		gameRulesView = mock(GameRulesView.class);
		scanner = mock(Scanner.class);

		settingsView = new SettingsView();
		settingsView.setScanner(scanner);
		settingsView.setGameRulesView(gameRulesView);

		when(scanner.nextLine()).thenReturn("1");

		doAnswer((Answer<Void>) invocation -> {
			System.out.println("Performing action!");
			when(scanner.nextLine()).thenReturn("3");
			return null;
		}).when(gameRulesView).render();

		settingsView.render();

		verify(gameRulesView, times(1)).render();
	}

	@Test
	void testRender2() {
		changePasswordView = mock(ChangePasswordView.class);
		scanner = mock(Scanner.class);

		settingsView = new SettingsView();
		settingsView.setScanner(scanner);
		settingsView.setChangePasswordView(changePasswordView);

		when(scanner.nextLine()).thenReturn("2");

		doAnswer((Answer<Void>) invocation -> {
			System.out.println("Performing action!");
			when(scanner.nextLine()).thenReturn("3");
			return null;
		}).when(changePasswordView).render();

		settingsView.render();

		verify(changePasswordView, times(1)).render();
	}
}