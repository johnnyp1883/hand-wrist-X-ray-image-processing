/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hand.wrist.x.ray.image.processing;

/**
 *
 * @author Makoto
 */
public class GrayHis {
    private int[] His;
    private double mean,moment,totalPixel,entropy;
   
    
public GrayHis(int[][] Img,int height,int width){
  His=new int[256];
  totalPixel=height*width;
  for(int i=0;i<height;i++){
      for(int j=0;j<width;j++)
          His[Img[i][j]]++;  
}
for(int i=0;i<256;i++)
   mean+=(double)(i*His[i])/totalPixel;

}    
public double GetMoment(int nth){
double pre=0,mid=0; 
for(int i=0;i<256;i++){    
     pre=Math.pow(((double)(i-mean)),nth);
     mid=pre*(((double)His[i])/totalPixel);
     moment+=mid;
     mid=pre=0;
}
return moment;
}
public double GetMean(){
return mean;
}
public double GetEntro(){
double pro=0;    
for(int i=0;i<256;i++){
    pro=((double)His[i])/totalPixel;
    entropy-=pro*(Math.log(pro)/Math.log(2));
    pro=0;
}
return entropy;

}
public double GetSD(){
return GetMoment(2);
}


}

