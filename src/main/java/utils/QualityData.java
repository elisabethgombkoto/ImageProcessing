package utils;

/**
 * Created by Elisabeth on 11.11.2017.
 */
public class QualityData {

  private Coordinate _centroid;
  private int _diameter;

  public QualityData (){};

  public QualityData(Coordinate centroid, int diameter){
    _centroid = centroid;
    _diameter = diameter;
  }

  public Coordinate get_centroid() {
    return _centroid;
  }

  public void set_centroid(Coordinate centroid) {
    this._centroid = centroid;
  }

  public int get_diameter() {
    return _diameter;
  }

  public void set_diameter(int diameter) {
    this._diameter = diameter;
  }
}
