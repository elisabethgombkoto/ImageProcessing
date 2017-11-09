package main;

import calcCentroids.CalcCentroidsFilter;
import calcCentroids.Coordinate;
import exercise2.*;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;

import javax.media.jai.PlanarImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

/**
 * Created by Elisabeth on 30.10.2017.
 */
public class RunPush {

  public static void main(String[] args) throws StreamCorruptedException, FileNotFoundException {
    System.setProperty("com.sun.media.jai.disableMediaLib", "true");

    //TODO ich check es noch nicht, also wir machen erst den rest fertig ok?
    //Katja erklärt mir es morgen, dann kann ich diese auch koorekt in main einfügen
    PictureSink pictureSink = new PictureSink();
    SimplePipe <ArrayList<Coordinate>> sp12 = new SimplePipe (pictureSink );
    CalcCentroidsFilter calcCentroidFilter = new CalcCentroidsFilter(sp12);
    SimplePipe <PlanarImage> sp11 = new SimplePipe <PlanarImage> ( calcCentroidFilter );

    //TODO absolute path auf relative path ändern
    ImageToFileFilter imageToFileFilter = new ImageToFileFilter(System.getProperty("user.dir")+"\\src\\main\\resources\\picture.jpg",(Writeable<PlanarImage>) sp11);
    SimplePipe <PlanarImage> sp10 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) imageToFileFilter );

    ShowImageFilter showImageFilter5 = new ShowImageFilter((Writeable<PlanarImage>) sp10, "Morphological Transformations Opening Filter" );
    SimplePipe <PlanarImage> sp9 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) showImageFilter5 );
    //TODO OpeningFilter konstruktor sollte die parameter von Außen bekommen
    OpeningFilter openingFilter = new OpeningFilter ((Writeable<PlanarImage>) sp9);
    SimplePipe <PlanarImage> sp8 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) openingFilter );

    ShowImageFilter showImageFilter4 = new ShowImageFilter((Writeable<PlanarImage>) sp8, "Median-Filter" );
    SimplePipe <PlanarImage> sp7 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) showImageFilter4 );
    //TODO MedianFilterDescriptor.MEDIAN_MASK_SQUARE, 5, (Writeable<PlanarImage>) new SimplePipe<PlanarImage>
    //TODO oder maskSize: 5 sollte die parameter für die konstruktor sein???
    MedianFilter medianFilter = new MedianFilter ((Writeable<PlanarImage>) sp7);
    SimplePipe <PlanarImage> sp6 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) medianFilter );

    ShowImageFilter showImageFilter3 = new ShowImageFilter((Writeable<PlanarImage>) sp6, "Thresholding-Filter" );
    SimplePipe <PlanarImage> sp5 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) showImageFilter3 );
    //TODO TresholdFilter konstruktor sollte die parameter von Außen bekommen
    TresholdFilter tresholdFilter = new TresholdFilter ((Writeable<PlanarImage>) sp5);
    SimplePipe <PlanarImage> sp4 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) tresholdFilter );

    ShowImageFilter showImageFilter2 = new ShowImageFilter((Writeable<PlanarImage>) sp4, " Region of Interest-Filter" );
    SimplePipe <PlanarImage> sp3 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) showImageFilter2 );
    //TODO RoiFilter konstruktor sollte die parameter von Außen bekommen
    RoiFilter roiFilter = new RoiFilter( (Writeable<PlanarImage>) sp3);
    SimplePipe <PlanarImage> sp2 = new SimplePipe <PlanarImage> ( (Writeable<PlanarImage>) roiFilter );

    ShowImageFilter showImageFilter1 = new ShowImageFilter((Writeable<PlanarImage>) sp2, "Original picture" );
    SimplePipe <PlanarImage> sp1 = new SimplePipe <PlanarImage>( (Writeable<PlanarImage>) showImageFilter1);
    SourcePicture source = new SourcePicture( "loetstellen.jpg", sp1 );

    source.run();

  }
}
