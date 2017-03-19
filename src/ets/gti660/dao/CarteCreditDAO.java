package ets.gti660.dao;

import java.util.List;

import javax.sql.DataSource;

import ets.gti660.model.CarteCredit;

public interface CarteCreditDAO {

	public void setDataSource(DataSource ds);

	public CarteCredit getCarteCredit(Integer id);

	public List<CarteCredit> listCarteCredit();

	public void delete(Integer id);

	public List<String> listType();

}
