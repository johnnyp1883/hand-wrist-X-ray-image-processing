/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hand.wrist.x.ray.image.processing;

import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;



/**
 *
 * @author Makoto
 */
public class ReadImage {
    //此class的目的是要將輸入的影像讀出，並且儲存RGB各自的值
    BufferedImage  Input;
    File file;
    int height;
    int width;
    int imagesize;
    int[][] red;
    int[][] green;
    int[][] blue;
    
    public ReadImage(String Filename)throws IOException{ 
     file=new File(Filename);
     Input= ImageIO.read(file);
     height=Input.getHeight();
     width=Input.getWidth();
     imagesize=height*width;
     red=new int[height][width];
     green=new int[height][width];
     blue=new int[height][width];
     //取得input影像的長寬資訊
     int tempRGB[]=new int[imagesize];
     int RGBdata[][]=new int[height][width];
     int scanwidth=width;
     Input.getRGB(0, 0, width, height, tempRGB, 0, scanwidth);
     //將bi物件中的image RGB值存入tempRGB一維陣列中
     // 參數依序是:起始座標(一對)，終止座標(一對)，存入的目標陣列，在目標陣列中由哪個index對應的元素開始，在掃描的圖中scan的寬幅
    
     for(int i=0;i<height;i++){
         for(int j=0;j<width;j++){
            RGBdata[i][j]=tempRGB[i*width+j];}}
     for(int i=0;i<height;i++){
         for(int j=0;j<width;j++){
          red[i][j]=(int)((RGBdata[i][j]&0x00ff0000)>>16);
 //將rgbData[i][j]對0x00ff0000作AND運算以取得rgbData[i][j]中的第17~24bit，並右移16個位元
          green[i][j]=(int)((RGBdata[i][j]&0x0000ff00)>>8);
 //將rgbData[i][j]對0x0000ff00作AND運算以取得rgbData[i][j]中的第9~16bit並右移8個位元         
          blue[i][j]=(int)(RGBdata[i][j]&0x000000ff);}}
 //將rgbData[i][j]對0x0000ff00作AND運算以取得rgbData[i][j]中的第1~8bit    
    }
    public int getHeight(){
    return height;
    }
    public int getWidth(){
    return width;
    }
}