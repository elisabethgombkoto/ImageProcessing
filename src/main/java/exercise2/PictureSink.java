package exercise2;

import calcCentroids.Coordinate;
import pmp.filter.Sink;
import pmp.interfaces.Readable;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bernd on 08.11.2017.
 *
 * berechent wird ob der mitte der lötsetelle mit der mitte der wunschposition übereinstimmt
 * es gibt eine toleranz für diese übereinsttimmung es muss in kostruktor von außen injected werden.
 * wenn die berechnung statgefunden hat dann soll der result in eine txt file geschrieben werden.
 */
public class PictureSink extends Sink<ArrayList<Coordinate>> {
  //expected_coordinates=[(73,77), (110,80), (202,80), (265,79), (330,81), (396,81)]

  private FileWriter _fileWriter;
  private String _exeptedCoordinates;
  private  int _tolerance = 5;

  public PictureSink(Readable<ArrayList<Coordinate>> input, String path, FileWriter fileWriter) throws InvalidParameterException{
    super(input);
    _exeptedCoordinates = path;
    _fileWriter = fileWriter;
  }

  public PictureSink(String path, FileWriter fileWriter)  {
    _exeptedCoordinates = path;
    _fileWriter = fileWriter;
  }

  @Override
  public void write(ArrayList<Coordinate> actualValues) throws StreamCorruptedException {
    ArrayList<Coordinate> expectedValues = createExpectedCordinateList(_exceptedMiddelCoordinatePath);
    StringBuilder stringBuilder = new StringBuilder();

    int i = 1;

    for ( Coordinate cordinate : actualValues) {
      String s = i++ + ".Point: Centre -> x = " + cordinate._x + " y = " + cordinate._y +
       ", Tolerance -> ±  " + _tolerance + " in the tolerance range ->  " +
          isInTolerance(cordinate, expectedValues.get(i-2)) + "\n";
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


    //TODO Bernd array from file einlesen nicht
    ArrayList<Coordinate> expected = new ArrayList<Coordinate>();

      Reader reader = null;
      try
      {
          reader = new FileReader( );

          for ( int c; ( c = reader.read() ) != -1; )
          System.out.print( (char) c );
      }
      catch ( IOException e ) {
          System.err.println( "Fehler beim Lesen der Datei!" );
      }
      finally {
          try { reader.close(); } catch ( Exception e ) { e.printStackTrace(); }
      }





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
    ArrayList<Coordinate> input;
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
