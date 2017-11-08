package exercise2;

import pmp.filter.DataTransformationFilter1;
import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.RenderedOp;
import java.security.InvalidParameterException;

/**
 * Created by Bernd on 08.11.2017.
 *  Es speichert einfach das übergeben bekommene planarimage.
 *  Dies passiert in der methode process.
 *  Das planarimage wird nicht verändert,
 *  daher kann das unveränderte weiter geschickt werden
 */
public class ImageToFileFilter extends DataTransformationFilter1<PlanarImage> {

  private String _destinationPath;

  public ImageToFileFilter(String destination, Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
    super(input, output);
    _destinationPath = destination;
  }

  public ImageToFileFilter(String destination, Readable<PlanarImage> input) throws InvalidParameterException {
    super(input);
    _destinationPath = destination;
  }

  public ImageToFileFilter(String destination, Writeable<PlanarImage> output) throws InvalidParameterException {
    super(output);
    _destinationPath = destination;
  }

  @Override
  protected void process(PlanarImage image) {
    RenderedOp op = JAI.create("filestore", image, _destinationPath, "PNG");
  }
}
