package exercise2;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.operator.MedianFilterShape;
import java.awt.image.renderable.ParameterBlock;
import java.security.InvalidParameterException;

/**
 * Created by Bernd on 08.11.2017.
 */
public class MedianFilter extends DataTransformationFilter2<PlanarImage, PlanarImage> {

    private MedianFilterShape _medianFilterShape;
    private int _size;

    public MedianFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output, MedianFilterShape medianFilterShape, int size) throws InvalidParameterException {
        super( input, output );
        _medianFilterShape = medianFilterShape;
        _size = size;
    }

    public MedianFilter(Readable<PlanarImage> input, MedianFilterShape medianFilterShape, int size) throws InvalidParameterException {
        super( input );
        _medianFilterShape = medianFilterShape;
        _size = size;

    }

    public MedianFilter(Writeable<PlanarImage> output, MedianFilterShape medianFilterShape, int size) throws InvalidParameterException {
        super( output );
        _medianFilterShape = medianFilterShape;
        _size = size;

    }

    protected PlanarImage process(PlanarImage entity) {
        final ParameterBlock pb = new ParameterBlock();
        pb.addSource( entity );
        pb.add( _medianFilterShape );
        pb.add( _size );
        return JAI.create( "medianfilter", pb );
    }
}
