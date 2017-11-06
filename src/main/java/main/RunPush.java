package main;

import exercise2.RoiFilter;
import exercise2.SourceImageFilter;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;

import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StreamCorruptedException;

/**
 * Created by Elisabeth on 30.10.2017.
 */
public class RunPush {
  public static void main(String[] args) throws StreamCorruptedException {
    try {


      SimplePipe simpleThresholdPipe = new SimplePipe( (Writeable) thresholdFilter );
      RoiFilter roiFilter = new RoiFilter( (Writeable) simpleThresholdPipe);
      SimplePipe roiPipe = new SimplePipe( (Writeable) roiFilter );
      SourceImageFilter sourceImageFilter = new SourceImageFilter((Writeable) roiPipe );
      sourceImageFilter.run();
    }
    catch (IOException e) {
    e.printStackTrace();
  }

  }


}
