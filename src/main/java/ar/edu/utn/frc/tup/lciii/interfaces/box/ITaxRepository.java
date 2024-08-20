package ar.edu.utn.frc.tup.lciii.interfaces.box;

import ar.edu.utn.frc.tup.lciii.entities.box.TaxEntity;

public interface ITaxRepository {
	TaxEntity getTaxEntityByBoxId(int taxId);
}
