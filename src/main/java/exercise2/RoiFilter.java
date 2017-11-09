package exercise2;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.security.InvalidParameterException;

/**
 * Created by Bernd on 06.11.2017.
 */
public class RoiFilter extends DataTransformationFilter2<PlanarImage, PlanarImage>{

    //Grösse des neuen Frames. y-Start ab 50 bis Ende 150
    private Rectangle _rectangle;

    public RoiFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output, Rectangle rectangle) throws InvalidParameterException {
        super( input, output );
        _rectangle = rectangle;
    }

    public RoiFilter(Readable<PlanarImage> input, Rectangle rectangle) throws InvalidParameterException {
        super( input );
        _rectangle = rectangle;
    }

    public RoiFilter(Writeable<PlanarImage> output, Rectangle rectangle) throws InvalidParameterException {
        super( output );
        _rectangle = rectangle;
    }
    //TODO RectangelDaten beim Konstruktoraufruf mitgeben.


    @Override
    protected PlanarImage process(PlanarImage entity) {
        PlanarImage image = entity;
        image = PlanarImage.wrapRenderedImage(image.getAsBufferedImage(_rectangle, image.getColorModel()));
        image.setProperty( "offsetX",(int) _rectangle.getX());
        image.setProperty( "offsetY", (int)_rectangle.getY());
        return image;
    }


}
