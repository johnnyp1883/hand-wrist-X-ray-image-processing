/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hand.wrist.x.ray.image.processing;

/**
 *
 * @author Makoto
 */
public class ComputeVariance {
    private double mean;
    private double SD;
    ComputeVariance(int[][] Img,int height,int width){
       double Total=height*width; 
       for(int i=0;i<height;i++){
           for(int j=0;j<width;j++)
               mean+=Img[i][j];}
             mean/=Total;
       for(int i=0;i<height;i++){
           for(int j=0;j<width;j++)
             SD+= ((double)Img[i][j]-mean)* ((double)Img[i][j]-mean);}
       SD/=Total;
       SD=Math.sqrt(SD);
    }
    public double getMean(){
    return mean;
    }
    public double getSD(){
    return SD;
    }
}
