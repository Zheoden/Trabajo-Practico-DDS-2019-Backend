package modelo.dtos;

import proveedores.openweather.Main;

public class OpenWeatherDTO {
	private Main main;

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public Double getTemp() {
		return this.main.getTemp();
	}

}
