package model.util;

import model.RGBColor;


/**
 * Combine two colors by combining their components.
 * 
 * This is a separate class from color since it is just one set of
 * ways to combine colors, many may exist and we do not want to keep
 * modifying the RGBColor class.
 * 
 * @author Robert C. Duvall
 */
public class ColorCombinations
{
	public static final double COLOR_MIN = -1;
    public static final double COLOR_MAX = 1;
    /**
     * Combine two colors by adding their components.
     */
    public static RGBColor add (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() + right.getRed(), 
                            left.getGreen() + right.getGreen(),
                            left.getBlue() + right.getBlue());
    }

    /**
     * Combine two colors by subtracting their components.
     */
    public static RGBColor subtract (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() - right.getRed(), 
                            left.getGreen() - right.getGreen(),
                            left.getBlue() - right.getBlue());
    }

    /**
     * Combine two colors by multiplying their components.
     */
    public static RGBColor multiply (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() * right.getRed(), 
                            left.getGreen() * right.getGreen(),
                            left.getBlue() * right.getBlue());
    }

    /**
     * Combine two colors by dividing their components.
     */
    public static RGBColor divide (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() / right.getRed(), 
                            left.getGreen() / right.getGreen(),
                            left.getBlue() / right.getBlue());
    }
    
    /**
     * Combine two colors by modulus their components.
     */
    public static RGBColor modulus (RGBColor left, RGBColor right)
    {
    	return  new RGBColor(left.getRed() % right.getRed(), 
                left.getGreen() % right.getGreen(),
                left.getBlue() % right.getBlue());
}
    	
    
    /**
     * Combine two colors by exponent their components.
     */
    public static RGBColor exponent (RGBColor left, RGBColor right)
    {
        return new RGBColor(Math.pow(left.getRed(), right.getRed()), 
                            Math.pow(left.getGreen() , right.getGreen()),
                            Math.pow(left.getBlue() , right.getBlue()));
    }
    
    /**
     * Combine two colors by negating their components.
     */
    public static RGBColor negate (RGBColor color)
    {
        return new RGBColor(-color.getRed(), 
                            -color.getGreen(),
                            -color.getBlue());
    }
    
    public static RGBColor color (RGBColor left, RGBColor center, RGBColor right)
    {
        return new RGBColor(left.getRed(), 
                            center.getGreen(),
                            right.getBlue());
    }
    
    public static RGBColor random(){
		double rand = Math.random()*2 -1; //scale random number so it is between -1 and 1
		return new RGBColor(rand);
    }
    
    
    public static RGBColor floor(RGBColor center){

    	return new RGBColor (Math.floor(center.getRed()),
    						 Math.floor(center.getGreen()),
    						 Math.floor(center.getBlue()));
    }
    
    public static RGBColor ceil(RGBColor center){

    	return new RGBColor (Math.ceil(center.getRed()),
    						 Math.ceil(center.getGreen()),
    						 Math.ceil(center.getBlue()));
    }
    
    public static RGBColor abs(RGBColor center){

    	return new RGBColor (Math.abs(center.getRed()),
    						 Math.abs(center.getGreen()),
    						 Math.abs(center.getBlue()));
    }
    
    public static RGBColor clamp(RGBColor center){
    		center.clamp();
    		return center;
    }
    
    public static RGBColor wrap(RGBColor center){
    	center.wrap();
    	return center;
    }
    

    public static RGBColor sin(RGBColor center){

    	return new RGBColor (Math.sin(center.getRed()),
    						 Math.sin(center.getGreen()),
    						 Math.sin(center.getBlue()));
    }
    
    
    public static RGBColor cos(RGBColor center){

    	return new RGBColor (Math.cos(center.getRed()),
    						 Math.cos(center.getGreen()),
    						 Math.cos(center.getBlue()));
    }
    
    public static RGBColor tan(RGBColor center){

    	return new RGBColor (Math.tan(center.getRed()),
    						 Math.tan(center.getGreen()),
    						 Math.tan(center.getBlue()));
    }
    
    public static RGBColor atan(RGBColor center){

    	return new RGBColor (Math.atan(center.getRed()),
    						 Math.atan(center.getGreen()),
    						 Math.atan(center.getBlue()));
    }
    
    public static RGBColor log(RGBColor center){

    	return new RGBColor (Math.log(center.getRed()),
    						 Math.log(center.getGreen()),
    						 Math.log(center.getBlue()));
    }
    
    public static RGBColor rgbToYCrCb(RGBColor center){

    	return ColorModel.rgb2ycrcb(center);
    }
    
    public static RGBColor yCrCbtoRGB (RGBColor center){

    	return ColorModel.ycrcb2rgb(center);
    }
    
    public static RGBColor perlinColor (RGBColor left, RGBColor right){

    	return PerlinNoise.colorNoise(left, right);
    }
    
    public static RGBColor perlinBW (RGBColor left, RGBColor right){

    	return PerlinNoise.greyNoise(left, right);
    }
  
}
