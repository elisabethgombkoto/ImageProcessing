import pmp.pipes.SimplePipe;

import java.io.StreamCorruptedException;

/**
 * Created by Elisabeth on 30.10.2017.
 */
public class Run {
  public static void main(String[] args) throws StreamCorruptedException {
    String lenaPaht = "C:\\Users\\Elisabeth\\IdeaProjects\\ImageProcessing\\src\\main\\resources\\lena512.JPG";
    SourcePicture lena =  new SourcePicture(lenaPaht);
    SimplePipe<String> sp1 = new SimplePipe<String>(lena);
  }


}
