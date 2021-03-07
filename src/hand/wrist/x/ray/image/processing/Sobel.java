/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hand.wrist.x.ray.image.processing;

import java.lang.Math.*;
        
public class Sobel {
    private int[][] result;
    private double[][] angle;
   
    public Sobel(int[][] inputImg,int height,int width){
     int[][] tempImg;   
     result=new int[height][width];
     int blockMid=1; //3x3的sobel mask超出image之外的長度為1
     int tempH=height+blockMid*2;//將image原長加上超出的上下兩邊mask多出的長
     int tempW=width+blockMid*2;//將image原寬加上超出去的左右兩邊mask多出的寬
     //int[][] tempImg ;
     //產生加大的鏡像影像
     Reflection reflection = new Reflection(inputImg,tempH,tempW,blockMid);
     //產生比原圖長寬各大2blockmid的影像pixel值，外圍的值用鏡射去計算
     tempImg=reflection.getImage();
    
     int value1=0;
     int value2=0;
     for(int i=blockMid;i<tempH-blockMid;i++){
         for(int j=blockMid;j<tempW-blockMid;j++){
 		     //y方向sobel        
             for(int m=j-blockMid;m<=j+blockMid;m++)
                { if(m==j)  
                    value1+=tempImg[i+blockMid][m]*(-2);
                  else
                    value1+=tempImg[i+blockMid][m]*(-1);}  
               for(int n=j-blockMid;n<=j+blockMid;n++)
                { if(n==j)  
                    value1+=tempImg[i-blockMid][n]*2;
                  else
                    value1+=tempImg[i-blockMid][n];} 
   //x方向sobel            
           for(int m=i-blockMid;m<=i+blockMid;m++)
                { if(m==i)  
                    value2+=tempImg[m][j+blockMid]*2;
                  else
                    value2+=tempImg[m][j+blockMid];}  
               for(int n=i-blockMid;n<=i+blockMid;n++)
                { if(n==i)  
                    value2-=tempImg[n][j-blockMid]*2;
                  else
                    value2-=tempImg[n][j-blockMid];} 
              
            result[i-blockMid][j-blockMid]= Math.abs(value1)+Math.abs(value2);
            angle[i-blockMid][j-blockMid]=Math.atan2(Math.abs(value1),Math.abs(value2));
         if(result[i-blockMid][j-blockMid]>255)
            result[i-blockMid][j-blockMid]=255;
         value1=0;
         value2=0;
         }}
  }
    public double[][] getAngle(){

     return angle;
}
    public int[][] getImage(){
    return result;
    }
}
