package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.model.box.TaxBox;

public interface TaxService {
	TaxBox getTaxByBoxId(int boxId);
}
