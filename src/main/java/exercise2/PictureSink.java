package exercise2;

import pmp.filter.Sink;
import pmp.interfaces.Readable;
import utils.Coordinate;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * Created by Bernd on 08.11.2017.
 * <p>
 * Berechent wird, ob die Mitte der Lötstelle mit der Mitte der Wunschposition übereinstimmt.
 * Es gibt eine Toleranz für diese Übereinstimmung und es muss im Kostruktor von außen injected werden.
 * Wenn die Berechnung stattgefunden hat, dann soll der Result in eine Textdatei geschrieben werden.
 */
public class PictureSink extends Sink<ArrayList<Coordinate>> {
    //expected_coordinates=[(73,77), (110,80), (202,80), (265,79), (330,81), (396,81)]

    private FileWriter _fileWriter;
    private String _exceptedMiddelCoordinatePath;
    private int _tolerance = 5;

    public PictureSink(Readable<ArrayList<Coordinate>> input, String path, FileWriter fileWriter) throws InvalidParameterException {
        super( input );
        _exceptedMiddelCoordinatePath = path;
        _fileWriter = fileWriter;
    }

    public PictureSink(String path, FileWriter fileWriter) {
        _exceptedMiddelCoordinatePath = path;
        _fileWriter = fileWriter;
    }

    @Override
    public void write(ArrayList<Coordinate> actualValues) throws StreamCorruptedException {
        ArrayList<Coordinate> expectedValues = createExpectedCoordinateList();
        StringBuilder stringBuilder = new StringBuilder();

        int i = 1;

        for (Coordinate coordinate : actualValues) {
            String s = i++ + ".Point: Centre -> x = " + coordinate._x + " y = " + coordinate._y +
                    ", Tolerance ->  " + _tolerance + " in the tolerance range ->  " +
                    isInTolerance( coordinate, expectedValues.get( i - 2 ) ) + "\n";
            stringBuilder.append( s );
        }
        try {
            _fileWriter.write( stringBuilder.toString() );
            _fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isInTolerance(Coordinate actualCoordinate, Coordinate expectedCoordinate) {
        int differenceX = actualCoordinate._x - expectedCoordinate._x;
        int differenceY = actualCoordinate._y - expectedCoordinate._y;

        if ((_tolerance > Math.abs( differenceX )) && (_tolerance > Math.abs( differenceY ))) {
            return true;
        }
        return false;
    }

    private ArrayList<Coordinate> createExpectedCoordinateList() {

        ArrayList<Coordinate> expected = new ArrayList<Coordinate>();
        StringBuilder sb = new StringBuilder();
        ArrayList<String> coordinateStrings = new ArrayList<>();

        BufferedReader br = null;
        try {
            br = new BufferedReader( new FileReader( _exceptedMiddelCoordinatePath ) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int c;
        try {

            while ((c = br.read()) != -1) {

                while ((!Character.isDigit( (char) c )) && c != -1) {
                    c = br.read();
                }

                while ((Character.isDigit( (char) c )) && c != -1) {
                    sb.append( (char) c );
                    c = br.read();
                }

                if (!sb.toString().isEmpty()) {
                    coordinateStrings.add( sb.toString() );
                    sb = new StringBuilder();
                }

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int x = 0;
        int y = 0;
        for (int i = 0; i < coordinateStrings.size(); i++) {
            if (i % 2 == 0) {
                x = Integer.parseInt( coordinateStrings.get( i ) );
            } else {
                y = Integer.parseInt( coordinateStrings.get( i ) );
                expected.add( new Coordinate( x, y ) );
            }
        }

        return expected;
    }

    @Override
    public void run() {
        ArrayList<Coordinate> input;
        try {
            input = m_Input.read();

            if (input != null) {
                write( input );
            }
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        }
    }
}
