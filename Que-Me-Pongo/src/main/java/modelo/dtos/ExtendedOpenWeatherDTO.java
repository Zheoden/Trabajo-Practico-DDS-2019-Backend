package modelo.dtos;

import java.util.List;

import modelo.proveedores.openweather.ExtendedMain;

public class ExtendedOpenWeatherDTO {
	private List<ExtendedMain> list;

	public List<ExtendedMain> getList() {
		return list;
	}

	public void setList(List<ExtendedMain> list) {
		this.list = list;
	}

}
