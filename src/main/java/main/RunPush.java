package main;

import CalcCentroids.CalcCentroidsFilter;
import exercise2.*;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;

import javax.media.jai.PlanarImage;
import java.io.IOException;
import java.io.StreamCorruptedException;

/**
 * Created by Elisabeth on 30.10.2017.
 */
public class RunPush {
  public static void main(String[] args) throws StreamCorruptedException {
    try {

      PictureSink pictureSink = new PictureSink();
      SimplePipe <PlanarImage> sp13 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) pictureSink );
      ImageToFileFilter imageToFileFilter = new ImageToFileFilter ((Writeable<PlanarImage>) sp13, "Picture.jpg");
      SimplePipe <PlanarImage> sp12 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) imageToFileFilter );

      ShowImageFilter showImageFilter6 = new ShowImageFilter((Writeable<PlanarImage>) sp12 );
      SimplePipe <PlanarImage> sp11 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) showImageFilter6 );
      CalcCentroidFilter calcCentroidFilter = new CalcCentroidFilter ((Writeable<PlanarImage>) sp11);
      SimplePipe <PlanarImage> sp10 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) calcCentroidFilter );

      ShowImageFilter showImageFilter5 = new ShowImageFilter((Writeable<PlanarImage>) sp10 );
      SimplePipe <PlanarImage> sp9 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) showImageFilter5 );
      OpeningFilter openingFilter = new OpeningFilter ((Writeable<PlanarImage>) sp9);
      SimplePipe <PlanarImage> sp8 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) openingFilter );

      ShowImageFilter showImageFilter4 = new ShowImageFilter((Writeable<PlanarImage>) sp8 );
      SimplePipe <PlanarImage> sp7 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) showImageFilter4 );
      MedianFilter medianFilter = new MedianFilter ((Writeable<PlanarImage>) sp7);
      SimplePipe <PlanarImage> sp6 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) medianFilter );

      ShowImageFilter showImageFilter3 = new ShowImageFilter((Writeable<PlanarImage>) sp6 );
      SimplePipe <PlanarImage> sp5 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) showImageFilter3 );
      TresholdFilter tresholdFilter = new TresholdFilter (Writeable<PlanarImage> sp5);
      SimplePipe <PlanarImage> sp4 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) tresholdFilter );

      ShowImageFilter showImageFilter2 = new ShowImageFilter((Writeable<PlanarImage>) sp4 );
      SimplePipe <PlanarImage> sp3 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) showImageFilter2 );
      RoiFilter roiFilter = new RoiFilter( (Writeable<PlanarImage>) sp3);
      SimplePipe <PlanarImage> sp2 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) roiFilter );

      ShowImageFilter showImageFilter1 = new ShowImageFilter((Writeable<PlanarImage>) sp2 );
      SimplePipe <PlanarImage> sp1 = new SimplePipe <PlanarImage>( (Writeable<PlanarImage>) showImageFilter1);
      SourcePicture source = new SourcePicture( "loetstellen.jpg" );

      source.run();
    }
    catch (IOException e) {
    e.printStackTrace();
  }

  }



}
