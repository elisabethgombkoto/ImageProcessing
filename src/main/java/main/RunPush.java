package main;

import filers.*;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;
import utils.Coordinate;

import javax.media.jai.PlanarImage;
import javax.media.jai.operator.MedianFilterDescriptor;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Elisabeth on 30.10.2017.
 */
public class RunPush {
    String _sourcePicturePath;
    String _expectedCentroidPath;
    String _destPicturePath;
    String _resultPath;
    String userDir = System.getProperty( "user.dir" );

    public RunPush(String sourcePicturePath, String expectedCentroidPath, String destPicturePath, String resultPath) {
        System.setProperty( "com.sun.media.jai.disableMediaLib", "true" );

        _sourcePicturePath = sourcePicturePath;
        _expectedCentroidPath = expectedCentroidPath;
        _destPicturePath = destPicturePath;
        _resultPath = resultPath;


        try {
            File file = new File( userDir + _resultPath );
            FileWriter fileWriter = new FileWriter( file );

            // new BufferedReader( new InputStreamReader( RunPush.class.getResourceAsStream( "expectedCentroids.txt" ) ) )
            PictureSink pictureSink = new PictureSink( userDir + _expectedCentroidPath, fileWriter );
            SimplePipe<ArrayList<Coordinate>> sp12 = new SimplePipe( pictureSink );
            CalcCentroidsFilter calcCentroidFilter = new CalcCentroidsFilter( sp12 );
            SimplePipe<PlanarImage> sp11 = new SimplePipe<PlanarImage>( calcCentroidFilter );

            // ImageToFileFilter, Parameterübergabe der dest-directory.
            ImageToFileFilter imageToFileFilter = new ImageToFileFilter( userDir + _destPicturePath, (Writeable<PlanarImage>) sp11 );
            SimplePipe<PlanarImage> sp10 = new SimplePipe<PlanarImage>( (Writeable<PlanarImage>) imageToFileFilter );

            ShowImageFilter showImageFilter5 = new ShowImageFilter( (Writeable<PlanarImage>) sp10, "Morphological Transformations Opening-Filter" );
            SimplePipe<PlanarImage> sp9 = new SimplePipe<PlanarImage>( (Writeable<PlanarImage>) showImageFilter5 );

            //OpeningFilter
            OpeningFilter openingFilter = new OpeningFilter( (Writeable<PlanarImage>) sp9 );
            SimplePipe<PlanarImage> sp8 = new SimplePipe<PlanarImage>( (Writeable<PlanarImage>) openingFilter );

            ShowImageFilter showImageFilter4 = new ShowImageFilter( (Writeable<PlanarImage>) sp8, "Median-Filter" );
            SimplePipe<PlanarImage> sp7 = new SimplePipe<PlanarImage>( (Writeable<PlanarImage>) showImageFilter4 );

            //MedianFilter, Parameterübergabe Mask Square und size 5.
            MedianFilter medianFilter = new MedianFilter( (Writeable<PlanarImage>) sp7, MedianFilterDescriptor.MEDIAN_MASK_SQUARE, 5 );
            SimplePipe<PlanarImage> sp6 = new SimplePipe<PlanarImage>( (Writeable<PlanarImage>) medianFilter );

            ShowImageFilter showImageFilter3 = new ShowImageFilter( (Writeable<PlanarImage>) sp6, "Thresholding-Filter" );
            SimplePipe<PlanarImage> sp5 = new SimplePipe<PlanarImage>( (Writeable<PlanarImage>) showImageFilter3 );

            // Tresholdfilter, Parameterübergabe, Parameterübergabe der Schwellenwerte im Konstruktur.
            TresholdFilter tresholdFilter = new TresholdFilter( (Writeable<PlanarImage>) sp5, 0, 35, 255 );
            SimplePipe<PlanarImage> sp4 = new SimplePipe<PlanarImage>( (Writeable<PlanarImage>) tresholdFilter );

            ShowImageFilter showImageFilter2 = new ShowImageFilter( (Writeable<PlanarImage>) sp4, " Region of Interest-Filter" );
            SimplePipe<PlanarImage> sp3 = new SimplePipe<PlanarImage>( (Writeable<PlanarImage>) showImageFilter2 );

            // Region of Interest Filter, Parameterübergabe des Bildausschnittes im Konstruktor.
            RoiFilter roiFilter = new RoiFilter( (Writeable<PlanarImage>) sp3, new Rectangle( 30, 50, 447, 100 ) );
            SimplePipe<PlanarImage> sp2 = new SimplePipe<PlanarImage>( (Writeable<PlanarImage>) roiFilter );

            ShowImageFilter showImageFilter1 = new ShowImageFilter( (Writeable<PlanarImage>) sp2, "Original picture" );
            SimplePipe<PlanarImage> sp1 = new SimplePipe<PlanarImage>( (Writeable<PlanarImage>) showImageFilter1 );
            SourcePicture source = new SourcePicture( userDir + _sourcePicturePath, sp1 );

            source.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
