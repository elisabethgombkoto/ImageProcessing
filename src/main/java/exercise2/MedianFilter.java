package exercise2;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.operator.MedianFilterDescriptor;
import javax.media.jai.operator.MedianFilterShape;
import java.awt.image.renderable.ParameterBlock;
import java.security.InvalidParameterException;

/**
 * Created by Bernd on 08.11.2017.
 */
public class MedianFilter extends DataTransformationFilter2<PlanarImage,PlanarImage> {

  public MedianFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
    super(input, output);
  }

  public MedianFilter(Readable<PlanarImage> input) throws InvalidParameterException {
    super(input);
  }

  public MedianFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
    super(output);
  }
  //google ist mein beste freund
  //https://www.programcreek.com/java-api-examples/index.php?api=javax.media.jai.operator.MedianFilterDescriptor
  protected PlanarImage process(PlanarImage entity) {
    final int size = 3;
    final MedianFilterShape shape = MedianFilterDescriptor.MEDIAN_MASK_SQUARE;
    final ParameterBlock pb = new ParameterBlock();
    pb.addSource(entity);
    pb.add(shape);
    pb.add(size);
    return JAI.create("medianfilter", pb);
  }
}
