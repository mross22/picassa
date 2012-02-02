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
    
    
}
