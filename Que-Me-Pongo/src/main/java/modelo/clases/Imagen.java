package modelo.clases;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imagen {

	private static int MAX_HEIGHT = 800;
	private static int MAX_WIDTH = 600;
	private BufferedImage imagenRenderizada;
	
	public BufferedImage getImagenRenderizada() {
		return imagenRenderizada;
	}

	public void setImagenRenderizada(BufferedImage imagenRenderizada) {
		this.imagenRenderizada = imagenRenderizada;
	}

	//Funcion Para Redimensionar El Tamanio De La Prenda Al Maximo Establecido
	public BufferedImage normalizarImagen(String url) {
		
		 BufferedImage imageBuffer = cargarImagen(url);
		 
		 imageBuffer = redimensionarImagen(imageBuffer, imageBuffer.getWidth(), MAX_HEIGHT);
		 imageBuffer = redimensionarImagen(imageBuffer, MAX_WIDTH, imageBuffer.getHeight());
		 
		 guardarImagen(imageBuffer, url);	
		 this.setImagenRenderizada(imageBuffer);
		 return imageBuffer;
	}
	
	// Funcion Para Cargar La Imagen De Su Url
	public BufferedImage cargarImagen(String url) {
		BufferedImage imageBuffer = null;
		try {
			imageBuffer = ImageIO.read(new File(url));
		} catch (Exception e) {
			System.err.println("No Se Encontro La Imagen A Cargar");
			e.printStackTrace();
		}
		return imageBuffer;
	}
    
	//Funcion Que Redimensiona La Imagen
    public BufferedImage redimensionarImagen(BufferedImage bufferedImage, int nuevoAncho, int nuevaAltura) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        BufferedImage bufim = new BufferedImage(nuevoAncho, nuevaAltura, bufferedImage.getType());
        Graphics2D g = bufim.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bufferedImage, 0, 0, nuevoAncho, nuevaAltura, 0, 0, width, height, null);
        g.dispose();
        return bufim;
    }
    
    //Sobeescribe La Imagen Con El Tamanio Especificado
	public void guardarImagen(BufferedImage bufferedImage, String url) {
		try {
			String format = (url.endsWith(".png")) ? "png" : "jpg";
			File file = new File(url);
			file.getParentFile().mkdirs();
			ImageIO.write(bufferedImage, format, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
