package main;

import exercise2.*;
import pmp.interfaces.Readable;
import pmp.pipes.SimplePipe;
import utils.QualityData;

import javax.media.jai.PlanarImage;
import javax.media.jai.operator.MedianFilterDescriptor;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Elisabeth on 11.11.2017.
 */
public class RunPullWithDiameter {
    String _sourcePicturePath;
    String _expectedCentroidPath;
    String _destPicturePath;
    String _resultPath;
    String userDir = System.getProperty( "user.dir" );

    public RunPullWithDiameter(String sourcePicturePath, String expectedCentroidPath, String destPicturePath, String resultPath) {
        System.setProperty( "com.sun.media.jai.disableMediaLib", "true" );
        _sourcePicturePath = sourcePicturePath;
        _expectedCentroidPath = expectedCentroidPath;
        _destPicturePath = destPicturePath;
        _resultPath = resultPath;

        try {
            File file = new File( userDir + _resultPath );
            FileWriter fileWriter = new FileWriter( file );

            SourcePicture sourcePicture = new SourcePicture( userDir + _sourcePicturePath );
            SimplePipe<PlanarImage> sp1 = new SimplePipe<PlanarImage>( sourcePicture );
            ShowImageFilter showImageFilter1 = new ShowImageFilter( (Readable<PlanarImage>) sp1, " Original picture" );
            SimplePipe<PlanarImage> sp2 = new SimplePipe<PlanarImage>( (Readable<PlanarImage>) showImageFilter1 );

            // Region of Interest Filter, Parameterübergabe des Bildausschnittes im Konstruktor.
            RoiFilter roiFilter = new RoiFilter( (Readable<PlanarImage>) sp2, new Rectangle( 30, 50, 447, 100 ) );
            SimplePipe<PlanarImage> sp3 = new SimplePipe<PlanarImage>( (Readable<PlanarImage>) roiFilter );
            ShowImageFilter showImageFilter2 = new ShowImageFilter( (Readable<PlanarImage>) sp3, " Region of Interest-Filter" );
            SimplePipe<PlanarImage> sp4 = new SimplePipe<PlanarImage>( (Readable<PlanarImage>) showImageFilter2 );

            // Tresholdfilter, Parameterübergabe, Parameterübergabe der Schwellenwerte im Konstruktur.
            TresholdFilter tresholdFilter = new TresholdFilter( (Readable<PlanarImage>) sp4, 0, 35, 255 );
            SimplePipe<PlanarImage> sp5 = new SimplePipe<PlanarImage>( (Readable<PlanarImage>) tresholdFilter );
            ShowImageFilter showImageFilter3 = new ShowImageFilter( (Readable<PlanarImage>) sp5, " Treshold-Filter" );
            SimplePipe<PlanarImage> sp6 = new SimplePipe<PlanarImage>( (Readable<PlanarImage>) showImageFilter3 );

            //MedianFilter, Parameterübergabe Mask Square und size 5.
            MedianFilter medianFilter = new MedianFilter( (Readable<PlanarImage>) sp6, MedianFilterDescriptor.MEDIAN_MASK_SQUARE, 5 );
            SimplePipe<PlanarImage> sp7 = new SimplePipe<PlanarImage>( (Readable<PlanarImage>) medianFilter );
            ShowImageFilter showImageFilter4 = new ShowImageFilter( (Readable<PlanarImage>) sp7, " Median-Filter" );
            SimplePipe<PlanarImage> sp8 = new SimplePipe<PlanarImage>( (Readable<PlanarImage>) showImageFilter4 );

            //OpeningFilter
            OpeningFilter openingFilter = new OpeningFilter( (Readable<PlanarImage>) sp8 );
            SimplePipe<PlanarImage> sp9 = new SimplePipe<PlanarImage>( (Readable<PlanarImage>) openingFilter );
            ShowImageFilter showImageFilter5 = new ShowImageFilter( (Readable<PlanarImage>) sp9, " Morphological Transformations Opening-Filter" );
            SimplePipe<PlanarImage> sp10 = new SimplePipe<PlanarImage>( (Readable<PlanarImage>) showImageFilter5 );

            //save
            ImageToFileFilter imageToFileFilter = new ImageToFileFilter( userDir + _destPicturePath, (Readable<PlanarImage>) sp10 );
            SimplePipe<PlanarImage> sp11 = new SimplePipe<PlanarImage>( (Readable<PlanarImage>) imageToFileFilter );

            QualityDatasFilter qualityDatasFilter = new QualityDatasFilter( sp11 );
            SimplePipe<ArrayList<QualityData>> sp12 = new SimplePipe<ArrayList<QualityData>>( qualityDatasFilter );

            QualityResultSink qualityResultSink = new QualityResultSink( (Readable<ArrayList<QualityData>>) sp12, userDir + _resultPath, fileWriter );

            qualityResultSink.run();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
