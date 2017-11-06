package exercise2;

import com.sun.media.jai.widget.DisplayJAI;

import javax.media.jai.PlanarImage;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Bernd on 06.11.2017.
 */
public class ImageViewHelper {

    private PlanarImage _image;

    public ImageViewHelper (PlanarImage image){
        _image = image;
    }

    public void viewImage(){
        String imageInfo =
                "Dimensions: "+image.getWidth()+"x"+image.getHeight()+ " Bands:"+image.getNumBands();

        System.out.println("width: "+image.getWidth() + " " + "height: " + " " + +image.getHeight());

// Create a frame for display.
        JFrame frame = new JFrame();
        frame.setTitle("DisplayJAI: loetstellen.jpg");

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
