package main;

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

    PictureSink pictureSink = new PictureSink();
    SimplePipe <PlanarImage> sp13 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) pictureSink );
    //TODO absolute path auf relative path ändern
    ImageToFileFilter imageToFileFilter = new ImageToFileFilter("C:\\Users\\Elisabeth\\IdeaProjects\\ImageProcessing\\src\\main\\resources\\picture.jpg",(Writeable<PlanarImage>) sp13);
    SimplePipe <PlanarImage> sp12 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) imageToFileFilter );

    //TODO ich check es noch nicht, also wir machen erst den rest fertig ok?
    //Katja erklärt mir es morgen, dann kann ich diese auch koorekt in main einfügen

    //ShowImageFilter showImageFilter6 = new ShowImageFilter((Writeable<PlanarImage>) sp12 );
    //SimplePipe <PlanarImage> sp11 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) showImageFilter6 );
    // calcCentroidFilter = new CalcCentroidFilter ((Writeable<PlanarImage>) sp11);
    // SimplePipe <PlanarImage> sp10 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) calcCentroidFilter );

    ShowImageFilter showImageFilter5 = new ShowImageFilter((Writeable<PlanarImage>) sp12 );
    SimplePipe <PlanarImage> sp9 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) showImageFilter5 );
    //TODO OpeningFilter konstruktor sollte die parameter von Außen bekommen
    OpeningFilter openingFilter = new OpeningFilter ((Writeable<PlanarImage>) sp9);
    SimplePipe <PlanarImage> sp8 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) openingFilter );

    ShowImageFilter showImageFilter4 = new ShowImageFilter((Writeable<PlanarImage>) sp8 );
    SimplePipe <PlanarImage> sp7 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) showImageFilter4 );
    //TODO MedianFilterDescriptor.MEDIAN_MASK_SQUARE, 5, (Writeable<PlanarImage>) new SimplePipe<PlanarImage>
    //TODO oder maskSize: 5 sollte die parameter für die konstruktor sein???
    MedianFilter medianFilter = new MedianFilter ((Writeable<PlanarImage>) sp7);
    SimplePipe <PlanarImage> sp6 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) medianFilter );

    ShowImageFilter showImageFilter3 = new ShowImageFilter((Writeable<PlanarImage>) sp6 );
    SimplePipe <PlanarImage> sp5 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) showImageFilter3 );
    //TODO TresholdFilter konstruktor sollte die parameter von Außen bekommen
    TresholdFilter tresholdFilter = new TresholdFilter ((Writeable<PlanarImage>) sp5);
    SimplePipe <PlanarImage> sp4 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) tresholdFilter );

    ShowImageFilter showImageFilter2 = new ShowImageFilter((Writeable<PlanarImage>) sp4 );
    SimplePipe <PlanarImage> sp3 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) showImageFilter2 );
    //TODO RoiFilter konstruktor sollte die parameter von Außen bekommen
    RoiFilter roiFilter = new RoiFilter( (Writeable<PlanarImage>) sp3);
    SimplePipe <PlanarImage> sp2 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) roiFilter );

    ShowImageFilter showImageFilter1 = new ShowImageFilter((Writeable<PlanarImage>) sp2 );
    SimplePipe <PlanarImage> sp1 = new SimplePipe <PlanarImage>( (Writeable<PlanarImage>) showImageFilter1);
    SourcePicture source = new SourcePicture( "loetstellen.jpg" );

    source.run();
  }
}
