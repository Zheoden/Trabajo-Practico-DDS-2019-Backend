package modelo.proveedores.openweather;

import modelo.utils.Utils;

public class Main {
	private Double temp;
	private Double humidity;
	private Double temp_min;
	private Double temp_max;

	public Double getTemp() {
		return Utils.kelvinToCelsius(temp);
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public Double getTemp_min() {
		return Utils.kelvinToCelsius(temp_min);
	}

	public void setTemp_min(Double temp_min) {
		this.temp_min = temp_min;
	}

	public Double getTemp_max() {
		return Utils.kelvinToCelsius(temp_max);
	}

	public void setTemp_max(Double temp_max) {
		this.temp_max = temp_max;
	}
}
