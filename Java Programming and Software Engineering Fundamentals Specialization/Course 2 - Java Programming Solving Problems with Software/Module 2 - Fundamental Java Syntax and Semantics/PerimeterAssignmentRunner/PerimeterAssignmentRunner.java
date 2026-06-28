import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int noOfPoints = 0;
        for(Point currPt: s.getPoints()){
            
            noOfPoints = noOfPoints + 1;
        }
        return noOfPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double totalLength = getPerimeter(s);
        int totalNoOfPoints = getNumPoints(s);
        double averageLength = totalLength/totalNoOfPoints;
        
        return averageLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        Point prevPt = s.getLastPoint();      
        double largestSide = 0.0;
        
        for (Point currPt : s.getPoints()) {
    
            double currDist = prevPt.distance(currPt);
            
            if (currDist > largestSide){
                largestSide = currDist;
                
            }
            
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX = 0.0;
        for(Point currPt: s.getPoints()){
        
            if(currPt.getX() > largestX){
                
                largestX = currPt.getX();
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
        for(File f: dr.selectedFiles()){
        
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if(perimeter > largestPerimeter){
            
                largestPerimeter = perimeter;
            }
        
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        File temp = null;
        
        for(File f: dr.selectedFiles()){
        
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr); 
            double perimeter = getPerimeter(s);
            if(perimeter == largestPerimeter){
            
                  temp = f;
            }
        
        
        }
        //return temp.getName();
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int totalNoOfPoints = getNumPoints(s);
        System.out.println("Total No of Points = "+ totalNoOfPoints);
        double averageLength = getAverageLength(s);
        System.out.println("Average Length = "+ averageLength);
        double LargestSide = getLargestSide(s);
        System.out.println("Largest Side = "+ LargestSide);
        double LargestXCoordinate = getLargestX(s);
        System.out.println("Largest X Coordinate = "+ LargestXCoordinate);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter of All Files = "+ largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String fileWithLargestPerimeter = getFileWithLargestPerimeter();
        System.out.println("Largest Perimeter of All Files = " + fileWithLargestPerimeter);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
