package exercise2;

import calcCentroids.Coordinate;
import pmp.filter.Sink;

import java.util.ArrayList;

/**
 * Created by Bernd on 08.11.2017.
 *
 * berechent wird ob der mitte der lötsetelle mit der mitte der wunschposition übereinstimmt
 * es gibt eine toleranz für diese übereinsttimmung es muss in kostruktor von außen injected werden.
 * wenn die berechnung statgefunden hat dann soll der result in eine txt file geschrieben werden.
 */
public class PictureSink extends Sink<ArrayList<Coordinate>> {
}
