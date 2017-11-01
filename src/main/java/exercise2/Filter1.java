package exercise2;

import com.sun.media.jai.widget.DisplayJAI;
import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import javax.swing.*;
import java.awt.*;
import java.security.InvalidParameterException;

/**
 * Created by Elisabeth on 31.10.2017.
 */
public class Filter1 extends DataTransformationFilter2<PlanarImage, PlanarImage> {

  public Filter1(Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
    super(input, output);
  }

  public Filter1(Readable<PlanarImage> input) throws InvalidParameterException {
    super(input);
  }

  public Filter1(Writeable<PlanarImage> output) throws InvalidParameterException {
    super(output);
  }

  protected PlanarImage process(PlanarImage image) {
    //Get some information about the image
    String imageInfo =
        "Dimensions: "+image.getWidth()+"x"+image.getHeight()+ " Bands:"+image.getNumBands();

// Create a frame for display.
    JFrame frame = new JFrame();
    frame.setTitle("DisplayJAI: lena512.jpg");

// Get the JFrame� ContentPane.
    Container contentPane = frame.getContentPane();
    contentPane.setLayout(new BorderLayout());



// Create an instance of DisplayJAI.
    DisplayJAI dj = new DisplayJAI(image);


// Add to the JFrame� ContentPane an instance of JScrollPane
// containing the DisplayJAI instance.
    contentPane.add(new JScrollPane(dj),BorderLayout.CENTER);

// Add a text label with the image information.
    contentPane.add(new JLabel(imageInfo),BorderLayout.SOUTH);

// Set the closing operation so the application is finished.
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500,600); // adjust the frame size.
    frame.setVisible(true); // show the frame.
    return null;
  }
}
