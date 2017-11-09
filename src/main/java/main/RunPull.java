package main;

import calcCentroids.CalcCentroidsFilter;
import calcCentroids.Coordinate;
import exercise2.*;
import pmp.interfaces.Readable;
import pmp.pipes.SimplePipe;

import javax.media.jai.PlanarImage;
import javax.media.jai.operator.MedianFilterDescriptor;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Elisabeth on 09.11.2017.
 */
public class RunPull {
  public static void main(String[] args) {
    System.setProperty("com.sun.media.jai.disableMediaLib", "true");
    try {
      File file = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\resultsPull.txt");
      FileWriter fileWriter = new FileWriter(file);

      SourcePicture sourcePicture = new SourcePicture();
      SimplePipe<PlanarImage> sp1 = new SimplePipe<PlanarImage>(sourcePicture);
      ShowImageFilter showImageFilter1 = new ShowImageFilter((Readable<PlanarImage>) sp1," Original picture");
      SimplePipe<PlanarImage> sp2 = new SimplePipe<PlanarImage>((Readable<PlanarImage>) showImageFilter1);

      // Region of Interest Filter, Parameterübergabe des Bildausschnittes im Konstruktor.
      RoiFilter roiFilter = new RoiFilter((Readable<PlanarImage>) sp2, new Rectangle(30, 50, 447, 100));
      SimplePipe<PlanarImage> sp3 = new SimplePipe<PlanarImage>((Readable<PlanarImage>) roiFilter);
      ShowImageFilter showImageFilter2 = new ShowImageFilter((Readable<PlanarImage>) sp3," Region of Interest-Filter" );
      SimplePipe<PlanarImage> sp4 = new SimplePipe<PlanarImage>((Readable<PlanarImage>) showImageFilter2);

      // Tresholdfilter, Parameterübergabe, Parameterübergabe der Schwellenwerte im Konstruktur.
      TresholdFilter tresholdFilter = new TresholdFilter((Readable<PlanarImage>) sp4, 0, 35 , 255);
      SimplePipe<PlanarImage> sp5 = new SimplePipe<PlanarImage>((Readable<PlanarImage>) tresholdFilter);
      ShowImageFilter showImageFilter3 = new ShowImageFilter((Readable<PlanarImage>) sp5," Treshold-Filter" );
      SimplePipe<PlanarImage> sp6 = new SimplePipe<PlanarImage>((Readable<PlanarImage>) showImageFilter3);

      //MedianFilter, Parameterübergabe Mask Square und size 5.
      MedianFilter medianFilter = new MedianFilter((Readable<PlanarImage>) sp6, MedianFilterDescriptor.MEDIAN_MASK_SQUARE, 5);
      SimplePipe <PlanarImage> sp7 = new SimplePipe <PlanarImage> ((Readable<PlanarImage>) medianFilter );
      ShowImageFilter showImageFilter4 = new ShowImageFilter((Readable<PlanarImage>) sp7," Median-Filter" );
      SimplePipe<PlanarImage> sp8 = new SimplePipe<PlanarImage>((Readable<PlanarImage>) showImageFilter4);

      //OpeningFilter
      OpeningFilter openingFilter = new OpeningFilter ((Readable<PlanarImage>) sp8);
      SimplePipe <PlanarImage> sp9 = new SimplePipe <PlanarImage> ((Readable<PlanarImage>) openingFilter );
      ShowImageFilter showImageFilter5 = new ShowImageFilter((Readable<PlanarImage>) sp9," Morphological Transformations Opening-Filter" );
      SimplePipe<PlanarImage> sp10 = new SimplePipe<PlanarImage>((Readable<PlanarImage>) showImageFilter5);

      //save
      ImageToFileFilter imageToFileFilter = new ImageToFileFilter(System.getProperty("user.dir")+"\\src\\main\\resources\\picturePull.jpg",(Readable<PlanarImage>) sp10);
      SimplePipe <PlanarImage> sp11 = new SimplePipe <PlanarImage> ((Readable<PlanarImage>) imageToFileFilter );

      CalcCentroidsFilter calcCentroidFilter = new CalcCentroidsFilter(sp11);
      SimplePipe <ArrayList<Coordinate>> sp12 = new SimplePipe <ArrayList<Coordinate>> (calcCentroidFilter);

      PictureSink pictureSink = new PictureSink((Readable<ArrayList<Coordinate>>) sp12, "hier sind die expected mittelpunkte zum finden",fileWriter);

      pictureSink.run();




    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
