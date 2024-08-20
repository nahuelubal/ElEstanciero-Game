package ar.edu.utn.frc.tup.lciii.model.player;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.interfaces.player.IPlayer;
import ar.edu.utn.frc.tup.lciii.model.config.Color;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.config.GameStyle;
import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import ar.edu.utn.frc.tup.lciii.model.deeds.Province;
import ar.edu.utn.frc.tup.lciii.repository.DeedRepository;
import ar.edu.utn.frc.tup.lciii.services.DeedService;
import ar.edu.utn.frc.tup.lciii.services.impl.DeedServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public abstract class AbstractPlayer implements IPlayer {
	public final int MAX_FARM_QUANTITY = 4;

	//Atributes
	protected int idPlayer;
	protected GameStyle gameStyle;
	protected Color color;
	protected Integer playerOrder;
	protected int moneyAvailable;
	protected List<AbstractDeed> deeds;
	protected Boolean isActive;
	protected int idBox;
	protected boolean isPrisoner;
	protected int roundsStayQuantity;
	protected boolean hasHabeasCorpus;

	public AbstractPlayer() {
		deeds = new ArrayList<>();
	}

	//Methods
	public Boolean hasEnoughMoney(Integer amount) {
		return moneyAvailable >= amount;
	}

	public void addRoundsStayQuantity() {
		roundsStayQuantity++;
	}

	public boolean hasProperty(AbstractDeed deed) {
		for (AbstractDeed abstractDeed : deeds) {
			if (Objects.equals(deed.getName(), abstractDeed.getName())) {
				return true;
			}
		}
		return false;
	}

	public int calculateRanchValue(Province province) {
		int farmsProv = province.getFarmQuantity();
		int farmValue = province.getConstructionValue();
		int totalValue = farmValue;

		totalValue += farmValue * (MAX_FARM_QUANTITY - farmsProv);

		return totalValue;
	}

	public boolean hasEntireProvince(Province province) {
		DeedService deedService = DeedServiceImpl.getInstance(new DeedRepository(), new ModelMapper());

		List<AbstractDeed> gameDeeds = deedService.getDeedList();

		Integer gameDeedQuantity = 0;
		Integer userDeedQuantity = 0;
		for (AbstractDeed deed : gameDeeds) {
			if (deed instanceof Province provinceFromDeed) {
				if (provinceFromDeed.getZone().getIdZone() == province.getZone().getIdZone()) {
					gameDeedQuantity++;
				}
			}
		}
		for (AbstractDeed deed : deeds) {
			if (deed instanceof Province provinceFromDeed) {
				if (provinceFromDeed.getZone().getIdZone() == province.getZone().getIdZone()) {
					userDeedQuantity++;
				}
			}
		}

		return gameDeedQuantity.equals(userDeedQuantity);
	}

	public int getPropertyTypeQuantity(AbstractDeed deed) {
		int q = 0;

		for (AbstractDeed d : deeds) {
			if (d.getClass().equals(deed.getClass())) {
				q++;
			}
		}

		return q;
	}


	@Override
	public void receive(int money) {
		moneyAvailable += money;
	}

	@Override
	public void pay(int money) {
		if (hasEnoughMoney(money)) {
			this.setMoneyAvailable(getMoneyAvailable() - money);
		} else {
			if (deeds.isEmpty()) {
				setBankrupt();
			} else {
				while (!hasEnoughMoney(money)) {
					ConsolePrinter.println("Insufficient money!");
					choosePropertyToMortgage();
				}
				this.setMoneyAvailable(getMoneyAvailable() - money);
			}
		}
	}

	@Override
	public void setBankrupt() {
		for (AbstractDeed d : deeds) {
			d.setIsMortgaged(false);
			d.setIsPurchased(false);
			if (d instanceof Province) {
				((Province) d).setHasRanch(false);
				((Province) d).setFarmQuantity(0);
			}
		}
		deeds.clear();
		Game.getInstance().removePlayer(this);
	}

}