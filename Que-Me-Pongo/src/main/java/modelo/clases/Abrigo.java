package modelo.clases;

import java.util.List;

import com.google.common.collect.Range;
import static java.util.Arrays.asList;

public class Abrigo {

	private static Abrigo NIVEL_1 = new Abrigo(Range.atLeast(26.0), asList(1, 2));
	private static Abrigo NIVEL_2 = new Abrigo(Range.open(23.0, 26.0), asList(1, 2, 3));
	private static Abrigo NIVEL_3 = new Abrigo(Range.openClosed(21.0, 23.0), asList(2, 3, 4));
	private static Abrigo NIVEL_4 = new Abrigo(Range.openClosed(19.0, 21.0), asList(3, 4, 5));
	private static Abrigo NIVEL_5 = new Abrigo(Range.openClosed(17.0, 19.0), asList(4, 5, 6));
	private static Abrigo NIVEL_6 = new Abrigo(Range.openClosed(15.0, 17.0), asList(5, 6, 7));
	private static Abrigo NIVEL_7 = new Abrigo(Range.openClosed(13.0, 15.0), asList(6, 7, 8));
	private static Abrigo NIVEL_8 = new Abrigo(Range.openClosed(11.0, 13.0), asList(7, 8, 9));
	private static Abrigo NIVEL_9 = new Abrigo(Range.openClosed(8.0, 11.0), asList(9, 10));
	private static Abrigo NIVEL_10 = new Abrigo(Range.atMost(8.0), asList(10));

	private Range<Double> rangoDeClima;
	private List<Integer> nivelesDeAbrigo;

	private Abrigo(Range<Double> rangoDeClima, List<Integer> nivelesDeAbrigo) {
		this.rangoDeClima = rangoDeClima;
		this.nivelesDeAbrigo = nivelesDeAbrigo;
	}

	private boolean esNivelParaElClima(Double clima) {
		return rangoDeClima.contains(clima);
	}

	public static List<Integer> obtenerNivelesDeAbrigo(Double clima, int sensibilidad) {
		List<Abrigo> abrigos = asList(NIVEL_1, NIVEL_2, NIVEL_3, NIVEL_4, NIVEL_5, NIVEL_6, NIVEL_7, NIVEL_8, NIVEL_9,
				NIVEL_10);
		return abrigos.stream().filter(abrigo -> abrigo.esNivelParaElClima(clima + sensibilidad)).findFirst().get().nivelesDeAbrigo;
	}

}