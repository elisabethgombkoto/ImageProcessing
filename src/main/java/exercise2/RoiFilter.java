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
    private Rectangle rectangle = new Rectangle (0, 50, 447, 150);

    public RoiFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
        super( input, output );
    }

    public RoiFilter(Readable<PlanarImage> input) throws InvalidParameterException {
        super( input );
    }

    public RoiFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
        super( output );
    }
    //TODO RectangelDaten beim Konstruktoraufruf mitgeben.


    @Override
    protected PlanarImage process(PlanarImage entity) {
        PlanarImage image = entity;
        image = PlanarImage.wrapRenderedImage((RenderedImage)image.getAsBufferedImage(rectangle, image.getColorModel()));
        image.setProperty( "offsetX", (int)rectangle.getX());
        image.setProperty( "offsetY",(int) rectangle.getY());
        return image;
    }


}
