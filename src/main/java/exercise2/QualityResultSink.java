package exercise2;

import utils.Coordinate;
import utils.QualityData;
import pmp.filter.Sink;
import pmp.interfaces.Readable;

import java.io.*;
import java.nio.charset.Charset;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Elisabeth on 11.11.2017.
 */
public class QualityResultSink extends Sink<ArrayList<QualityData>> {
  //expected_coordinates=[(73,77), (110,80), (202,80), (265,79), (330,81), (396,81)]

  private FileWriter _fileWriter;
  private String _exceptedMiddelCoordinatePath;
  private  int _tolerance = 5;

  public QualityResultSink(Readable<ArrayList<QualityData>> input, String path, FileWriter fileWriter) throws InvalidParameterException {
    super(input);
    _exceptedMiddelCoordinatePath = path;
    _fileWriter = fileWriter;
  }

  public QualityResultSink(String path, FileWriter fileWriter)  {
    _exceptedMiddelCoordinatePath = path;
    _fileWriter = fileWriter;
  }

  @Override
  public void write(ArrayList<QualityData> actualValues) throws StreamCorruptedException {
    ArrayList<Coordinate> expectedValues = createExpectedCordinateList(_exceptedMiddelCoordinatePath);
    StringBuilder stringBuilder = new StringBuilder();

    int i = 1;

    for ( QualityData qualityData : actualValues) {
      String s = i++ + ".Point: Centre -> x = " + qualityData.get_centroid()._x + " y = " + qualityData.get_centroid()._y +
          ", Diameter-> " + qualityData.get_diameter() + ", Tolerance -> ±  " + _tolerance + " in the tolerance range ->  " +
          isInTolerance(qualityData.get_centroid(), expectedValues.get(i-2)) + "\n";
      stringBuilder.append(s);
    }
    try {
      _fileWriter.write(stringBuilder.toString());
      _fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private boolean isInTolerance(Coordinate actuelCordinate, Coordinate expectedCoordinate) {
    int differencX = actuelCordinate._x-expectedCoordinate._x;
    int differencY = actuelCordinate._y-expectedCoordinate._y;

    if((_tolerance> Math.abs(differencX)) &&(_tolerance>Math.abs(differencY))){
      return true;
    }
    return false;
  }

  private ArrayList<Coordinate> createExpectedCordinateList(String filePath){
    ArrayList<Coordinate> expected = new ArrayList<Coordinate>();
    Coordinate cor1 = new Coordinate(73,77);
    expected.add(cor1);
    Coordinate cor2 = new Coordinate(110,80);
    expected.add(cor2);
    Coordinate cor3 = new Coordinate(202,80);
    expected.add(cor3);
    Coordinate cor4 = new Coordinate(265,79);
    expected.add(cor4);
    Coordinate cor5 = new Coordinate(330,81);
    expected.add(cor5);
    Coordinate cor6 = new Coordinate(396,81);
    expected.add(cor6);

    return expected;
  }

  @Override
  public void run() {
    ArrayList<QualityData> input;
    try {
      input = m_Input.read();

      if (input != null) {
        write(input);
      }
    } catch (StreamCorruptedException e) {
      e.printStackTrace();
    }
  }
}