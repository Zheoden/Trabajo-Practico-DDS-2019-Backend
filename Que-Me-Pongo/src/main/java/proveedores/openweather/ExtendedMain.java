package proveedores.openweather;

public class ExtendedMain {
	private Main main;
	private String dt_txt;
	
	public Main getMain() {
		return main;
	}
	
	public void setMain(Main main) {
		this.main = main;
	}

	public String getDt_txt() {
		return dt_txt;
	}
	
	public void setDt_txt(String dt_txt) {
		this.dt_txt = dt_txt;
	}
	
	public Double getTemp() {
		return this.main.getTemp();
	}
}
