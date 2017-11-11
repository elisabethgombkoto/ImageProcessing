package utils;

/**
 * Created by Elisabeth on 11.11.2017.
 */
public class QualityData {

  private Coordinate _centroid;
  private double _diameter;

  public QualityData (){};

  public QualityData(Coordinate centroid, double diameter){
    _centroid = centroid;
    _diameter = diameter;
  }

  public Coordinate get_centroid() {
    return _centroid;
  }

  public void set_centroid(Coordinate _centroid) {
    this._centroid = _centroid;
  }

  public double get_diameter() {
    return _diameter;
  }

  public void set_diameter(double _diameter) {
    this._diameter = _diameter;
  }
}
