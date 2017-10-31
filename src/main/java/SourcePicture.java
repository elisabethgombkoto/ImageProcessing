import pmp.filter.Source;
import pmp.interfaces.Readable;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
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
