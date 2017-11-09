package exercise2;

import pmp.filter.Source;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import java.io.FileNotFoundException;
import java.io.StreamCorruptedException;

/**
 * Created by Elisabeth on 30.10.2017.
 */
public class SourcePicture extends Source<PlanarImage>  {

  private String _path;
  //public SourcePicture(String path, SimplePipe<PlanarImage> sp1){
    //_path = path;
  //}

  public SourcePicture(String path, Writeable<PlanarImage> output) throws FileNotFoundException {
    super(output);
    _path = path;
  }
  public SourcePicture(String path){
    _path = path;
  };

  public PlanarImage read() throws StreamCorruptedException {
    PlanarImage image = JAI.create("fileload", _path);
  return image;
  }

  @Override
  public void run() {
    PlanarImage output = null;

    try {
      output = read();
      m_Output.write(output);
    } catch (StreamCorruptedException e) {
      e.printStackTrace();
    }

  }



}
