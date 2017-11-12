package main;

import java.io.StreamCorruptedException;
import java.util.Scanner;

/**
 * Created by Bernd on 11.11.2017.
 */
public class Run {
    String selected;
    RunPushWithDiameter runPushWithDiameter;
    RunPullWithDiameter runPullWithDiameter;
    RunPush runPush;
    RunPull runPull;

    public Run(String str) {
        selected = str;
        try {
            runMethod();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        }
    }

    private void runMethod() throws StreamCorruptedException {
        switch (selected) {
            case "a":
                runPushWithDiameter = new RunPushWithDiameter( "\\loetstellen.jpg", "\\expectedCentroids.txt", "\\picturePushWithDiameter.png", "\\resultsPushWithDiameter.txt" );
                break;
            case "b":
                runPullWithDiameter = new RunPullWithDiameter( "\\loetstellen.jpg", "\\expectedCentroids.txt", "\\picturePullWithDiameter.png", "\\resultsPullWithDiameter.txt" );
                break;
            case "c":
                runPush = new RunPush( "\\loetstellen.jpg", "\\expectedCentroids.txt", "\\picturePush.png", "\\resultsPush.txt" );
                break;
            case "d":
                runPull = new RunPull( "\\loetstellen.jpg", "\\expectedCentroids.txt", "\\picturePull.png", "\\resultsPull.txt" );
                break;
            default:
                runPushWithDiameter = new RunPushWithDiameter( "\\loetstellen.jpg", "\\expectedCentroids.txt", "\\picturePushWithDiameter.png", "\\resultsPushWithDiameter.txt" );
        }
    }


    public static void main(String[] args) {

        System.out.println( "Enter 'a' for Push with Diameter" );
        System.out.println( "Enter 'b' for Pull with Diameter" );
        System.out.println( "Enter 'c' for Push without Diameter" );
        System.out.println( "Enter 'd' for Pull without Diameter" );

        Scanner scanner = new Scanner( System.in );
        String selected = scanner.nextLine();
        System.out.println( "You selected " + selected );
        Run run = new Run( selected );


    }
}
