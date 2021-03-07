/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hand.wrist.x.ray.image.processing;

import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;


 public class WriteBPM {
   
    
    private int pixel[];
    byte writein[];
    public void SaveGray(String savepath,int height,int width,int gray[][]){  
    
         FileOutputStream fileout;
         File file=new File(savepath);
         pixel=new int[height*width];
         
         BufferedImage gf=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
         int tmp;
         for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
              tmp=gray[i][j];
              pixel[i*width+j]=0xff000000|((tmp<<16)|(tmp<<8)|tmp);
              //將tmp的值重複填入0xff000000的RGB三個顏色域
            }
         }
          gf.setRGB(0, 0, width, height,pixel,0, width);
          //將灰階值寫入BufferedImage gf
          
         try{ 
          fileout=new FileOutputStream(file);
          ImageIO.write(gf, "bmp", fileout);
        //經由gf寫入fileout的串流來寫入檔案
          fileout.close();
         }catch(IOException en){System.out.println("檔案寫入失敗"); }
         
          }
    public void SaveColor(String savepath,int height,int width,int red[][],int green[][],int blue[][]){  
    //待修
         FileOutputStream fileout;
         File file=new File(savepath);
         pixel=new int[height*width];
         
         BufferedImage gf=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
         int tmp1,tmp2,tmp3;
         for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
              tmp1=red[i][j];
              tmp2=green[i][j];
              tmp3=blue[i][j];        
              pixel[i*width+j]=0xff000000|((tmp1<<16)|(tmp2<<8)|tmp3);
              //將tmp的值重複填入0xff000000的RGB三個顏色域
            }
         }
          gf.setRGB(0, 0, width, height,pixel,0, width);
          //將RGB值寫入BufferedImage gf
          
         try{ 
          fileout=new FileOutputStream(file);
          ImageIO.write(gf, "bmp", fileout);
        //經由gf寫入fileout的串流來寫入檔案
          fileout.close();
         }catch(IOException en){System.out.println("檔案寫入失敗"); }
         
         
 }
}
