package main;

import exercise2.Filter1;
import exercise2.SourcePicture;
import pmp.interfaces.Readable;
import pmp.pipes.SimplePipe;

import javax.media.jai.PlanarImage;
import java.io.StreamCorruptedException;
import java.util.ResourceBundle;

/**
 * Created by Elisabeth on 30.10.2017.
 */
public class Run {
  public static void main(String[] args) throws StreamCorruptedException {
    String workspaceDir = System.getProperty("user.dir");
    String loetstellePath= workspaceDir + "\\src\\main\\resources\\lena512.JPG";
    //String lenaPath ="C:\\Users\\Elisabeth\\IdeaProjects\\ImageProcessing\\src\\main\\resources\\lena512.JPG";
    SourcePicture lena =  new SourcePicture(loetstellePath);
    System.out.println(System.getProperty("user.dir"));



    SimplePipe<PlanarImage> sp1 = new SimplePipe<PlanarImage>(lena);
    Filter1 filter1 = new Filter1((Readable<PlanarImage>) sp1);
    filter1.read();
  }


}