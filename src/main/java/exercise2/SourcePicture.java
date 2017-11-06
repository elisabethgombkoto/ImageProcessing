package exercise2;

import pmp.filter.Source;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import java.io.StreamCorruptedException;

/**
 * Created by Elisabeth on 30.10.2017.
 */
public class SourcePicture extends Source<PlanarImage>  {

  private String _path;
  public SourcePicture(String path){
    _path = path;
  }

  public PlanarImage read() throws StreamCorruptedException {

    PlanarImage image = JAI.create("fileload", _path);
  return image;
  }
}
