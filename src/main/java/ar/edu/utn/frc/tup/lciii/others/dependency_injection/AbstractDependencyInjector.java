package ar.edu.utn.frc.tup.lciii.others.dependency_injection;

import ar.edu.utn.frc.tup.lciii.interfaces.box.IBoxRepository;
import ar.edu.utn.frc.tup.lciii.interfaces.box.ITaxRepository;
import ar.edu.utn.frc.tup.lciii.interfaces.card.ICardRepository;
import ar.edu.utn.frc.tup.lciii.interfaces.deeds.IDeedRepository;
import ar.edu.utn.frc.tup.lciii.model.config.Dice;
import ar.edu.utn.frc.tup.lciii.repository.BoxRepository;
import ar.edu.utn.frc.tup.lciii.repository.CardRepository;
import ar.edu.utn.frc.tup.lciii.repository.DeedRepository;
import ar.edu.utn.frc.tup.lciii.repository.TaxRepository;
import ar.edu.utn.frc.tup.lciii.repository.game.GameRepository;
import ar.edu.utn.frc.tup.lciii.repository.player.PlayerRepository;
import ar.edu.utn.frc.tup.lciii.repository.user.UserRepository;
import ar.edu.utn.frc.tup.lciii.services.*;
import ar.edu.utn.frc.tup.lciii.services.impl.*;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Scanner;

@Data
public class AbstractDependencyInjector {
	// Scanner
	private Scanner scanner;
	private Dice dice;

	// Repositories
	private UserRepository userRepository;
	private PlayerRepository playerRepository;
	private IBoxRepository boxRepository;
	private ICardRepository cardRepository;
	private GameRepository gameRepository;
	private IDeedRepository deedRepository;
	private ITaxRepository taxRepository;

	// Services
	private PlayerService playerService;
	private GameService gameService;
	private BoxService boxService;
	private CardService cardService;
	private DashboardService dashboardService;
	private DeedService deedService;
	private TaxService taxService;

	// Mapper
	private ModelMapper modelMapper;

	public AbstractDependencyInjector() {
		// Initialize Repositories
		userRepository = UserRepository.getInstance();
		playerRepository = PlayerRepository.getInstance();
		gameRepository = GameRepository.getInstance();
		boxRepository = BoxRepository.getInstance();
		cardRepository = CardRepository.getInstance();
		deedRepository = DeedRepository.getInstance();
		taxRepository = TaxRepository.getInstance();

		// Initialize Mapper
		modelMapper = new ModelMapper();

		// Initialize Scanner
		scanner = new Scanner(System.in);

		// Initialize Dice
		dice = new Dice();

		// Initialize Services
		deedService = DeedServiceImpl.getInstance(deedRepository, modelMapper);
		playerService = PlayerServiceImpl.getInstance(playerRepository, deedService, modelMapper);
		taxService = TaxServiceImpl.getInstance(taxRepository, modelMapper);
		boxService = BoxServiceImpl.getInstance(boxRepository, taxService, deedService, modelMapper);
		gameService = GameServiceImpl.getInstance(gameRepository, playerService, deedService, boxService, modelMapper);
		cardService = CardServiceImpl.getInstance(cardRepository, modelMapper);
		dashboardService = DashboardServiceImpl.getInstance(boxService, cardService);
	}
}
