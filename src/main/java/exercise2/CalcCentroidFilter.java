package exercise2;

import calcCentroids.CalcCentroidsFilter;
import calcCentroids.Coordinate;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * Created by Bernd on 08.11.2017.
 */
public class CalcCentroidFilter extends CalcCentroidsFilter {

  public CalcCentroidFilter(Readable<PlanarImage> input) throws InvalidParameterException {
    super(input);
  }

  public CalcCentroidFilter(Writeable<ArrayList<Coordinate>> output) throws InvalidParameterException {
    super(output);
  }
}
