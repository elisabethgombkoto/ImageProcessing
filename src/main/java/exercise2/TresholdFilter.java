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


    public TresholdFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
        super( input, output );
    }

    public TresholdFilter(Readable<PlanarImage> input) throws InvalidParameterException {
        super( input );
    }

    public TresholdFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
        super( output );
    }
//http://www.javased.com/index.php?api=javax.media.jai.JAI
    protected PlanarImage process(PlanarImage entity) {
        double[] low, high, map;
        low=new double[1];
        high=new double[1];
        map=new double[1];
        low[0]=0;
        high[0]=35;
        map[0]=255;
        ParameterBlock pb=new ParameterBlock();
        pb.addSource(entity);
        pb.add(low);
        pb.add(high);
        pb.add(map);
        RenderedOp dest= JAI.create("threshold",pb);
        return dest;
    }
}