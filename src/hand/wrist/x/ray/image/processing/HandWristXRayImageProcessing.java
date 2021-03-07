
package hand.wrist.x.ray.image.processing;


import java.awt.image.BufferedImage;
import java.io.*;

enum Scale{three,five,seven};

public class HandWristXRayImageProcessing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      int height,width;
        int Red[][],Green[][],Blue[][];
        String FileName="5025_ASI_F_5.17.bmp";
                //"145-Female Bone Age 3Years 6Months.bmp";
        String input="D://Radipgraphic Atlas of Skeletal Development of the Hand and Wrist 掃描圖檔/output/"+FileName;
        //Lena
        
        String output="D:/Radipgraphic Atlas of Skeletal Development of the Hand and Wrist 掃描圖檔/output/處理後的output/";
        try{
         ReadImage readimage=new ReadImage(input);
         height=readimage.getHeight();
         width=readimage.getWidth();
              
         Red=readimage.red;
         Green=readimage.green;
         Blue=readimage.blue;
         width=readimage.width;
         height=readimage.height;
         int gray[][]=new int[height][width];
      //轉灰階
         for(int i=0;i<height;i++){
             for(int j=0;j<width;j++){
                 gray[i][j]=(int)(0.299*((float)Red[i][j])+0.587*((float)Green[i][j])+0.114*((float)Blue[i][j]));}}
         readimage=null;
         WriteBPM writebmp=new WriteBPM();
         //存灰階  
         writebmp.SaveGray(output+"//"+"Gray-"+FileName,height,width,gray);
     /*   
       //做7X7的高斯模糊 
         GuassianFilter GF=new GuassianFilter(Scale.seven,gray,height,width); 
         int[][] blur=GF.getImg();
         writebmp.SaveGray(output+"/"+"2-G7blur.bmp", height, width, blur);*/
      //作median Filter
       /*  MedianFilter MF=new MedianFilter(Scale.seven,gray,height,width);
         int[][] med=MF.getImg();
         writebmp.SaveGray(output+"//"+"3-7MedianLena.bmp", height, width, med);
         MF=null;
         */
        //作反白
         NegativeImg ne=new NegativeImg(gray,height,width);
         int[][] NeImg=ne.getImage();
         writebmp.SaveGray(output+"//"+"Negative-"+FileName,height,width,NeImg);
         //計算灰階值之mean 及 standard deviation
         ComputeVariance cv=new ComputeVariance(gray,height,width);
         double mean=cv.getMean();
         double SD=cv.getSD();
        
         System.out.println(mean+","+SD);
         GrayHis GH=new GrayHis(gray,height,width); //生成灰階histogram物件
         System.out.println(GH.GetMean()+","+Math.sqrt(GH.GetMoment(2))+GH.GetEntro());
         
         int[] grayHis=new int[256];
         BackGround2Value B2v=new BackGround2Value(gray,height,width,mean,SD);
         int[][] B2va=B2v.getImg();
         writebmp.SaveGray(output+"/"+"Bivalue-"+FileName,height,width,B2va);
       //處理雜訊  
         MedianFilter MF=new MedianFilter(Scale.three,B2va,height,width);
         int[][] med=MF.getImg();
         writebmp.SaveGray(output+"//"+"3Median2Value-"+FileName, height, width, med);
         MF=null;
         
        }catch(IOException e){System.out.println("讀取圖檔發生錯誤");}
        
        
    }
}
