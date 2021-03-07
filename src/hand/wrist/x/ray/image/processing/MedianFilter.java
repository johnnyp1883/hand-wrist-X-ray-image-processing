/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hand.wrist.x.ray.image.processing;

/**
 *
 * @author Makoto
 */
public class MedianFilter {
   
    private int sizeMid;
    private int tmpH;
    private int tmpW;
    private int[][] result;
    private int Median;// 第幾大的數是一個特定size的filter的中位數
public MedianFilter(Scale scale ,int[][] inputImage,int height,int width){
    result=new int[height][width];
    if(scale==Scale.three){
    Median=5;
    sizeMid=1;
    tmpH=2*sizeMid+height;
    tmpW=2*sizeMid+width;
   
    }
    else if(scale==Scale.five){
    Median=13;
    sizeMid=2;
    tmpH=2*sizeMid+height;
    tmpW=2*sizeMid+width;
 
    }
    else{
    Median=25;
    sizeMid=3;
    tmpH=2*sizeMid+height;
    tmpW=2*sizeMid+width;
    }
    Reflection ref=new Reflection(inputImage,tmpH,tmpW,sizeMid);
    int[][] refImg=ref.getImage();
    
    int[] sortQueue=new int[(sizeMid*2+1)*(sizeMid*2+1)];//一個filter之大小
    for(int i=sizeMid;i<tmpH-sizeMid;i++)
        for(int j=sizeMid;j<tmpW-sizeMid;j++){
            for(int m=0;m<sortQueue.length;m++){
               for(int k=-sizeMid;k<=sizeMid;k++){
                  for(int l=-sizeMid;l<=sizeMid;l++)
                     sortQueue[m]=refImg[i+k][j+l];}
        }
          result[i-sizeMid][j-sizeMid] =sort(Median,sortQueue); 
                    }
}
    
 private int sort(int Median,int[] sortQueue){
     int Max=0;
     int MaxIndex=0;
   for(int i=1;i<=Median;i++){ //在此queue中找尋Median次的最大值，並捨去最大值，輸出最後一次的最大值即是中位數  
     for(int j=i;j<sortQueue.length;j++){
         if(sortQueue[j]>Max)
              Max=sortQueue[j];//找到sortQueue中的最大值
              MaxIndex=j;
         }
       swap(sortQueue[MaxIndex],sortQueue[i]);
       Max=0;
     }
   return (sortQueue[Median-1]);
 }   
 private void swap(int a,int b){
 int tmp=0;
 tmp=a;
 a=b;
 b=a;
 
 }   
 public int[][] getImg(){
return result;
 }   
}


