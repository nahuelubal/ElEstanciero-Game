package ar.edu.utn.frc.tup.lciii.model.config;

import ar.edu.utn.frc.tup.lciii.model.deeds.Province;
import ar.edu.utn.frc.tup.lciii.model.player.BotPlayerTypes;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class GameStyle {
    //Atributes
    private Integer idStyleGame;
    private BotPlayerTypes styleGame;
    private Boolean railwayPurchase;
    private Boolean companyPurchase;
    private Integer purchasePossibility;
    private List<Province> provincesToPrioritize;

    //Constructor
    public GameStyle(Integer idStyleGame, BotPlayerTypes styleGame, Boolean railwayPurchase, Boolean companyPurchase, Integer purchasePossibility, List<Province> provincesToPrioritize) {
        this.idStyleGame = idStyleGame;
        this.styleGame = styleGame;
        this.railwayPurchase = railwayPurchase;
        this.companyPurchase = companyPurchase;
        this.purchasePossibility = purchasePossibility;
        this.provincesToPrioritize = provincesToPrioritize;
    }
}
