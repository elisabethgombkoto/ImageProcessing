package exercise2;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.RenderedOp;
import javax.media.jai.operator.ThresholdDescriptor;
import java.awt.image.renderable.ParameterBlock;
import java.security.InvalidParameterException;

/**
 * Created by Bernd on 08.11.2017.
 * google is mein beste freund
 * http://www.javased.com/index.php?api=javax.media.jai.JAI
 */
public class TresholdFilter extends DataTransformationFilter2<PlanarImage,PlanarImage> {
    private double _low;
    private double _high;
    private double _map;


    public TresholdFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output, double low, double high, double map) throws InvalidParameterException {
        super( input, output );
        _low = low;
        _high = high;
        _map = map;
    }

    public TresholdFilter(Readable<PlanarImage> input, double low, double high, double map) throws InvalidParameterException {
        super( input );
        _low = low;
        _high = high;
        _map = map;
    }

    public TresholdFilter(Writeable<PlanarImage> output, double low, double high, double map) throws InvalidParameterException {
        super( output );
        _low = low;
        _high = high;
        _map = map;
    }
//http://www.javased.com/index.php?api=javax.media.jai.JAI
    protected PlanarImage process(PlanarImage entity) {
        double[] lowArray = {_low};
        double[] highArray = {_high};
        double[] mapArray = {_map};


        ParameterBlock pb=new ParameterBlock();
        pb.addSource(entity);
        pb.add(lowArray);
        pb.add(highArray);
        pb.add(mapArray);
        PlanarImage dest= JAI.create("threshold",pb);
        return dest;
    }
}