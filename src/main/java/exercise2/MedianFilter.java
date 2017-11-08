package exercise2;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
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

  protected PlanarImage process(PlanarImage entity) {
    return null;
  }
}
