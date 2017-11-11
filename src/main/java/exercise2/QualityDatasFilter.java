package exercise2;

import utils.Coordinate;
import utils.QualityData;
import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import java.awt.image.BufferedImage;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Elisabeth on 11.11.2017.
 */
public class QualityDatasFilter extends DataTransformationFilter2<PlanarImage, ArrayList<QualityData>> {

  private HashMap<Coordinate, Boolean> _general = new HashMap<Coordinate, Boolean>();
  private LinkedList<ArrayList<Coordinate>> _figures = new LinkedList<ArrayList<Coordinate>>();
  private javax.media.jai.PlanarImage _image;


  public QualityDatasFilter(Readable<PlanarImage> input) throws InvalidParameterException {
    super(input);
  }

  public QualityDatasFilter(Writeable<ArrayList<QualityData>> output) throws InvalidParameterException {
    super(output);
  }


  protected ArrayList<QualityData> process(PlanarImage entity) {
    _image = entity;
    BufferedImage bi = entity.getAsBufferedImage();

    for (int x = 0; x < bi.getWidth(); x++) {
      for (int y = 0; y < bi.getHeight(); y++) {
        int p = bi.getRaster().getSample(x, y, 0);
        if (p == 255 && _general.containsKey(new Coordinate(x, y)) == false) {
          getNextFigure(bi, x, y);        //if there is a not visited white pixel, save all pixels belonging to the same figure
        }
      }
    }

    return calculateCentroids();    //calculate the centroids of all figures
  }

  private void getNextFigure(BufferedImage img, int x, int y) {
    ArrayList<Coordinate> figure = new ArrayList<Coordinate>();
    _general.put( new Coordinate( x, y ), true );
    figure.add(new Coordinate(x, y));

    addConnectedComponents(img, figure, x, y);

    _figures.add(figure);
  }

  private void addConnectedComponents(BufferedImage img, ArrayList<Coordinate> figure, int x, int y) {
    if (x - 1 >= 0 && _general.containsKey((new Coordinate(x - 1, y))) == false && img.getRaster().getSample(x - 1, y, 0) == 255) {
      _general.put(new Coordinate(x - 1, y), true);
      figure.add(new Coordinate(x - 1, y));
      addConnectedComponents(img, figure, x - 1, y);
    }
    if (x + 1 < img.getWidth() && _general.containsKey((new Coordinate(x + 1, y))) == false && img.getRaster().getSample(x + 1, y, 0) == 255) {
      _general.put(new Coordinate(x + 1, y), true);
      figure.add(new Coordinate(x + 1, y));
      addConnectedComponents(img, figure, x + 1, y);
    }
    if (y - 1 >= 0 && _general.containsKey((new Coordinate(x, y - 1))) == false && img.getRaster().getSample(x, y - 1, 0) == 255) {
      _general.put(new Coordinate(x, y - 1), true);
      figure.add(new Coordinate(x, y - 1));
      addConnectedComponents(img, figure, x, y - 1);
    }
    if (y + 1 < img.getHeight() && _general.containsKey((new Coordinate(x, y + 1))) == false && img.getRaster().getSample(x, y + 1, 0) == 255) {
      _general.put(new Coordinate(x, y + 1), true);
      figure.add(new Coordinate(x, y + 1));
      addConnectedComponents(img, figure, x, y + 1);
    }
  }

  private ArrayList<QualityData> calculateCentroids() {
    ArrayList<QualityData> centroids = new ArrayList<QualityData>();
    int i = 0;
    for (ArrayList<Coordinate> figure : _figures) {
      LinkedList<Integer> xValues = new LinkedList<Integer>();
      LinkedList<Integer> yValues = new LinkedList<Integer>();

      for (Coordinate c : figure) {
        xValues.add(c._x);
        yValues.add(c._y);
      }

      Collections.sort(xValues);
      Collections.sort(yValues);

      int xMedian = xValues.get(xValues.size() / 2);
      int yMedian = yValues.get(yValues.size() / 2);
      QualityData qualityData = new QualityData();
      qualityData.set_centroid(new Coordinate(xMedian + (Integer) _image.getProperty("offsetX"), yMedian + (Integer) _image.getProperty("offsetY")));
      qualityData.set_diameter(xValues.size());
      centroids.add(i, qualityData);

      i++;
    }
    return centroids;
  }
}
